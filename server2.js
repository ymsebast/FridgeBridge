
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

// HomePage Route
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
    // return results.json
    return res.json(results);
  


  });
});

// Starts the server to begin listening
// =============================================================
app.listen(PORT, function () {
  console.log("App listening on PORTS " + PORT);
});


