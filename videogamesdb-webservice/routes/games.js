const express = require('express');
const router = express.Router();
const Game = require('../models/Game');


// fetch all 
router.get('/', async (req, res) => {
  try {
    const games = await Game.find();
    return res.json(games);
  } catch (err) {
    return res.json({ success: 0, msg: err });
  }

});

// specific game
router.get('/:gameId', async (req, res) => {
  try {
    const game = await Game.findById(req.params.gameId);
    return res.json(game);
  } catch (err) {
    return res.json({ success: 0, msg: err });
  }
});

// add a new game
router.post('/', async (req, res) => {
  const game = new Game({
    name: req.body.name,
    description: req.body.description,
    initial_release_year: req.body.initial_release_year,
    image_url: req.body.image_url
  });

  try {
    const savedGame = await game.save();
    return res.send(savedGame);
  }
  catch (err) {
    return res.json({ success: 0, msg: err });
  }
});

// update a game with id
router.put('/:gameId', async (req, res) => {
  try {
    const updatedGame = await Game.updateOne(
      { _id: req.params.gameId },
      {
        $set: {
          name: req.body.name,
          description: req.body.description,
          initial_release_year: req.body.initial_release_year,
          image_url: req.body.image_url
        }
      },
    );

    return res.json(updatedGame);
  } catch (err) {
    return res.json({ success: 0, msg: err });
  }
});

// delete a game with id
router.delete('/:gameId', async (req, res) => {
  try {
    const removedGame = await Game.remove({ _id: req.params.gameId });
    return res.json(removedGame);
  } catch (err) {
    return res.json({ success: 0, msg: err });
  }

});

module.exports = router;