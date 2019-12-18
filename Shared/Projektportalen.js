//NB All changes made in hand-in 2 has been commented and tagged with "ALROL17" for better overview

/*
"Imports"
*/
const http = require('http');
const url = require('url');
const fileSys = require('fs');
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');


/*
The server itself.
Handlers for each type of request.
*/
const server = express();

/*
sets CORS and bodyparser (used for handling POST request-body with express) for Server
*/
server.use(bodyParser.urlencoded({ extended: false }));
server.use(bodyParser.json());
server.use(cors());
server.options('*', cors());


// ALROL17: Locally storing all users and offers
let users;
let offerList;

// ALROL17: Initial loading of both lists
prepareLists();

function prepareLists() {
    console.log("I'm here");
    if(users == null) {
        console.log("user if true");
        loadUserList();
    }
    if(offerList == null) {
        console.log("offer if true");
        loadOfferList();
        console.log(offerList);
    }
}

// ALROL17: Update function for the offerlist in case of changes to the list while storing locally
// Not really necessary but allows for better readability
function UpdateOfferList() {
    offerList = loadOfferList();
}

// ALROL17: method for correctly querying the lists via get-request
function loadUserList() {
    console.log("loadUserList");
    //loadData('./users.json');
    /*
    server.get('/users', (req, res) => {
        users = getUserList();
        //res.json(JSON.stringify(users));
        console.log("users = " + users);
        res.end();
    })
    */
}

function loadOfferList() {
    loadData('./offers.json');
    /*
    server.get('/offers', (req, res) => {
        offerList = getOfferList();
        res.json(JSON.stringify(offerList)); 
        res.end();
    });
    */
}


// GET user for LogIn
//ALROL17: Replaced forEach with findIndex()
server.get('/users/:usna', (req, res) => {
    console.log("login user 1");

    let userIndex = users.findIndex(x => x.username == req.param.usna);
    console.log(userIndex);

    res.json(JSON.stringify(users[userIndex]));
    /*
    users.forEach(element => {
        if (element.username == req.params.usna) {
            // console.log to check which user we found:
            console.log(element.username);
            
            res.json(JSON.stringify(element));
        }

    
    });
    */

    res.end();
})

server.get('/loginuser', (req, res) => {
    console.log("login user 2");

    let userIndex = users.find(x => x.username == req.query.username);
    console.log("userIndex = " + userIndex);

    /*
    users.forEach(e => {
        if (e.username == req.query.username) {
            someObj = JSON.stringify(e);
            console.log(e);
        }
    });
    */

    res.json(JSON.stringify(userIndex));

    res.end();
})

// GET for specific user ID
server.get('/users', (req, res) => {
    console.log("specific user)");

    let userIndex = users.find(x => x.userID == req.query.userID);
    console.log("userIndex = " + userIndex);

    res.json(JSON.stringify(userIndex));

    /*
    users.forEach(element => {
        if (element.id == req.query.userID) {
            res.json(JSON.stringify(element));
        }
    });
    */

    res.end();
})


// GET for all offers
server.get('/offers', (req, res) => {
    console.log("all offers");

    res.json(JSON.stringify(offerList));
    console.log("all offers called");
    res.end();
});

// GET for single offer
server.get('/offer', (req, res) => {
    console.log("single offer");

    let offer = offerList.find(x => x.id == req.query.id);
    res.json(JSON.stringify(offer));
    /*
    offerList.forEach(e => {
        if(e.id == req.query.id) {
            res.json(JSON.stringify(e));
        }
    });
    */

    res.end();
});

// DELETE for removing an offer
server.delete('/offer', (req, res) => {
    console.log("delete offer");

    offers.forEach(element => {
        if (element.id == req.query.offerId) {
            offers.splice(offers.indexOf(element), 1);
        }
    });

    saveOfferList(offers);

    console.log("we have deleted!");

    // ALROL17: Update locally stored offerlist
    UpdateOfferList();

    res.end();
})

