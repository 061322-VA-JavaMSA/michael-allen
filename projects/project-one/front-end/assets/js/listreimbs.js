async function listReimbs(role, status) {
    /* Because this function is called when the page loads, insert the navbar as well as load the results. */
    insertNav();

    let username = null;
    let response;

    if(role=="employee") {
        username = principal.username;

        response = await fetch(`${apiUrl}/reimbursements?author=${username}&status=${status}`, {
            credentials: 'include'
        });

        if(response.status == 200) {
            let data = await response.json();

            if(status=="Pending")
                createPendingTable(data);
            if(status=="Approved" || status=="Denied")
                createResolvedTable(data);
        }
        else {
            console.log("Unable to retrieve reimbursements.");
        }
    }
    else if(role=="manager") {

        response = await fetch(`${apiUrl}/reimbursements?status=${status}`, {
            credentials: 'include'
        });

        if(response.status == 200) {
            let data = await response.json();

            if(status=="Pending")
                createDecisionTable(data);
            if(status=="Approved" || status=="Denied")
                createManagerResolvedTable(data);
        }
    
    }

    function createPendingTable(data) {

        let tableBody = document.getElementById("reimbs-tbody");

        data.forEach(reimb => {
            let tr = document.createElement('tr');
            let tdType = document.createElement('td');
            let tdDescrip = document.createElement('td');
            let tdAmount = document.createElement('td');
            let tdSubmitted = document.createElement('td');

            let lcReimbType = reimb.type;
            let ucReimbType = lcReimbType.charAt(0).toUpperCase() + lcReimbType.slice(1);

            tdType.innerHTML = ucReimbType;
            tdDescrip.innerHTML = reimb.description;
            tdAmount.innerHTML = "$" + reimb.amount;
            tdSubmitted.innerHTML = reimb.submitted;

            tr.append(tdType);
            tr.append(tdDescrip);
            tr.append(tdAmount);
            tr.append(tdSubmitted);

            tableBody.append(tr);
        });
    }

    function createDecisionTable(data) {

        let tableBody = document.getElementById("reimbs-tbody");

        data.forEach(reimb => {
            let tr = document.createElement('tr');
            let tdAuthor = document.createElement('td');
            let tdType = document.createElement('td');
            let tdDescrip = document.createElement('td');
            let tdAmount = document.createElement('td');
            let tdSubmitted = document.createElement('td');
            let tdDecision = document.createElement('td');

            let lcReimbType = reimb.type;
            let ucReimbType = lcReimbType.charAt(0).toUpperCase() + lcReimbType.slice(1);

            tdAuthor.innerHTML = reimb.author;
            tdType.innerHTML = ucReimbType;
            tdDescrip.innerHTML = reimb.description;
            tdAmount.innerHTML = "$" + reimb.amount;
            tdSubmitted.innerHTML = reimb.submitted;
            tdDecision.innerHTML = "<select id='select" + reimb.id + "'>"
                                + "<option value='Approved'>Approve</option>"
                                + "<option value='Denied'>Deny</option>"
                                + "</select>"
                                + " | <button id='updateBtn' onclick='updateReimb(" + reimb.id + ")'>Submit</button>";

            tr.append(tdAuthor);
            tr.append(tdType);
            tr.append(tdDescrip);
            tr.append(tdAmount);
            tr.append(tdSubmitted);
            tr.append(tdDecision);

            tableBody.append(tr);
        });
    }

    function createResolvedTable(data) {

        let tableBody = document.getElementById("reimbs-tbody");

        data.forEach(reimb => {
            let tr = document.createElement('tr');
            let tdType = document.createElement('td');
            let tdDescrip = document.createElement('td');
            let tdAmount = document.createElement('td');
            let tdSubmitted = document.createElement('td');
            let tdManager = document.createElement('td');
            let tdResolveDate = document.createElement('td');

            let lcReimbType = reimb.type;
            let ucReimbType = lcReimbType.charAt(0).toUpperCase() + lcReimbType.slice(1);

            tdType.innerHTML = ucReimbType;
            tdDescrip.innerHTML = reimb.description;
            tdAmount.innerHTML = "$" + reimb.amount;
            tdSubmitted.innerHTML = reimb.submitted;
            tdManager.innerHTML = reimb.resolver;
            tdResolveDate.innerHTML = reimb.resolved;

            tr.append(tdType);
            tr.append(tdDescrip);
            tr.append(tdAmount);
            tr.append(tdSubmitted);
            tr.append(tdManager);
            tr.append(tdResolveDate);

            tableBody.append(tr);
        });
    }

    function createManagerResolvedTable(data) {

        let tableBody = document.getElementById("reimbs-tbody");

        data.forEach(reimb => {
            let tr = document.createElement('tr');
            let tdEmployee = document.createElement('td');
            let tdType = document.createElement('td');
            let tdDescrip = document.createElement('td');
            let tdAmount = document.createElement('td');
            let tdSubmitted = document.createElement('td');
            let tdManager = document.createElement('td');
            let tdResolveDate = document.createElement('td');

            let lcReimbType = reimb.type;
            let ucReimbType = lcReimbType.charAt(0).toUpperCase() + lcReimbType.slice(1);

            tdEmployee.innerHTML = reimb.author;
            tdType.innerHTML = ucReimbType;
            tdDescrip.innerHTML = reimb.description;
            tdAmount.innerHTML = "$" + reimb.amount;
            tdSubmitted.innerHTML = reimb.submitted;
            tdManager.innerHTML = reimb.resolver;
            tdResolveDate.innerHTML = reimb.resolved;

            tr.append(tdEmployee);
            tr.append(tdType);
            tr.append(tdDescrip);
            tr.append(tdAmount);
            tr.append(tdSubmitted);
            tr.append(tdManager);
            tr.append(tdResolveDate);

            tableBody.append(tr);
        });
    }
}