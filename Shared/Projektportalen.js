/*
"Imports"
*/
const http = require('http');
const url = require('url');
const fileSys = require('fs');






/*
Server logic:
All possible requests has its own handler
*/
let requestHandler = Object.create(null);
requestHandler.GET = (request, response) => {
    //console.log(request);
    response.writeHead(200, { 'Content-Type': 'text/plain' });
    response.end('We got a GET request');
}

function saveData(businessObjectList, fileName) {
    let dataToSave = JSON.stringify(businessObjectList);

    try {
        fileSys.writeFileSync('./data.json', dataToSave);    
    } catch (error) {
        console.log('File does not exist...')
        return false;
    }
    
    return true;
}



/* let saveData = (dataToSave) => {
    let newData = JSON.stringify(dataToSave);
    //Possibly change text to application
    //const textToBLOB = new Blob([data], {type: 'text/json'});
    //const saveFile = './saveData.json';

    let existingData = "";
    try {
        existingData = fileSys.readFileSync('./data.json');

    } catch (error) {
        console.log("File does not exist");
    }


    fileSys.writeFileSync('./data.json', [newData, existingData]);

} */

function loadData(fileName) {
    let businessObjectList = JSON.parse(fileSys.readFileSync(fileName));
    let newList = [];
    businessObjectList.forEach(element => {
        newList = new User(id, name, email, busBool, username, password);

    });
    return businessObjectList;
}

/* let loadData = (fileName, ID) => {
    let plaintext = "";
    plaintext = JSON.parse(fileSys.readFileSync(fileName));

    //console.log("File does not exist/Can't parse");
    console.log(plaintext);
} */

//Testmethod may delete later(?)
let purgeFile = (saveFile) => {
    fileSys.writeFileSync(saveFile, "");
}

/*
The server itself:
All input will be refered to the handlers above 
based on the request-method received from the client
*/
let server = http.createServer((request, response) => {
    let test = [new User(123, 'NoName', 's@s.dk', false, 'something', '1234'), new User(321, 'newName', 'e@e.com', false, 'UserName', '987654')];
    console.log(test[0].name);
    console.log(test[1].name);
    try {
        requestHandler[request.method](request, response);

    } catch (error) {
        response.writeHead(405, { 'Content-Type': 'text/plain' });
        response.end('Method not allowed!');
    }
    loadData('./data.json', 123);
    console.log
    //purgeFile('./data.json');
    //saveData(test);
});
/*
Defines which port to listen to
*/
server.listen(8888);

/*
Business Objecs:
ToDo (Description)...
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

    print() {
        console.log(name + ', ' + email);
    }


}