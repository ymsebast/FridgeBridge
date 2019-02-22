// fs is a core Node package for reading and writing files
var fs = require("fs");

// This block of code will read from the "food.txt" file.
// The code will store the contents of the reading inside the variable "data"
fs.readFile("food.txt", "utf8", function (error, data) {

    // If the code experiences any errors it will log the error to the console.
    if (error) {
        return console.log(error);
    }

    // We will then print the contents of data
    console.log(data);

    // Then split it by commas (to make it more readable)
    var dataArr = data.split(",");

    // We will then re-display the content as an array for later use.
    console.log(dataArr);

    var firebase = require("firebase");
    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyCBdrmu0Zh7QJ4dl4IV9HCZNCH4y7QP9Lw",
        authDomain: "test-43928.firebaseapp.com",
        databaseURL: "https://test-43928.firebaseio.com",
        projectId: "test-43928",
        storageBucket: "test-43928.appspot.com",
        messagingSenderId: "668914477501"
    };
    firebase.initializeApp(config);

    // Include the request npm package (Don't forget to run "npm install request")
    var request = require("request");

    // Then run a request to the USDA Nutritional Facts API 
    var queryURL = "https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=JrdeqppR6h6iZFOFYmbUU6ECER95OYP0wl73znKp&fg=0900&nutrients=208&nutrients=205&nutrients=204&nutrients=203&max=1500";

    request(queryURL, function (error, response, body) {

        // If the request is successful (i.e. if the response status code is 200)
        if (!error && response.statusCode === 200) {

            var obj = JSON.parse(body)
            // console.log(obj.report.foods[0].name);

            // compare with txt file array
            for (var i = 0; i < dataArr.length; i++) {
                var txtfood = dataArr[i].toLowerCase();
                // console.log(txtfood);
                for (var j = 0; j < obj.report.foods.length; j++) {
                    var apifood = obj.report.foods[j].name.toLowerCase();
                    // console.log(apifood);
                    // if (apifood.includes(txtfood) || apifood.includes(txtfood + ",")) {
                    if (apifood.includes(txtfood + ",") && apifood.includes(", raw")) {
                        console.log(obj.report.foods[j].name);
                        console.log(obj.report.foods[j].nutrients[0]);
                        console.log(obj.report.foods[j].nutrients[1]);
                        console.log(obj.report.foods[j].nutrients[2]);
                        console.log(obj.report.foods[j].nutrients[3])



                        // set database
                        var database = firebase.database();

                        // Clear database for testing
                        database.ref("Food").remove();

                        // Send data to firebase
                        database.ref("Food").push({
                            food: obj.report.foods[j].name,
                            kcal: obj.report.foods[j].nutrients[0].gm + " " + obj.report.foods[j].nutrients[0].unit + " /100g",
                            fats: obj.report.foods[j].nutrients[2].gm + obj.report.foods[j].nutrients[2].unit + " " + obj.report.foods[j].nutrients[2].nutrient + " /100g",
                            carbs: obj.report.foods[j].nutrients[3].gm + obj.report.foods[j].nutrients[3].unit + " " + obj.report.foods[j].nutrients[3].nutrient + " /100g",
                            protein: obj.report.foods[j].nutrients[1].gm + obj.report.foods[j].nutrients[1].unit + " " + obj.report.foods[j].nutrients[1].nutrient + " /100g"

                        });
                        // database.ref("Food").remove();

                    }
                }
            }
        }
    });




});
