package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.service.GuestService;
import hu.progmatic.hotel.service.ReservationService;
import hu.progmatic.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class HotelController {
    private GuestService guestService;
    private RoomService roomService;
    private ReservationService reservationService;

    public HotelController(GuestService guestService, RoomService roomService, ReservationService reservationService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }
    @GetMapping({"","/"})
    public String getHome(Model model) {
        model.addAttribute("guest", new Guest());
        model.addAttribute("reservation", new Reservation());// új foglalással új embert felveszek
    }
}
