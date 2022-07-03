package main.java.edu.kit.tm.cm.smartcampus.building.api;

import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.RoomRequest;
import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.RoomResponse;
import main.java.edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public interface RoomApi {

    @GetMapping("/buildings/{bin}/rooms")
    RoomResponse getRooms(@PathVariable String bin);

    @PostMapping("/buildings/{bin}/rooms")
    RoomResponse addRoom(@PathVariable String bin, @RequestBody RoomRequest roomRequest);

    @GetMapping("/rooms/{rin}")
    RoomResponse getRoom(@PathVariable String rin);

    @PutMapping("/rooms/{rin}")
    RoomResponse editRoom(@PathVariable String rin, @RequestBody RoomRequest roomRequest);

    @DeleteMapping("/rooms/{rin}")
    void deleteRoom(@PathVariable String rin);

}