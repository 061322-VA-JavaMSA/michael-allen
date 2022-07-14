async function listEmp() {
    response = await fetch (`${apiUrl}/users?role=Employee`, {
        credentials: 'include'
    });

    if(response.status == 200) {

        let data = await response.json();

        populateEmployeeTable(data);

    }
    else {
        console.log("Unable to retreive employees.");
    }
}

function populateEmployeeTable(data) {

    let tableBody = document.getElementById("reimbs-tbody");

    data.forEach(user => {
        let tr = document.createElement('tr');
        let tdName = document.createElement('td');
        let tdUsername = document.createElement('td');
        let tdEmail = document.createElement('td');
        let tdReimbs = document.createElement('td');

        tdName.innerHTML = user.firstName + " " + user.lastName;
        tdUsername.innerHTML = user.username;
        tdEmail.innerHTML = user.email;
        tdReimbs.innerHTML = "<a href='manemployeereimb.html?fname=" + user.firstName + "&lname=" + user.lastName + "' id='viewLink'>View reimbursement requests</a>"

        tr.append(tdName);
        tr.append(tdUsername);
        tr.append(tdEmail);
        tr.append(tdReimbs);

        tableBody.append(tr);
    });
}
