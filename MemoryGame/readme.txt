Memory Game

Memory Game is a card matching game. Cards are shuffled and arranged face-down 
in a grid. Players take turns choosing two cards. If the card values match, 
the player earns a point. If they don't, the cards are returned to the grid and
the next player tries. Players use the information about cards they've seen to make
better guesses. The game is over when there are no unmatched cards remaining. 
The player with the most matches wins.

The Memory Game API has three endpoints:

POST /start
====================

No request body.

Initializes a new random Memory Game and returns the game's identifier 
and dimensions as JSON:

{
    "gameId": 11,
    "rows": 6,
    "columns": 4
}

POST /start/{rows}/{columns}
====================

No request body.

Path variables include number of rows and number of columns.

This is a useful endpoint for testing. It allows you to control the size of the
Memory Game grid. Initializes a new Memory Game with specific dimensions 
and returns the game's identifier and dimensions as JSON:

{
    "gameId": 12,
    "rows": 2,
    "columns": 2
}

You are required to call /start before playing.

/start endpoints return one of two HTTP status codes:
201 CREATED -- successfully created a game (see JSON response above).
409 CONFLICT -- rows or columns are less to or equal to zero
    or row * columns will result in an odd number of cards (can't be matched).

PUT /move
====================

Expects a JSON message in the request body:

{
    "gameId": 11,
    "row": 2,
    "column": 3
}

row and column identify the card to be selected. Row and column are 0-based. 
If a row or column is outside the grid, it is an error.

Always returns a JSON response:

{
    "message": "A message describing the outcome or null.",
    "status": SEE BELOW,
    "value": 3 // 0-31, 
       // the value of the selected card if the status is not ERROR or GAME_OVER
}

Statuses include:

GAME_NOT_FOUND -- the gameId in the request was not found. Correct and try again.
ERROR -- Something is wrong with the request. Correct and try again.
NEXT -- Successfully selected the first card. Ready for the next selection.
MATCH -- The second selection matches. Score a point for the current player.
NO_MATCH -- The second selection does not match. Return the cards to the grid. 
    Next player's turn.
GAME_OVER -- The current game is over. Let the players know.

In addition, /move my return one of three HTTP status codes:

412 Precondition Failed -- The game isn't initialized or doesn't exist. 
    Call /start first.
422 Unprocessable Entity -- The move is off the board, is a duplicate, or the card
    has already been matched. Try again.
200 OK -- The move succeeded.

Write an HTML, CSS, and JavaScript front-end for the Memory Game. It should:
- Keep track of the number of players (at least 2).
    Remember whose turn it is.
    Track scores.
- Initialize the game by calling /start and then display a grid of "cards" in the
    correct dimensions. DO NOT hard-code the size. 
    Each time a game is initialized, the dimensions are different.
- It IS okay to call /start/{row}/{columns} for testing, but your final product
    must use /start.
- Allow players to select a card.
- If the selection is valid, show players the result. 
    If it's the first card, show its value. 
    If it's the second, show its value and indicate if it's a match, no
      match, or if the game is over.
- If the selection is invalid, warn the player and allow them to select again.
- You must decide how many players are playing. The API doesn't care.
- When the game is over, indicate it visually and don't allow players to continue
    to interact with the "board".
- Allow players to start over at any time. 
    This may result in a different sized grid.

Tips:
- Use Postman to initialize and play through a game. Note the HTTP status codes
    and JSON statuses returned.
- Wireframes are intentionally not included. That means the UI starts and ends with
    you. Make a plan. Prototype on paper. You'll save yourself a bunch of trouble 
    if you know how each component will transition and interact.