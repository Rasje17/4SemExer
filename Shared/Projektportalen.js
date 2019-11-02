/*
"Imports"
*/
const http = require('http');
const url = require('url');
const fileSys = require('fs');
const express = require('express');


/*
The server itself.
Handlers for each type of request.
*/
const server = express();

server.get('/offers', (req, res) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');

    let testUsers = [new User(123, 'NoName', 's@s.dk', false, 'something', '1234'), new User(321, 'newName', 'e@e.com', false, 'UserName', '987654')];
    let testOffers = [new Offer(951, 'Google', 'New Job', 'jobDesc', 'longer JobDisc', 88888888, testUsers[0])];
    res.json(JSON.stringify(testOffers));
});

//Defines which port to listen to
server.listen(8888);

/*
The server itself:
All input will be refered to the handlers above 
based on the request-method received from the client
*/
//let server = http.createServer((request, response) => {

/* ---- TESTS:
let testUsers = [new User(123, 'NoName', 's@s.dk', false, 'something', '1234'), new User(321, 'newName', 'e@e.com', false, 'UserName', '987654')];
let testOffers = [new Offer(951, 'Google', 'New Job', 'jobDesc', 'longer JobDisc', 88888888, testUsers[0])];
saveUserList(testUsers);
saveOfferList(testOffers);
let outUsers = getUserList();
let outOffers = getOfferList();
console.log(outUsers[0].name + ', ' + outUsers[1].name);
console.log(outOffers[0].offeringBusiness);
*/

//try {
//  requestHandler[request.method](request, response);
//    console.log("we're here!");
//  } catch (error) {
//        response.writeHead(405, { 'Content-Type': 'text/plain' });
//        response.end('You dumbfuck, shits crashed yo');
//    }

//});






/*
Server logic:
All possible requests has its own handler
*/
let requestHandler = Object.create(null);
requestHandler.GET = (request, response) => {


    console.log("We're in GET!");

    response.write(JSON.stringify(getOfferList()), JSON);

    let offerIDs = [];
    getOfferList().forEach(element => {
        offerIDs.push(element.id);
    });

    console.log("We got these IDs: " + offerIDs);

    response.writeHead(200, { 'Content-Type': 'text/json', "Access-Control-Allow-Origin": "*" });

    response.end("Done");
}

/*
More server logic, for handling saving and loading of data
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