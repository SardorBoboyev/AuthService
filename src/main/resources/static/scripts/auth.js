function signUp() {
    clearErrors();

    const user = {
        username: $("#username").val(),
        password: $("#password").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        phoneNumber: $("#phoneNumber").val(),
        dateOfBirth: $("#dateOfBirth").val()
    };

    let hasError = false;

    if (!user.username || user.username.length < 3 || user.username.length > 20) {
        showError('usernameError', 'Username must be between 3 and 20 characters');
        hasError = true;
    }

    if (!user.password || user.password.length < 8) {
        showError('passwordError', 'Password must be at least 8 characters long');
        hasError = true;
    }

    if (!user.firstName) {
        showError('firstNameError', 'First name is mandatory');
        hasError = true;
    }

    if (!user.lastName) {
        showError('lastNameError', 'Last name is mandatory');
        hasError = true;
    }

    const phonePattern = /^\+?[0-9]{7,15}$/;
    if (!user.phoneNumber || !phonePattern.test(user.phoneNumber)) {
        showError('phoneNumberError', 'Phone number must be between 7 to 15 digits');
        hasError = true;
    }

    if (!user.dateOfBirth) {
        showError('dateOfBirthError', 'Date of birth is mandatory');
        hasError = true;
    }

    if (hasError) {
        return;
    }

    sendRequest(SIGN_UP_URL, user, 'POST')
        .then(res => {
            if (res.ok) {
                window.location.href = '/auth';
            } else {
                throw new Error('Invalid credentials');
            }
        })
        .catch(err => {
            console.error(err);
            showError('globalError', 'An error occurred during sign up. Please try again.');
        });
}

function showError(fieldId, message) {
    const errorField = document.getElementById(fieldId);
    if (errorField) {
        errorField.textContent = message;
        errorField.style.display = 'block';
    }
}

// Функция для очистки ошибок
function clearErrors() {
    const errorFields = document.querySelectorAll('.error-message');
    errorFields.forEach(field => {
        field.textContent = '';
        field.style.display = 'none';
    });
}
