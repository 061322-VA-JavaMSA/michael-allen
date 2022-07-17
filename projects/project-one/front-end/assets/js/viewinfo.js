let fname = principal.firstName;
let lname = principal.lastName;
let username = principal.username;
let email = principal.email;

let propertyColumn = document.getElementById("property");
propertyColumn.innerHTML = "<p><strong>First Name:</strong></p>"
                        + "<p><strong>Last Name:</strong></p>"
                        + "<p><strong>Username:</strong></p>"
                        + "<p><strong>Email:</strong></p>";

let valueColumn = document.getElementById("value");
valueColumn.innerHTML = `<p><input class='showInfo' value='${fname}' disabled></input></p>`
                      + `<p><input class='showInfo' value='${lname}' disabled></input></p>`
                      + `<p><input class='showInfo' value='${username}' disabled></input></p>`
                      + `<p><input class='showInfo' value='${email}' disabled></input></p>`;

function editInfo() {
    valueColumn.innerHTML = `<p><input class='infoField' id='fname' value='${fname}'></input></p>`
                          + `<p><input class='infoField' id='lname' value='${lname}'></input></p>`
                          + `<p><input class='infoField' id='username' value='${username}'></input></p>`
                          + `<p><input class='infoField' id='email' value='${email}'></input></p>`;

    let submitBtn = document.getElementById("editSubmitBtn");
    submitBtn.setAttribute("onclick", "submitInfo()");
    submitBtn.innerHTML = "Submit information";
}

async function submitInfo() {
    let updatedFname = document.getElementById("fname").value;
    let updatedLname = document.getElementById("lname").value;
    let updatedUsername = document.getElementById("username").value;
    let updatedEmail = document.getElementById("email").value;
    let id = principal.id;

    const object = { id: `${id}`,
                    firstName: `${updatedFname}`,
                    lastName: `${updatedLname}`,
                    username: `${updatedUsername}`,
                    email: `${updatedEmail}` };

    let response = await fetch(`${apiUrl}/users`, {
        method: 'PUT',
        body: JSON.stringify(object)
    });

    if(response.status == 200) {
        document.getElementById("userInfo").innerHTML = "<p id='updateSuccess'>Your information was successfully updated! Your changes will take effect on the next login.</p>"
    }
}