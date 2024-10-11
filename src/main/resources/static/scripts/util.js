const HOST = 'http://localhost:8089'
const LOGIN_URL = HOST + '/api/auth/login';
const SIGN_UP_URL = HOST + '/api/auth/register';

function sendRequest(url, data, method) {
    switch (method) {
        case 'POST':
            return fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify(data),
            });
        case 'GET':
            return fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                }
            });
    }
}