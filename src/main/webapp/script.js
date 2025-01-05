// Add current date and time to the footer
function updateDateTime() {
    const dateTimeElement = document.getElementById('date-time');
    const now = new Date();
    const formattedDateTime = now.toLocaleString();
    dateTimeElement.textContent = `Current Date and Time: ${formattedDateTime}`;
}

// Validate signup form
function validateSignupForm(event) {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (username.trim() === "") {
        alert("Username is required.");
        event.preventDefault();
        return false;
    }

    if (password.length < 8) {
        alert("Password must be at least 8 characters long.");
        event.preventDefault();
        return false;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        event.preventDefault();
        return false;
    }

    return true;
}

// Add event listeners
document.addEventListener('DOMContentLoaded', () => {
    updateDateTime();
    setInterval(updateDateTime, 60000); // Update date and time every minute

    const signupForm = document.getElementById('signup-form');
    if (signupForm) {
        signupForm.addEventListener('submit', validateSignupForm);
    }
});
