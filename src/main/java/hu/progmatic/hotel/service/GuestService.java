package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.repository.GuestRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
