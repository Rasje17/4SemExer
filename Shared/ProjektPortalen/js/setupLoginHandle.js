function loginHandle(JSONdata) {
    let data = JSON.parse(JSONdata);
    let pw = document.getElementById("pwField").value;
    let loginID = 0;
    let return_url = "";

    if (urlParams.has('src')) {
        if (data.password == pw.toString()) {
            console.log("Password was correct!");
            // login godkendt, giv id og send videre
            if (data.busBool) {
                // business sendes til businesspage
                loginID = 2;

                if (urlParams.get('src').includes('?')) {
                    return_url = urlParams.get('src').substr(0).split('&') + "&userID=" + data.id + "&loginVal=" + loginID;
                    if (urlParams.has('offerId')) {
                        return_url += urlParams.get('offerId');
                    }
                }
                else {
                    return_url = urlParams.get('src') + "?userID=" + data.id + "&loginVal=" + loginID;
                }

                window.location.href = return_url;
            }
            else {
                // student sendes til offer overview
                loginID = 1;

                if (urlParams.get('src').includes('?')) {
                    return_url = urlParams.get('src').substr(0).split('&') + "&userID=" + data.id + "&loginVal=" + loginID;
                    if (urlParams.has('offerId')) {
                        return_url += urlParams.get('offerId');
                    }
                }
                else {
                    return_url = urlParams.get('src') + "?userID=" + data.id + "&loginVal=" + loginID;
                }

                window.location.href = return_url;
            }
        }
        else {
            // login afvist, reset
            // evt check noget ud med et label og set style.visibility="visible", men "hidden" til at starte med
            window.location.href = 'login.html';
        }
    }
    else {
        if (data.password == pw.toString()) {
            console.log("Password was correct!");
            // login godkendt, giv id og send videre
            if (data.busBool) {
                // business sendes til businesspage
                loginID = 2;
                window.location.href = ('businessPage.html?loginVal=' + loginID + '&userID=' + data.id);
            }
            else {
                // student sendes til offer overview
                loginID = 1;
                window.location.href = ('offerOverview.html?loginVal=' + loginID + '&userID=' + data.id);
            }
        }
        else {
            // login afvist, reset
            // evt check noget ud med et label og set style.visibility="visible", men "hidden" til at starte med
            window.location.href = 'login.html';
        }
    }
}