package corbos.memorygame.models;

public class GameStartResponse {

    private static final int INVALID_VALUE = -1;
    private final Game game;
    private final String message;

    public GameStartResponse(Game g) {
        game = g;
        message = "game created";
    }

    public GameStartResponse(String message) {
        this.message = message;
        game = null;
    }

    public int getRows() {
        if (game == null) {
            return INVALID_VALUE;
        }
        return game.getRows();
    }

    public int getColumns() {
        if (game == null) {
            return INVALID_VALUE;
        }
        return game.getColumns();
    }

    public int getGameId() {
        if (game == null) {
            return INVALID_VALUE;
        }
        return game.getGameId();
    }

    public String getMessage() {
        return message;
    }

}
