document.getElementById('hamburgerBtn').addEventListener('click', function() {
    const navMenu = document.querySelector(".nav-menu");
    const userActions = document.querySelector(".user-actions");
	
    this.classList.toggle("active");
    navMenu.classList.toggle("active");
    userActions.classList.toggle("active");
});