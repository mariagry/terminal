function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/user/sign-in', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => response.text())
        .then(data => {
            if (data === 'Error') {

                appendCommand('Incorrect login or password');
            } else {
                const userId = data;
                window.location.href = '/console';
                localStorage.setItem('userId', userId);
            }
        })
        .catch(error => console.error('Error:', error));
}
