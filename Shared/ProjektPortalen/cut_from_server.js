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
// let requestHandler = Object.create(null);
// requestHandler.GET = (request, response) => {


//     console.log("We're in GET!");

//     response.write(JSON.stringify(getOfferList()), JSON);

//     let offerIDs = [];
//     getOfferList().forEach(element => {
//         offerIDs.push(element.id);
//     });

//     console.log("We got these IDs: " + offerIDs);

//     response.writeHead(200, { 'Content-Type': 'text/json', "Access-Control-Allow-Origin": "*" });

//     response.end("Done");
// }

/*
More server logic, for handling saving and loading of data
*/