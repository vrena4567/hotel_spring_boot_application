package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.repository.GuestRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    private GuestRepo guestRepo;

    public GuestService(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    public List<Guest> getAllGuest(){
        return guestRepo.findAll();
    }

    public Guest saveNewGuest(Guest guest){
        return guestRepo.save(guest);
    }
    public Guest getLatestGuest(){
        return getAllGuest().get(getAllGuest().size()-1);
    }
    public Guest getGuestByID(Integer id) throws GuestNotFoundException{
        Optional<Guest> result = guestRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new GuestNotFoundException("Could not find any guests with ID " + id);
    }
    @Transactional
    public void deleteById(Integer id) throws GuestNotFoundException {
        Integer count = guestRepo.countById(id);
        if(count == null || count == 0){
            throw new GuestNotFoundException("Could not find any reservations with ID " + id);
        }
        guestRepo.deleteById(id);
    }
}
