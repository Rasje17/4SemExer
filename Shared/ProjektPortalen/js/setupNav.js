let this_url = window.location.href;

    function setupNav() {
        if (urlParams.has('loginVal') && urlParams.get('loginVal') != 0 && urlParams.get('loginVal') != null) {
            document.getElementById('loginRef').innerHTML = "Log Ud";
            document.getElementById('loginRef').href = "index.html";
        }
        else {
            document.getElementById('loginRef').innerHTML = "Login";
            document.getElementById('loginRef').href = ("login.html?src=" + this_url);
        }

        document.getElementById('offerlink').innerHTML = "Tilbudsoversigt";
        document.getElementById('offerlink').href = ('offerOverview.html?userID=' + urlParams.get('userID') + '&loginVal=' + urlParams.get('loginVal'));
    }