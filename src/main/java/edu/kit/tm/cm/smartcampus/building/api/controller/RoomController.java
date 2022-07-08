package edu.kit.tm.cm.smartcampus.building.api.controller;


import edu.kit.tm.cm.smartcampus.building.api.payload.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.RoomResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.RoomsResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController implements RoomApi {

    @Override
    public RoomsResponse getRooms(String bin) {
        return null;
    }

    @Override
    public RoomResponse addRoom(String bin, RoomRequest roomRequest) {
        return null;
    }

    @Override
    public RoomResponse getRoom(String rin) {
        return null;
    }

    @Override
    public RoomResponse editRoom(String rin, RoomRequest roomRequest) {
        return null;
    }

    @Override
    public void deleteRoom(String rin) {

    }
}
