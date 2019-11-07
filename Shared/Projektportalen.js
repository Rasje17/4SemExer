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
sets CORS for Server
*/
server.use(bodyParser.urlencoded({ extended: false }));
server.use(bodyParser.json());
server.use(cors());
server.options('*', cors());

// GET user for LogIn
server.get('/users/:usna', (req, res) => {
    
    let users = getUserList();

    users.forEach(element => {
        if (element.username == req.params.usna) {
            // console.log to check which user we found:
            console.log(element.username);
            
            res.json(JSON.stringify(element));
            res.end();
        }

    res.end();
    });
})

// GET for all offers
server.get('/offers', (req, res) => {
    res.json(JSON.stringify(getOfferList()));   
    res.end();
});

// GET for single offer
server.get('/offer', (req, res) => {

    let offerList = getOfferList();
    offerList.forEach(e => {
        if(e.id == req.query.id) {
            res.json(JSON.stringify(e));
            res.end();
        }
    });
    res.end();
});

// PUT for aadding aplicant to offer
server.put('/offer', (req, res) => {

    let offerList = getOfferList();
    

    offerList.forEach(e => {
        if(e.id == req.query.offerId) {
            e.addApplicant(parseInt(req.query.applicant));
        }
    })
    saveOfferList(offerList);
    
})

// GET for getting entire list of users
server.get('/allusers/', (req, res) => {
    let users = getUserList();

    res.json(JSON.stringify(users));
    res.end();
})

// POST for adding new users
server.post('/allusers', (req, res) => {
    console.log("im here");

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
    console.log("Users saved: " + users);

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
                newList.push(new Offer(element.id, element.offeringBusiness, element.title, element.shortDesc, element.longDesc, element.contactInfo, element.applicants));
            });
            break;
        default:
            console.log('Filename not recognized');
            return null;
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