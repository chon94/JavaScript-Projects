package corbos.memorygame.models;

public class MoveResponse {

    private String message;
    private MoveStatus status;
    private int value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MoveStatus getStatus() {
        return status;
    }

    public void setStatus(MoveStatus status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
