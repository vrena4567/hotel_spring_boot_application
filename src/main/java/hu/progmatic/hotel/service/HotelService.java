package hu.progmatic.hotel.service;

import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.repository.GuestRepo;
import hu.progmatic.hotel.repository.ReservationRepo;
import hu.progmatic.hotel.repository.RoomRepo;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.temporal.ChronoUnit;
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

    public Integer getAvailableRoomCapacity(){
        List<Integer> availableRooms = getAvailableRooms();
        Integer capacity = 0;
        for (Integer id: availableRooms) {
            for (Room room: roomRepo.findAll()) {
                if(room.getId() == id){
                    capacity += room.getCapacity();
                }
            }
        }
        return capacity;
    }
    public Integer getBookedPlacesNumer(){
        List<Reservation> reservations = reservationRepo.findAll();
        Integer bookedPlaces = 0;
        for (Reservation currentReservation : reservations) {
            bookedPlaces += currentReservation.getRoom().getCapacity();
        }
        return bookedPlaces;
    }
}
