package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.repository.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepo roomRepo;

    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }
    public List<Room> getAllRoom(){
        return roomRepo.findAll();
    }
}
