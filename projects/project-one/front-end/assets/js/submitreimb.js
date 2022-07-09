let submitForm = document.getElementById("submitForm");
let errorMsg1 = document.getElementById("noDescripError");
let errorMsg2 = document.getElementById("noAmountError");
let submitBtn = document.getElementById('submitBtn');
submitBtn.addEventListener('click', submitReimb);

let apiUrl = 'http://localhost:8080/project-one';

async function submitReimb() {

    let username = principal.username;
    let reimbType = document.getElementById("reimbType").value;
    let reimbDescrip = document.getElementById("reimbDescrip").value;
    let reimbAmount = document.getElementById("reimbAmount").value;
    
    let current = new Date();
    let dateSubmitted = current.toLocaleDateString();

    let reimbStatus = "Pending";

    if(reimbDescrip == "") {
        errorMsg1.innerHTML = "You must enter a description.<br><br>";
    }
    if(reimbAmount == "") {
        errorMsg2.innerHTML = "You must enter an amount.<br><br>";
    }
    else {

        const object = { description: `${reimbDescrip}`,
            author: `${username}`,
            amount: `${reimbAmount}`,
            type: `${reimbType}`,
            submitted: `${dateSubmitted}`,
            status: `${reimbStatus}`};

        let response = await fetch(`${apiUrl}/reimbursements`, {
            method: 'POST',
            body: JSON.stringify(object)
        });

        if(response.status == 201) {
            window.location.href = "../../submitsuccess.html";
        } else {
            console.log("Unable to submit reimbursement request.");
        }
    }
}