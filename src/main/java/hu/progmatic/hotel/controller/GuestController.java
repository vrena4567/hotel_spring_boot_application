package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Guest;
import hu.progmatic.hotel.model.Reservation;
import hu.progmatic.hotel.service.GuestNotFoundException;
import hu.progmatic.hotel.service.GuestService;
import hu.progmatic.hotel.service.ReservationNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GuestController {
    private GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    @GetMapping("/guests")
    public String showGuestList(Model model) {
        model.addAttribute("listGuests", guestService.getAllGuest());
        return "guests";
    }

    @GetMapping("/guest/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) throws GuestNotFoundException {
        Guest guest= guestService.getGuestByID(id);
        model.addAttribute("pageTitle", "Edit Guest (ID: " + id + ")");
        model.addAttribute("guest", guest);
        return "guest_edit_form";
    }
    @PostMapping("/guest/edit/save")
    public String saveGuest(Guest guest, RedirectAttributes ra) {
        guestService.saveNewGuest(guest);
        ra.addFlashAttribute("message", "The guest has been updated successfully.");
        return "redirect:/guests";
    }

    @GetMapping("/guest/delete/{id}")
    public String deleteReservation(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            guestService.deleteById(id);
            ra.addFlashAttribute("message", "The guest (ID: " + id + ") was deleted.");
        } catch (GuestNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/guests";
    }
}
