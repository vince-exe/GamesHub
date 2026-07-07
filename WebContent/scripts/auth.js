function checkEmail(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
    
    if (formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } else if (formElem.validity.typeMismatch) {
        span.innerHTML = "Formato email non valido (es. nome@dominio.com)";
    } else if (formElem.validity.tooShort) {
        span.innerHTML = "La lunghezza minima consentita è di 11 caratteri";
    } else if (formElem.validity.tooLong) {
        span.innerHTML = "La lunghezza massima consentita è di 30 caratteri";
    }
	
    return false;
}

function checkPassword(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
	
    if (formElem.validity.valueMissing) 
        span.innerHTML = "Questo campo è obbligatorio";
    else if(formElem.validity.tooShort)
		span.innerHTML = "La lunghezza minima consentita è di 8 caratteri";
	else if(formElem.validity.tooLong)
		span.innerHTML = "La lunghezza massima consentita è di 20 caratteri";
	
    return false;
}