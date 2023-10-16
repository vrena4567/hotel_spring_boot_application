package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.repository.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomRepo roomRepo;

    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getAllRoom(){
        return roomRepo.findAll();
    }

    public Room updateRoom(Room room){
        return roomRepo.save(room);
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


    public Room getRoomByID(Integer id) throws RoomNotFoundException {
        Optional<Room> result = roomRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RoomNotFoundException("Could not find any rooms with ID " + id);
    }
}
