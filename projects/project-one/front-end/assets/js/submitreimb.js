let submitForm = document.getElementById("submitForm");
let errorMsg1 = document.getElementById("noDescripError");
let errorMsg2 = document.getElementById("noAmountError");
let submitBtn = document.getElementById("submitBtn");
submitBtn.addEventListener('click', submitReimb);

async function submitReimb() {

    let author = principal.firstName + " " + principal.lastName;
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
            author: `${author}`,
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