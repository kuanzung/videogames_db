const mongoose = require('mongoose');

// Book Schema
const GameSchema = mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  description: {
    type: String
  },
  initial_release_year: {
    type: Number
  },
  image_url: {
    type: String
  }
});

module.exports = mongoose.model('Game', GameSchema);