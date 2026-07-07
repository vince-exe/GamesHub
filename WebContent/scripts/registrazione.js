function checkConfirmPassword(formElem, span, passwordElement) {
	if (!formElem.checkValidity()) {
        formElem.classList.add("input-error");
        if (formElem.validity.valueMissing) {
            span.innerHTML = "Questo campo è obbligatorio";
        } else if (formElem.validity.tooShort) {
            span.innerHTML = "La lunghezza minima consentita è di 8 caratteri";
        }
        return false;
    }

    if (passwordElement.value !== formElem.value) {
        formElem.classList.add("input-error");
        span.innerHTML = "Le due password non coincidono";
        return false;
    }
    
    formElem.classList.remove("input-error");
    span.innerHTML = "";
    return true;
}

function checkRegistrazioneForm() {
    let valid = true;    
    let form = document.getElementById("regForm");
    
    let spanNome = document.getElementById("errorNome");
    if (!checkNomeCognome(form.nome, spanNome)) 
		valid = false;
	
    let spanCognome = document.getElementById("errorCognome");
    if (!checkNomeCognome(form.cognome, spanCognome)) 
		valid = false;

    let spanEmail = document.getElementById("errorEmail");
    if (!checkEmail(form.email, spanEmail)) 
		valid = false;
    
    let spanPassword = document.getElementById("errorPassword");
    if (!checkPassword(form.password, spanPassword))
		valid = false;
    
    let spanConfermaPassword = document.getElementById("errorConfermaPassword");
    if (!checkConfirmPassword(form.confermaPassword, spanConfermaPassword, form.password))
		valid = false;

    return valid;
}

function checkNomeCognome(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
	
    if (formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } else if(formElem.validity.tooLong) {
		span.innerHTML = "La lunghezza massima consentita è di 20 caratteri";
	} else if(formElem.validity.tooShort) {
		span.innerHTML = "La lunghezza minima consentita è di 1 carattere";
    }
    return false;
}