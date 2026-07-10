function handleTipologiaChange(selectElement) {
	const piattaformaSelect = document.getElementById('piattaforma');
	const chiaveInput = document.getElementById('chiaveAttivazione');
	
	if (selectElement.value === 'videogioco') {
		piattaformaSelect.disabled = false;
		chiaveInput.disabled = false;
		piattaformaSelect.required = true;
		chiaveInput.required = true;
	} else { // se è una console
		piattaformaSelect.disabled = true;
		chiaveInput.disabled = true;
		piattaformaSelect.required = false;
		chiaveInput.required = false;

		piattaformaSelect.value = "";
		chiaveInput.value = "";
	}
}