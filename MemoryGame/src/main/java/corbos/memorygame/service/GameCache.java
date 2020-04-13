package corbos.memorygame.service;

import corbos.memorygame.models.Game;
import corbos.memorygame.models.GameStartResponse;
import corbos.memorygame.models.MoveRequest;
import corbos.memorygame.models.MoveResponse;
import corbos.memorygame.models.MoveStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameCache {

    private static final int RANGE = 4;

    private final Random random = new Random();
    private final Map<Integer, Game> games = new HashMap<>();

    private int nextGameId = 1;

    public GameStartResponse create() {

        int rows = 4 + (random.nextInt(RANGE) * 2);
        int columns = 4 + (random.nextInt(RANGE) * 2);

        return create(rows, columns);
    }

    public GameStartResponse create(int rows, int columns) {

        int gameId = nextGameId++;

        Game g = new Game(rows, columns, gameId);
        games.put(g.getGameId(), g);

        return new GameStartResponse(g);
    }

    public MoveResponse move(MoveRequest request) {
        Game g = games.get(request.getGameId());
        MoveResponse response = new MoveResponse();
        if (g == null) {
            response.setStatus(MoveStatus.GAME_NOT_FOUND);
            response.setMessage(String.format(
                    "Game ID %s not found.",
                    request.getGameId()));
        } else {
            response = g.move(request);
        }
        return response;
    }

}
