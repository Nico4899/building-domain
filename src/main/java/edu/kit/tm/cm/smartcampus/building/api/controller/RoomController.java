package main.java.edu.kit.tm.cm.smartcampus.building.api.controller;

import main.java.edu.kit.tm.cm.smartcampus.building.api.RoomApi;
import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.RoomRequest;
import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.RoomResponse;

public class RoomController implements RoomApi {
    @Override
    public RoomResponse getRooms(String bin) {
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
