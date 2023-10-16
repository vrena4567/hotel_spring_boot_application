package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.service.GuestService;
import hu.progmatic.hotel.service.HotelService;
import hu.progmatic.hotel.service.ReservationService;
import hu.progmatic.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HotelController {
    private GuestService guestService;
    private RoomService roomService;
    private ReservationService reservationService;
    private HotelService hotelService;

    public HotelController(GuestService guestService, RoomService roomService, ReservationService reservationService, HotelService hotelService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
    }

    @GetMapping("/home")
    public String getHomePager(){
        return "home";
    }

    @GetMapping("/reservations")
    public String showReservationList(Model model) {
        model.addAttribute("listReservations", reservationService.getAllReservation());
        return "reservations";
    }

    @GetMapping("reservations/new/guest")
    public String showNewFormForGuest(Model model){
        model.addAttribute("guest", new Guest());
        return "guest_form";
    }
    @PostMapping("reservations/guest/save")
    public String saveGuest(Guest guest){
        guestService.saveNewGuest(guest);
        return "redirect:/reservations/new";
    }

    @GetMapping("/reservations/new")
    public String showNewForm(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        model.addAttribute("latestGuest", guestService.getLatestGuest());
        model.addAttribute("availableRooms", hotelService.getAvailableRooms());
        return "reservation_form";
    }

    @PostMapping("reservations/save")
    public String saveReservation(Reservation reservation, @ModelAttribute ("roomId") Integer roomId){
        reservation.setGuest(guestService.getLatestGuest());
        reservation.setRoom(roomService.getRoomById(roomId));
        System.out.println(roomId);
        reservationService.saveNewReservation(reservation);
        return "redirect:/reservations";
    }









}
