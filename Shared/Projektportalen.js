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

// Class seperation provided by PACHR16 during handin2. This is not done in collaboration but I needed the classes seperate for fixing an issue with classes not being defined
const User = require('./User.js');
const Offer = require('./Offer');

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
let users = loadData('./users.json');
let offerList = loadData('./offers.json');

// GET user for LogIn
server.get('/users/:usna', (req, res) => {

    let currentUser = users.find(x => x.username == req.param.usna);
    res.json(JSON.stringify(currentUser));

    res.end();
})

server.get('/loginuser', (req, res) => {
    let currentUser = users.find(x => x.username == req.query.username);
    res.json(JSON.stringify(currentUser));

    res.end();
})

// GET for specific user ID
server.get('/users', (req, res) => {
    let requestedUser = users.find(x => x.userID == req.query.userID);
    res.json(JSON.stringify(requestedUser));

    res.end();
})


// GET for all offers
server.get('/offers', (req, res) => {
    res.json(JSON.stringify(offerList));

    res.end();
});

// GET for single offer
server.get('/offer', (req, res) => {
    let offer = offerList.find(x => x.id == req.query.id);
    res.json(JSON.stringify(offer));

    res.end();
});

// DELETE for removing an offer
server.delete('/offer', (req, res) => {

    let removeIndex = offerList.findIndex(x => x.id == req.query.offerId)
    offerLsit.splice(removeIndex, 1);

    saveOfferList(offerList);

    console.log("we have deleted!");

    res.end();
})

// PUT for adding aplicant to offer
server.put('/offer', (req, res) => {

    let currentOffer = offerList.find(x => x.offerId == req.query.offerId);
    currentOffer.addApplicant(parseInt(req.query.applicant));

    saveOfferList(offerList);
    
    res.end();
})

// GET for getting entire list of users
// ALROL17: This is for testing only and should not be released with the complete system as it allows for data leak
server.get('/allusers', (req, res) => {
    res.json(JSON.stringify(users));
    res.end();
})

// POST for adding new users
server.post('/allusers', (req, res) => {

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
    return loadData('./users.json');
}

function getOfferList() {
    return loadData('./offers.json');
}

function saveUserList(businessObjectList) {
    return saveData(businessObjectList, './users.json');
}

function saveOfferList(businessObjectList) {
    return saveData(businessObjectList, './offers.json');
}

function saveData(businessObjectList, fileName) {
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
    let businessObjectList = JSON.parse(fileSys.readFileSync(fileName));
    let newList = [];
    switch (fileName) {
        case './users.json':
            businessObjectList.forEach(element => {
                newList.push(new User(element.id, element.name, element.email, element.busBool, element.username, element.password));
            });
            break;
        case './offers.json':
            businessObjectList.forEach(element => {
                newList.push(new Offer(element.id, element.ownerID, element.offeringBusiness, element.title, element.shortDesc, element.longDesc, element.contactInfo, element.applicants));
            });
            break;
        default:
            console.log('Filename not recognized');
            return newList;
    }
    return newList;
}