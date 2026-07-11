function handleTipologiaChange(selectElement) {
	if (!selectElement)
		return;
    
    const piattaformaSelect = document.getElementById('piattaforma');
    const chiaveInput = document.getElementById('chiaveAttivazione');
    const genere = document.getElementById("genere");
	
    if (selectElement.value === 'videogioco') {
		genere.disabled = false;
		genere.required = true;
        piattaformaSelect.disabled = false;
        chiaveInput.disabled = false;
        piattaformaSelect.required = true;
        chiaveInput.required = true;
    } 
	else { // Se è una console
        piattaformaSelect.disabled = true;
		genere.disabled = true;
        chiaveInput.disabled = true;
        piattaformaSelect.required = false;
        chiaveInput.required = false;
		genere.required = false;
		
		genere.value = ""
        piattaformaSelect.value = "";
        chiaveInput.value = "";
        
        document.getElementById('errorPiattaforma').innerHTML = "";
        document.getElementById('errorChiave').innerHTML = "";
		document.getElementById('errorGenere').innerHTML = "";
		
		genere.classList.remove('input-error');
        piattaformaSelect.classList.remove("input-error");
        chiaveInput.classList.remove("input-error");
    }
}

function checkTipologia(formElem, span) {
	if (formElem.value === "") {
	    formElem.classList.add("input-error");
	    span.innerHTML = "Questo campo è obbligatorio";
	    return false;
	}
	
    handleTipologiaChange(formElem);
    
    formElem.classList.remove("input-error");
    span.innerHTML = "";
    return true;
}

function checkChiave(formElem, span) {
    if (formElem.disabled) // nel caso è una console l'elemento è disabilitato quindi nessun controllo
		return true;

    if (formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
        formElem.classList.add("input-error");
        return false;
    } 
	else if (formElem.validity.patternMismatch) {
        span.innerHTML = "Inserisci una chiave valida (AAAA-BBBB-CCCC-DDDD)";
        formElem.classList.add("input-error");
        return false;        
    } 
	else {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
}

function checkInput(formElem, span, minlen) {
	if(formElem.disabled) // controllo specifico nel caso sia 'genere'
		return true;
		
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
    
    if (formElem.validity.valueMissing)
        span.innerHTML = "Questo campo è obbligatorio";
    else if (formElem.validity.tooShort)
        span.innerHTML = "La lunghezza minima consentita è di " + minlen + " caratteri";
	
    return false;
}

function checkPiattaforma(formElem, tipologiaValue, span) {
    if (tipologiaValue !== "videogioco") {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }	
	
    if (formElem.value === "") { // la tipologia è videogioco ma la piattaforma è vuota
        formElem.classList.add("input-error");
        span.innerHTML = "Questo campo è obbligatorio";
        return false;
    }

    formElem.classList.remove("input-error");
    span.innerHTML = "";
    return true;
}

function checkAggiungiProdottoForm() {
    let valid = true;    
    let form = document.getElementById("aggiungiForm");

    let spanNome = document.getElementById("errorNome");
    if (!checkInput(form.nome, spanNome, 1))
		valid = false;
	
    let spanCasa = document.getElementById("errorCasa");
    if (!checkInput(form.casaProduttrice, spanCasa, 1))
		valid = false;
	
    let spanTipologia = document.getElementById("errorTipologia");
    if (!checkTipologia(form.tipologia, spanTipologia))
		valid = false;
	
    let spanGenere = document.getElementById("errorGenere");
    if (!checkInput(form.genere, spanGenere, 1))
		valid = false;
	
	let spanPiattaforma = document.getElementById("errorPiattaforma");
	if (!checkPiattaforma(form.piattaforma, form.tipologia.value, spanPiattaforma))
		valid = false;

    let spanChiave = document.getElementById("errorChiave");
    if (!checkChiave(form.chiaveAttivazione, spanChiave))
		valid = false;
	
    let spanDescrizione = document.getElementById("errorDescrizione");
    if (!checkInput(form.descrizione, spanDescrizione, 10))
		valid = false;

    let spanImmagine = document.getElementById("errorImmagine");
    if (!checkInput(form.immagine, spanImmagine, 0))
		valid = false;
	
    return valid;
}