// PUT for adding aplicant to offer
server.put('/offer', (req, res) => {
    console.log("add applicant to offer");

    offerList.forEach(e => {
        if(e.id == req.query.offerId) {
            e.addApplicant(parseInt(req.query.applicant));
        }
    })
    saveOfferList(offerList);

    //ALROL17: Update locally stored (serverside) offerlist
    UpdateOfferList();
    
    res.end();
})

// GET for getting entire list of users
server.get('/allusers/', (req, res) => {
    console.log("get all users");
    console.log(users);
    res.json(JSON.stringify(users));
    res.end();
})

// POST for adding new users
server.post('/allusers', (req, res) => {
    console.log("add new user");

    let rName = req.body.name;
    let rMail = req.body.email;
    let rBusBool = req.body.busBool;
    let rUserName = req.body.username;
    let rPassword = req.body.password;

    let users = getUserList();
    let nextID = users.length;
    
    let newUser = new User(nextID, rName, rMail, rBusBool, rUserName, rPassword);

    users.push(newUser);
    saveUserList(users);

    res.end();
})

// POST for creating new Offers
server.post('/createOffer', (req, res) => {
    console.log("create new offer");

    let rOwnerID = req.body.ownerID;
    let rOfferingBusiness = req.body.offeringBusiness;
    let rTitle = req.body.title;
    let rShortDesc = req.body.shortDesc;
    let rLongDesc = req.body.longDesc;
    let rContactInfo = req.body.contactInfo;

    let offers = getOfferList();
    let nextID = offers[offers.length-1].id+1;
    
    let newOffer = new Offer(nextID, rOwnerID, rOfferingBusiness, rTitle, rShortDesc, rLongDesc, rContactInfo, []);

    offers.push(newOffer);
    saveOfferList(offers);

    console.log("OFFERS SAVED /createOffer POST");

    res.end();
})

//Defines which port to listen to
server.listen(8888);



/*
functions for loading and saving data
*/
function getUserList() {
    console.log("getUserList");

    return loadData('./users.json');
}

function getOfferList() {
    console.log("getOfferList");

    return loadData('./offers.json');
}

function saveUserList(businessObjectList) {
    console.log("saveUserList");

    return saveData(businessObjectList, './users.json');
}

function saveOfferList(businessObjectList) {
    console.log("saveOfferList");

    return saveData(businessObjectList, './offers.json');
}

function saveData(businessObjectList, fileName) {
    console.log("saveData");

    let dataToSave = JSON.stringify(businessObjectList);

    try {
        fileSys.writeFileSync(fileName, dataToSave);
    } catch (error) {
        console.log('File does not exist...');
        return false;
    }
    return true;
}

function loadData(fileName) {
    console.log("loadData");

    let businessObjectList = JSON.parse(fileSys.readFileSync(fileName));
    let newList = [];
    switch (fileName) {
        case './users.json':
            console.log("user.json");

            businessObjectList.forEach(element => {
                newList.push(new User(element.id, element.name, element.email, element.busBool, element.username, element.password));
                console.log(element.name);
            });
            break;
        case './offers.json':
            console.log("offers.json");

            businessObjectList.forEach(element => {
                newList.push(new Offer(element.id, element.ownerID, element.offeringBusiness, element.title, element.shortDesc, element.longDesc, element.contactInfo, element.applicants));
                console.log(element.title);
            });
            break;
        default:
            console.log('Filename not recognized');
            return newList;
    }
    return newList;
}

/*
Business Objects:
Defines the objects that has to be saved and manipulated with business logic.
*/
class Offer {
    constructor(id, ownerID, offeringBusiness, title, shortDesc, longDesc, contactInfo, applicants) {
        this.id = id;
        this.ownerID = ownerID;
        this.offeringBusiness = offeringBusiness;
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.contactInfo = contactInfo;
        this.applicants = applicants;
    }

    addApplicant(applicantId) {
        if(!(this.applicants.includes(applicantId))) {
            this.applicants.push(applicantId);  
        }
    }
}

class User {
    constructor(id, name, email, busBool, username, password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.busBool = busBool;
        this.username = username;
        this.password = password;
    }
}