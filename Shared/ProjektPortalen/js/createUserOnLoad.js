window.onload = () => {
    let btn = document.getElementById("opretButton");
    btn.addEventListener("click", () => {
        if (document.getElementById("userName").value != "" && document.getElementById("pw").value != "" && document.getElementById("name").value != "" && document.getElementById("email").value != "") {
            let subUserName = document.getElementById("userName").value;
            let subpw = document.getElementById("pw").value;
            let subBusBool = false;
            if (document.getElementById("virksomhed").checked) {
                busBool = true;
            }
            let subName = document.getElementById("name").value;
            let subEmail = document.getElementById("email").value;

            // THIS FIXES SHIT - DONT TOUCH!!!
            // If we don't ping the server first, sometimes the POST request never reaches it.
            // for some reason, if we have a useless GET-fetch first, it works.
            // (This is btw a huge security issue, because we get all information about every user.)
            fetch("http://localhost:8888/allusers");

            fetch("http://localhost:8888/allusers", {
                method: 'POST',
                body: JSON.stringify({
                    name: subName,
                    email: subEmail,
                    busBool: subBusBool,
                    username: subUserName,
                    password: subpw
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            });

            document.getElementById("userName").value="";
            document.getElementById("pw").value="";
            document.getElementById("name").value="";
            document.getElementById("email").value="";

        }
        else {
            console.log("Udfyld venligst alle felterne!");
        }
    })

    setupNav();
}