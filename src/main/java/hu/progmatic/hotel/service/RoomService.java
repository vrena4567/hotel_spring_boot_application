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
    public int getAllRoomCapacity(){
        int allRoomCapacity = 0;
        for (int i = 0; i < getAllRoom().size(); i++) {
            allRoomCapacity += getAllRoom().get(i).getCapacity();
        }
        return allRoomCapacity;
    }
    public Room getRoomById(Integer id){
        return roomRepo.findById(id).orElse(null);
    }

}
