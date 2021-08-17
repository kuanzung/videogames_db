const express = require("express");
const app = express();
const mongoose = require("mongoose");
const path = require("path");
const cors = require("cors");

const config = require("./config/database");

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// cors middleware
app.use(cors());

// set static folder
app.use(express.static(path.join(__dirname, "public")));

// import routes
const gamesRoute = require("./routes/games");

app.use("/api/games", gamesRoute);

// routes
app.get("/", (req, res) => {
  res.end("./public/index.html");
});

// connect to mongodb
mongoose.connect(
  config.database,
  { useNewUrlParser: true, useUnifiedTopology: true },
  () => console.log("connected to mongodb")
);

mongoose.connection.on("error", (err) => {
  console.log("database error: " + err);
});

const port = process.env.PORT || 3000; // if port number null, set port to 8080

app.listen(port);
global.console.log(`Service available at: http://192.168.0.104:${port}/...`);
