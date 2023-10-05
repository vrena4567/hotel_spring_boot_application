package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.service.GuestService;
import hu.progmatic.hotel.service.ReservationService;
import hu.progmatic.hotel.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Rest {
    private GuestService guestService;
    private RoomService roomService;
    private ReservationService reservationService;

    public Rest(GuestService guestService, RoomService roomService, ReservationService reservationService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/guests")
    public List<Guest> getAllGuestPage(){
        return guestService.getAllGuest();
    }
    @GetMapping("/rooms")
    public List<Room> getAllRoomPage(){
        return roomService.getAllRoom();
    }
    @GetMapping("/reservations")
    public List<Reservation> getAllReservationPage(){
        return reservationService.getAllReservation();
    }
}
