var http = require("http");
var fs = require("fs");

var PORT = 8080;

var server = http.createServer(handleRequest);

// Start our server
server.listen(PORT, function () {
    // Callback triggered when server is successfully listening. Hurray!
    console.log("Server listening on: http://localhost:" + PORT);
});

// Create a function which handles incoming requests and sends responses
function handleRequest(req, res) {

    // Capture the url the request is made to
    var path = req.url;

    // Depending on the URL, display a different HTML file.
    switch (path) {

        case "/":
            return displayJSON(path, req, res);

        default:
            return display404(path, req, res);
    }
}

function displayJSON(url, req, res) {
    var myHTML;
    fs.readFile('./results.json', function read(err, data) {
        if (err) {
            throw err;
        }
        myHTML = data;
        // Configure the response to return a status code of 200 (meaning everything went OK), and to be an HTML document
        res.writeHead(200, { "Content-Type": "text/html" });

        // End the response by sending the client the myHTML string (which gets rendered as an HTML document thanks to the code above)
        res.end(myHTML);
    });
}
