function checkLoginForm() {
    let valid = true;    
	
    let form = document.getElementById("logForm");
    
    let spanEmail = document.getElementById("errorEmail");
    if (!checkEmail(form.email, spanEmail))
        valid = false;
    
    let spanPassword = document.getElementById("errorPassword");
    if (!checkPassword(form.password, spanPassword))
        valid = false;
    
    return valid;
}