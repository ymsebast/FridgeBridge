
// Dependencies
// =============================================================
var express = require("express");
var path = require("path");
var fs = require("fs");

// Sets up the Express App
// =============================================================
var app = express();
var PORT = 3000;

// Sets up the Express app to handle data parsing
app.use(express.urlencoded({ extended: true }));
app.use(express.json());


// Routes
// =============================================================

// HTML Routes
app.get("/", function (req, res) {
  res.sendFile(path.join(__dirname, "index.html"));
});

// API Routes
app.get("/api/results", function (req, res) {
  var myHTML;
  //read JSON file into results
  fs.readFile('./results.json', function read(err, data) {
    if (err) {
      throw err;
    }
    results = JSON.parse(data);
    //get correct result

    // Configure the response to return a status code of 200 (meaning everything went OK), and to be an HTML document
    // res.writeHead(200, { "Content-Type": "text/html" });
    // res.end(results);
    return res.json(results);
    // End the response by sending the client the myHTML string (which gets rendered as an HTML document thanks to the code above)
  


  });
});

// Starts the server to begin listening
// =============================================================
app.listen(PORT, function () {
  console.log("App listening on PORTS " + PORT);
});


