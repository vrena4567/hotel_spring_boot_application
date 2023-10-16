package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.repository.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private ReservationRepo reservationRepo;

    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }


    public List<Reservation> getAllReservation(){
        return reservationRepo.findAll();
    }

    public void saveNewReservation(Reservation reservation){
        reservationRepo.save(reservation);
    }

    public Reservation getReservationById(Integer id) throws ReservationNotFoundException{
        Optional<Reservation> result = reservationRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ReservationNotFoundException("Could not find any reservations with ID " + id);
    }
    public void deleteById(Integer id) throws ReservationNotFoundException {
        Integer count = reservationRepo.countById(id);
        if(count == null || count == 0){
            throw new ReservationNotFoundException("Could not find any reservations with ID " + id);
        }
        reservationRepo.deleteById(id);
    }
}
