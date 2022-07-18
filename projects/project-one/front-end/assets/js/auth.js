let loginBtn = document.getElementById('loginBtn');
loginBtn.addEventListener('click', login);

let apiUrl = 'http://localhost:8080/project-one';

async function login() {

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let response = await fetch(`${apiUrl}/auth`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'username': `${username}`, 
            'password': `${password}`
        })
    });

    if(response.status == 200) {
        let data = await response.json();
        let userRole = data.role;

        if(userRole == "Employee") {
            //Store user data inside the session
            sessionStorage.setItem('principal', JSON.stringify(data));
            
            //Redirect user to appropriate home page
            window.location.href = "../../employeehome.html";
        }
        else {
            sessionStorage.setItem('principal', JSON.stringify(data));
            window.location.href = "../../managerhome.html";
        }
    } else {
        console.log("Unable to log in.");
        document.getElementById("errorMsg").innerHTML = "Invalid credentials."
    }
}