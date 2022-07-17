let apiUrl = 'http://localhost:8080/project-one';

let principalString = sessionStorage.getItem('principal');
let principal = null;

if(principalString) {
    principal = JSON.parse(principalString);
}

async function logout() {

    let response = await fetch(`${apiUrl}/auth`, {
        method: 'DELETE',
        credentials: 'include'
    });

    if(response.status == 200) {
        sessionStorage.clear();
        principal = null;
        window.location.href = "../../index.html";
    }
    else {
        console.log('Unable to logout.');
    }
}
