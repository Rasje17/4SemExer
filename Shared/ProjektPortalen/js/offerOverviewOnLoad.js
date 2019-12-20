window.onload = () => {
    let userId = (urlParams.get('userID'));
    let loginValue = (urlParams.get('loginVal'));

    fetch('http://localhost:8888/offers')
        .then(response => response.json())
        .then(json => convertToCards(json));

    function convertToCards(json) {
        let offers = JSON.parse(json);
        offers.forEach(element => {
            createCard(element);
        });


    }

    function createCard(ob) {
        let card = document.createElement("div");
        card.setAttribute("id", ob.id);
        card.className = "card";
        card.addEventListener("click", () => {
            if (urlParams.has('loginVal')) {
                location.href = 'offerPage.html?offerId=' + ob.id + '&userID=' + userId + '&loginVal=' + loginValue;
            }
            else {
                location.href = 'offerPage.html?offerId=' + ob.id;
            }
        })

        let container = document.createElement("div");
        container.className = "container";

        let title = document.createElement("h3");
        title.textContent = ob.title;
        container.appendChild(title);

        let partner = document.createElement("p");
        partner.textContent = "Samarbejdspartner: " + ob.offeringBusiness;
        container.appendChild(partner);

        let shDisc = document.createElement("p");
        shDisc.textContent = ob.shortDesc;
        container.appendChild(shDisc);



        card.appendChild(container);
        document.getElementById("offerlist").appendChild(card);
    }

    setupNav();
}