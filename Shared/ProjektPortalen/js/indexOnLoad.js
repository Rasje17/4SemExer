window.onload = () => {
    console.log("this script is used");

    if (urlParams.has('loginVal') && urlParams.get('loginVal') != 0 && urlParams.get('loginVal') != null) {
        document.getElementById('loginRef').innerHTML = "Log Ud";
        document.getElementById('loginRef').href = "index.html";
    }
    else {
        document.getElementById('loginRef').innerHTML = "Login";
        document.getElementById('loginRef').href = ("login.html");
    }
}