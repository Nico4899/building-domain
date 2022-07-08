package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.payload.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.RoomResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.RoomsResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface RoomApi {

    @GetMapping("/buildings/{bin}/rooms")
    RoomsResponse getRooms(@PathVariable String bin);

    @PostMapping("/buildings/{bin}/rooms")
    RoomResponse addRoom(@PathVariable String bin, @RequestBody RoomRequest roomRequest);

    @GetMapping("/rooms/{rin}")
    RoomResponse getRoom(@PathVariable String rin);

    @PutMapping("/rooms/{rin}")
    RoomResponse editRoom(@PathVariable String rin, @RequestBody RoomRequest roomRequest);

    @DeleteMapping("/rooms/{rin}")
    void deleteRoom(@PathVariable String rin);

}