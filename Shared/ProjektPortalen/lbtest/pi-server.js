/* eslint-env es6, node */

/**
 * Code by Ross Johnson
 * Introduction to Load Balancing using Node.js - Part 1
 * https://mazira.com/blog/introduction-load-balancing-nodejs
 */
let args = process.argv.splice(2);
let http = require('http');

/**
 * This function estimates pi using Monte-Carlo integration
 * https://en.wikipedia.org/wiki/Monte_Carlo_integration
 * @returns {number}
 */
function estimatePi() {
    let n = 25000000, inside = 0;

    for ( let i = 0; i < n; i++ ) {
        let x = Math.random();
        let y = Math.random();
        if ( Math.sqrt(x * x + y * y) <= 1 )
            inside++;
    }

    return 4 * inside / n;
}

// Create a basic server that responds to any request with the pi estimation
let server = http.createServer((req, res) => {
    res.writeHead(200, {'Content-Type' : 'text/plain'});
    res.end('Pi: ' + estimatePi());
});

// Listen to a specified port, or default to 8000
server.listen(args[0] || 8000);