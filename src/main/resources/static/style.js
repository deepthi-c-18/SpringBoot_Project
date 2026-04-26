// Call GET API
function getMessage() {
    fetch('/api/message')
        .then(response => response.text())
        .then(data => {
            document.getElementById("apiResponse").innerText = data;
        });
}

// Call GET API with parameter
function sendName() {
    let name = prompt("Enter your name:");
    
    fetch('/api/greet?name=' + name)
        .then(response => response.text())
        .then(data => {
            document.getElementById("apiResponse").innerText = data;
        });
}
document.getElementById("userForm").addEventListener("submit", function(e) {
    e.preventDefault();

    let user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(res => res.json())
    .then(data => {

        // Clear previous messages
        document.getElementById("errors").innerText = "";
        document.getElementById("response").innerText = "";

        // Check if validation errors
        if (data.name || data.email) {
            document.getElementById("errors").innerText =
                JSON.stringify(data);
        } else {
            document.getElementById("response").innerText =
                "User saved successfully!";
        }
    });
});/**
 * 
 */