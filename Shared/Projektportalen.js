/*
"Imports"
*/
const http = require('http');
const url = require('url');






/*
Server logic:
All possible requests has its own handler
*/
let requestHandler = Object.create(null);
requestHandler.GET = (request, response) => {
    //console.log(request);
    response.writeHead(200, {'Content-Type': 'text/plain'});
    response.end('We got a GET request');
}

/*
The server itself:
All input will be refered to the handlers above 
based on the request-method received from the client
*/
let server = http.createServer((request, response) => {
    let test = new User(123, 'NoName', 's@s.dk', false, 'something', '1234');
    console.log(test.name);
    try {
        requestHandler[request.method](request, response);
        
    } catch (error) {
        response.writeHead(405, {'Content-Type': 'text/plain'});
        response.end('Method not allowed!');
    }

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
    
    constructor(id, name, email, buissBool, username, password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.buissBool = buissBool;
        this.username = username;
        this.password = password;
    }

    print() {
        console.log(name + ', ' + email);
    }


}