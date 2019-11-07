/*
"Imports"
*/
const http = require('http');
const url = require('url');
const fileSys = require('fs');
const express = require('express');
const cors = require('cors');


/*
The server itself.
Handlers for each type of request.
*/
const server = express();

/*
sets CORS for Server
*/
server.use(cors());
//server.options('*', cors())

// GET for all offers
server.get('/offers', (req, res) => {
    // res.header('Access-Control-Allow-Origin', '*');
    // res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');

    //let testUsers = [new User(123, 'NoName', 's@s.dk', false, 'something', '1234'), [7,8]];
    let testOffers = [new Offer(951, 'Google', 'New Job', 'jobDesc', 'longer JobDisc', 88888888, [3,8])];
    saveOfferList(testOffers);
    res.json(JSON.stringify(testOffers));   
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
    constructor(id, offeringBusiness, title, shortDesc, longDesc, contactInfo, applicants) {
        this.id = id;
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