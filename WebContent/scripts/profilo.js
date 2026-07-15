function checkProfiloForm() {
    let valid = true;    
    let form = document.getElementById("profiloForm");
    
    let spanNome = document.getElementById("errorNome");
    if (!checkNomeCognome(form.nome, spanNome))
		valid = false;
	
    let spanCognome = document.getElementById("errorCognome");
    if (!checkNomeCognome(form.cognome, spanCognome))
		valid = false;

    let spanEmail = document.getElementById("errorEmail");
    if (!checkEmail(form.email, spanEmail))
		valid = false;

    let spanCellulare = document.getElementById("errorCellulare");
    if (!checkCellulare(form.telefono, spanCellulare))
		valid = false;

    let spanPassword = document.getElementById("errorPassword");
    let spanConfermaPassword = document.getElementById("errorConfermaPassword");

    // la nuova password viene controllata solo se l'utente ha iniziato a scriverla così so di per certo che vuole modificarla
    if (form.nuovaPassword.value.length > 0) {
        if (!checkPassword(form.nuovaPassword, spanPassword))
			valid = false;
		
        if (!checkConfirmPassword(form.confermaPassword, spanConfermaPassword, form.nuovaPassword))
			valid = false;
    } 
	else {
        form.nuovaPassword.classList.remove("input-error");
        form.confermaPassword.classList.remove("input-error");
        spanPassword.innerHTML = "";
        spanConfermaPassword.innerHTML = "";
    }

    let spanDataNascita = document.getElementById("errorData");
    if (!checkDataNascita(form.dataNascita, spanDataNascita))
		valid = false;

    return valid;
}