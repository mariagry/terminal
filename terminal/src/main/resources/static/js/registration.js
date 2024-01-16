function register() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    fetch('/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            } else {
                return response.json();
            }
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => console.error('Error:', error));
}