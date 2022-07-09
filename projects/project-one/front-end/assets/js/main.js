let principalString = sessionStorage.getItem('principal');
let principal = null;

if(principalString) {
    principal = JSON.parse(principalString);
}