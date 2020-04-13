package corbos.memorygame.controllers;

import corbos.memorygame.models.*;
import corbos.memorygame.service.GameCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class GameController {

    private final GameCache cache = new GameCache();

    @PostMapping("/start")
    public ResponseEntity<GameStartResponse> start() {
        return new ResponseEntity(cache.create(), HttpStatus.CREATED);
    }

    @PostMapping("/start/{rows}/{columns}")
    public ResponseEntity<GameStartResponse> start(@PathVariable int rows, @PathVariable int columns) {
        try {
            return new ResponseEntity(cache.create(rows, columns), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(
                    new GameStartResponse(ex.getMessage()),
                    HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/move")
    public ResponseEntity<MoveResponse> move(@RequestBody MoveRequest request) {

        MoveResponse moveResponse = cache.move(request);

        HttpStatus status = HttpStatus.OK;
        if (moveResponse.getStatus() == MoveStatus.GAME_NOT_FOUND) {
            status = HttpStatus.PRECONDITION_FAILED; // 412       
        } else if (moveResponse.getStatus() == MoveStatus.ERROR) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;// 422
        }

        return new ResponseEntity(moveResponse, status);
    }
}
