const terminalElement = document.getElementById('terminal');
const inputElement = document.createElement('div');
inputElement.id = 'input';
terminalElement.appendChild(inputElement);

terminalElement.addEventListener('keydown', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        const command = inputElement.textContent.trim();
        if (command.length > 0) {
            processCommand(command);
            sendCommandToServer(command);
            appendCommand('');
            inputElement.textContent = '';
        }
    }
});

terminalElement.addEventListener('click', function () {
    moveCursorToEnd();
});

function setColor() {
    fetch(`/terminal/user/${encodeURIComponent(localStorage.getItem('userId'))}`)

        .then(response => response.json())
        .then(data => {
            document.body.style.backgroundColor = data.windowColor ;
            document.getElementById('input').style.backgroundColor = data.windowColor;
            document.getElementById('input').style.color = data.textColor ;
            document.body.style.color = data.textColor ;
            console.log(  document.getElementById('windowColor').value)
            document.getElementById('windowColor').value = data.windowColor;
            document.getElementById('textColor').value = data.textColor;

        })
        .catch(error => {
            console.error('Error:', error);
            appendCommand('Error occurred while processing the command.');
        });
}

function moveCursorToEnd() {
    const range = document.createRange();
    const selection = window.getSelection();
    range.selectNodeContents(inputElement);
    range.collapse(false);
    selection.removeAllRanges();
    selection.addRange(range);
}

function processCommand(command) {
    const result = `$ ${command} (processed result)`;
    appendCommand(result);
}

function appendCommand(command) {
    const newCommandElement = document.createElement('p');
    newCommandElement.textContent = command;
    terminalElement.insertBefore(newCommandElement, inputElement);
    moveCursorToEnd();
    terminalElement.scrollTop = terminalElement.scrollHeight;
}

function sendCommandToServer(command) {
    const url =
        command.startsWith("/b")
            ? `/terminal/b?command=${encodeURIComponent(command.substring(3))}`
            : command.startsWith("/c")
                ? `/terminal/c?command=${encodeURIComponent(command.substring(3))}`
                : `/terminal/command?command=${encodeURIComponent(command)}`;
    console.log(url)
    fetch(url)
        .then(response => response.text())
        .then(data => {
            const responseText = `Server Response: ${data}`;
            appendCommand(responseText
            );
        })
        .catch(error => {
            console.error('Error:', error);
            appendCommand('Error occurred while processing the command.');
        });
}

function saveColorSettings() {
    const windowColor = document.getElementById('windowColor').value;
    const textColor = document.getElementById('textColor').value;

    const userId = encodeURIComponent(localStorage.getItem('userId'));

    const url = `/terminal/user/${userId}/settings`;
    const settings = {
        windowColor: windowColor,
        textColor: textColor
    };

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(settings),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Налаштування збережено:', data);
            setColor();
        })
        .catch(error => {
            console.error('Помилка збереження налаштувань:', error);
        });
}

setColor();
