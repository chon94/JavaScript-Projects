var cardFront = "<img src='https://gamepedia.cursecdn.com/hearthstone_gamepedia/thumb/d/de/Card_back-Suramar.png/200px-Card_back-Suramar.png?version=4a4480e3fbe47b274657a23fbc599118'/> ";
var gameId;
var matches;
// var matchesTwo;
var tries;
var cardOne;
var anotherFlipped;
var matchesToWin;
// var isPlayer1;
// var player1Score;
// var player2Score;


$('#btnStart').click(function () {
    $.ajax({
        url: 'http://localhost:8080/start',
        type: 'POST',
        success: function (startGame) {
            gameId = startGame.gameId;
            matchesToWin = (startGame.rows * startGame.columns) / 2;
            restartGame();

            var cardsDiv = $('#cards');
            cardsDiv.empty();
            var html = '';

            for (var i = 0; i < startGame.rows; i++) {
                html += '<div class="row">';
                for (var j = 0; j < startGame.columns; j++) {
                    html += `<div id="memory-card-${i}-${j}"  data-row="${i}" data-col="${j}" class="col-md card memory-card">
                    ${cardFront}
                    </div>`;
                };
                html += '</div>';
            };
            cardsDiv.append(html);
        }
    });
});

$("#cards").on("click", ".memory-card", function () {
    let data = new Object();
    data.row = $(this).data("row");
    data.column = $(this).data("col");
    data.gameId = gameId;
    data = JSON.stringify(data);

    let currentCard = this;

    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/move',
        contentType: "application/json; charset=utf-8",
        data: data,
        success: function (response) {
            currentCard.innerHTML = `<h1>${response.value}</h1>`
            if (cardOne == null) {
                cardOne = currentCard;
                anotherFlipped = true;
            } else {
                matchOrFlip(cardOne, currentCard);
            }
        },
        error: flipError
    });
})

function matchOrFlip(card1, card2) {
    if (card1.innerHTML == card2.innerHTML) {
        matches++;
        $('#matches-count').empty();
        $('#matches-count').append(`Matches : ${matches} / ${matchesToWin}`);

        $('#' + card1.id).css('color', 'green');
        $('#' + card2.id).css('color', 'green');
    } else {
        setTimeout(function () {
            card1.innerHTML = cardFront;
            card2.innerHTML = cardFront;
        }, 300)
    }


    anotherFlipped = false;
    cardOne = null;

    tries++;
    $('#try-count').empty();
    $('#try-count').append(`Tries : ${tries}`);

    if (matches == matchesToWin) {
        $('body').css('color', 'green');
    }


}

function restartGame() {
    anotherFlipped = false;
    cardOne = null;
    matches = 0;
    tries = 0;

    $('#matches-count').empty();
    $('#matches-count').append(`Matches: 0/${matchesToWin}`);

    $('#try-count').empty();
    $('#try-count').append('Tries: 0');
}

function flipError(thisCard) {
    var errormsg;
    if (cardOne == null) {
        errormsg = '<h2>Card is already matched. Select a new card.</h2>';
    } else if (cardOne.value == thisCard.value && cardOne != null) {
        errormsg = '<h2>Error! Please select a different card! </h2>';
    } else if (cardOne.value == thisCard.value) {
        errormsg = '<h2>Unable to flip the same card. Try again. </h2>';
    }

    $('#error').append(errormsg);

    setTimeout(function () {
        $('#error').empty();
    }, 1500);
}