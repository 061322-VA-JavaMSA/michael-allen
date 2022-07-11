async function listReimbs(role, status) {
    /* Because this function is called when the page loads, insert the navbar as well as load the results. */
    insertNav();

    let username = null;
    let response;

    if (role=="employee") {
        username = principal.username;

        response = await fetch(`${apiUrl}/reimbursements?author=${username}&status=${status}`, {
            credentials: 'include'
        });
    }
    else {
        if(status=="Pending") {
            response = await fetch(`${apiUrl}/reimbursements?status=${status}`, {
                credentials: 'include'
            });
        }
    }

    if(response.status == 200) {
        let data = await response.json();

        if(data[0].resolved == null) {
            if(role=="manager") {
                createDecisionTable(data);
            }
            else {
                createPendingTable(data);
            }
        }
        else if (data[0].resolved != "" && data[0].resolved != null) {
            createResolvedTable(data);
        }
        
    }
    else {
        console.log("Unable to retreive reimbursements.");
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
            tdDecision.innerHTML = "<select name='managerDecision' id='managerDecision'>"
                                    + "<option value='approve'>APPROVE</option>"
                                    + "<option value='deny'>DENY</option>"
                                + "</select>"
                                + " | <input type='button' value='Submit'>";

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
}