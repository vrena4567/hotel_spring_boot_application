package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String getHomePager() {
        return "home";
    }

    @GetMapping("/reservations")
    public String showReservationList(Model model) {
        model.addAttribute("listReservations", reservationService.getAllReservation());
        model.addAttribute("allRoomCapacity", roomService.getAllRoomCapacity());
        model.addAttribute("bookedPlacesNumber", hotelService.getBookedPlacesNumer());
        model.addAttribute("availableRoomCapacity", hotelService.getAvailableRoomCapacity());
        return "reservations";
    }

    @GetMapping("reservations/new/guest")
    public String showNewFormForGuest(Model model) {
        model.addAttribute("guest", new Guest());
        return "guest_form";
    }

    @PostMapping("reservations/guest/save")
    public String saveGuest(Guest guest) {
        guestService.saveNewGuest(guest);
        return "redirect:/reservations/new";
    }

    @GetMapping("/reservations/new")
    public String showNewForm(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("pageTitle", "Add New Reservation");
        model.addAttribute("reservation", reservation);
        model.addAttribute("latestGuest", guestService.getLatestGuest());
        model.addAttribute("availableRooms", hotelService.getAvailableRooms());
        return "reservation_form";
    }

    @PostMapping("reservations/save")
    public String saveReservation(Reservation reservation, @ModelAttribute("roomId") Integer roomId, RedirectAttributes ra) {
        reservation.setGuest(guestService.getLatestGuest());
        reservation.setRoom(roomService.getRoomById(roomId));
        reservationService.saveNewReservation(reservation);
        ra.addFlashAttribute("message", "The reservation has been saved successfully.");
        return "redirect:/reservations";
    }

    @GetMapping("/reservations/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws ReservationNotFoundException {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("pageTitle", "Edit Reservation (ID: " + id + ")");
        model.addAttribute("reservation", reservation);
        Guest actualGuest = reservation.getGuest();
        model.addAttribute("latestGuest", actualGuest);
        List<Integer> allRooms = hotelService.getAvailableRooms();
        allRooms.add(reservation.getRoom().getId());
        model.addAttribute("availableRooms", allRooms);
        return "reservation_edit_form";
    }

    @PostMapping("reservations/edit/save")
    public String saveEditedReservation(Reservation reservation, @ModelAttribute("roomId") Integer roomId, RedirectAttributes ra,
                                        @ModelAttribute("id") Integer id) throws ReservationNotFoundException {
        try{
            Guest guest = reservationService.getReservationById(id).getGuest();
            reservation.setGuest(guest);
            reservation.setRoom(roomService.getRoomById(roomId));
            reservationService.saveNewReservation(reservation);
            ra.addFlashAttribute("message", "The reservation has been updated successfully.");
        } catch (ReservationNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/reservations";
    }

    @GetMapping("/reservations/delete/{id}")
    public String deleteReservation(@PathVariable("id") Integer id, RedirectAttributes ra) throws ReservationNotFoundException {
        try {
            reservationService.deleteById(id);
            ra.addFlashAttribute("message", "The reservation (ID: " + id + ") was deleted.");
        } catch (ReservationNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/reservations";
    }
}
