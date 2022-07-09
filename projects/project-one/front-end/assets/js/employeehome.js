let welcome = document.getElementById("welcome");

if(principal) {
    welcome.innerHTML = `Welcome, ${principal.firstName}!`;
}