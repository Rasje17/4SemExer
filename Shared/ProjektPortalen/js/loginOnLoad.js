window.onload = () => {
    let loginBtn = document.getElementById("loginButton");
    loginBtn.addEventListener("click", () => {
        let userName = document.getElementById("uNameField").value;
        fetch("http://localhost:8888/loginuser?username=" + userName)
            .then(response => response.json())
            .then(json => loginHandle(json));
    });
    document.getElementById("createNewButton").onclick = function () {
        location.href = 'createUser.html';
    };
}