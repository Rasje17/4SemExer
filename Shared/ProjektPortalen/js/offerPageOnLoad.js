window.onload = () => {
    let offerId = (urlParams.get('offerId'));
    let userId = (urlParams.get('userID'));
    let ownerID = "";

    function offervalue(data) {
        let receivedOffer = JSON.parse(data);
        console.log(receivedOffer);
        document.getElementById("offerTitle").innerHTML = receivedOffer.title;
        document.getElementById('contactInfo').innerHTML = receivedOffer.contactInfo;
        document.getElementById('longDesc').innerHTML = receivedOffer.longDesc;
        ownerID = receivedOffer.ownerID;
        setupButton();
    }

    fetch('http://localhost:8888/offer?id=' + offerId)
        .then(response => response.json())
        .then(json => offervalue(json));


    function setupButton() {
        let applyBtn = document.getElementById("applyButton");

        if (urlParams.get('loginVal') == 2 && urlParams.get('userID') == ownerID) {
            applyBtn.textContent = "Delete";
            applyBtn.addEventListener("click", () => {
                fetch(('http://localhost:8888/offer?&offerId=' + offerId),
                    { method: 'DELETE' })
                    .then(resp => console.log(resp.status));
                window.location.href = "offerOverview.html?userID=" + userId + "&loginVal=" + urlParams.get('loginVal');
                })
        } else if (urlParams.get('loginVal') == 1) {
            applyBtn.textContent = 'Ansøg';
            applyBtn.addEventListener("click", () => {
                fetch(('http://localhost:8888/offer?applicant=' + userId + '&offerId=' + offerId),
                    { method: 'PUT' })
                    .then(resp => console.log(resp.status));
            })
        } else if (urlParams.get('loginVal') == 2) {
            applyBtn.remove();
        } else {
            applyBtn.textContent = 'Log ind for at ansøge';
            applyBtn.addEventListener("click", () => {
                window.location.href = "login.html?src=" + this_url;
            })
        }
    }
    
    setupNav();
}