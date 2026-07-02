function checkFormElement(formElem, span, errorMessage) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
	
    if (formElem.validity.valueMissing) 
        span.innerHTML = "Questo campo è obbligatorio";
	else if(formElem.validity.tooShort)
		span.innerHTML = "Lunghezza minima 8 caratteri"
    else 
    	span.innerHTML = errorMessage;
    
    return false;
}

function checkForm() {
    let valid = true;    
	
    let form = document.getElementById("logForm");
    
    let spanEmail = document.getElementById("errorEmail");
    if (!checkFormElement(form.email, spanEmail, "email invalida"))
        valid = false;
    
    let spanPassword = document.getElementById("errorPassword");
    if (!checkFormElement(form.password, spanPassword, "password invalida"))
        valid = false;
        
    return valid;
}