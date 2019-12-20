window.onload = () => {
    let businessID = urlParams.get("userID");
    let businessName = "";

    fetch('http://localhost:8888/users?userID=' + businessID)
        .then(res => res.json())
        .then(json => showName(json));

    function showName(JSONdata) {
        let data = JSON.parse(JSONdata);
        businessName = data.name;
        document.getElementById("businessName").innerHTML = "Tilbudshåndtering for " + data.name;
    }

    let createOfferButton = document.getElementById('createOfferButton');
    createOfferButton.addEventListener('click', () => {
        if (document.getElementById('offerTitle').value != "" && document.getElementById('shortDesc').value != "" && document.getElementById('longDesc').value != "" && document.getElementById('contactInfo').value != "") {
            fetch('http://localhost:8888/createOffer', {
                method: 'POST',
                body: JSON.stringify({
                    ownerID: businessID,
                    offeringBusiness: businessName,
                    title: document.getElementById('offerTitle').value,
                    shortDesc: document.getElementById('shortDesc').value,
                    longDesc: document.getElementById('longDesc').value,
                    contactInfo: document.getElementById('contactInfo').value
                }),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8'
                }
            });

        } else {
            console.log('Udfyld vevnligst alle felterne!');
        }

        document.getElementById("offerTitle").value = "";
        document.getElementById("shortDesc").value = "";
        document.getElementById("longDesc").value = "";
        document.getElementById("contactInfo").value = "";
    })

    let overButton = document.getElementById("toOverview");
    overButton.addEventListener("click", () => {
        location.href = 'offerOverview.html?userID=' + urlParams.get('userID') + '&loginVal=' + urlParams.get('loginVal');
    })

    
    setupNav();
}