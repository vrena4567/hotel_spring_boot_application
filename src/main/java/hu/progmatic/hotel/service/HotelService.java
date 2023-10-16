package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.repository.GuestRepo;
import hu.progmatic.hotel.repository.ReservationRepo;
import hu.progmatic.hotel.repository.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    private GuestRepo guestRepo;
    private ReservationRepo reservationRepo;
    private RoomRepo roomRepo;

    public HotelService(GuestRepo guestRepo, ReservationRepo reservationRepo, RoomRepo roomRepo) {
        this.guestRepo = guestRepo;
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }

    public List<Integer> getAvailableRooms(){
        List<Reservation> reservations = reservationRepo.findAll();
        List<Integer> reservationsId = new ArrayList<>();
        List<Room> rooms = roomRepo.findAll();
        List<Integer> roomId = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationsId.add(reservation.getRoom().getId());
        }
        for (Room room : rooms) {
            roomId.add(room.getId());
        }
        roomId.removeAll(reservationsId);
        return roomId;
    }
}
