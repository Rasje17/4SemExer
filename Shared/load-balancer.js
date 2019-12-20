/* eslint-env es6, node */

let args = process.argv.splice(2); 
let http      = require('http'); //Import library http for creating server
let httpProxy = require('http-proxy'); //Import library http-proxy for creating proxy server

//Targets for the proxy server to redirect to
let destinations = [
    { target: 'http://localhost:8001' },
    { target: 'http://localhost:8002' },
    { target: 'http://localhost:8003' }
];

let i = 0;
let proxy = httpProxy.createProxyServer({}); //Create proxy-server

//Create the server to handle requests and response to and from the destinations listed above, through proxy server
http.createServer((req, res) => {
    proxy.web(req, res, destinations[i]);
    i = (i + 1) % destination.length;
}).listen(args[0] || 8000); //Tells the server to listen on the argument input in the cmd terminal or to listen on port 8000 if nothing is given in terminal 