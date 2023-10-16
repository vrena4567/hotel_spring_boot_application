package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.model.Room;
import hu.progmatic.hotel.service.RoomNotFoundException;
import hu.progmatic.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String showRoomList(Model model) {
        model.addAttribute("listRooms", roomService.getAllRoom());
        return "rooms";
    }

    @GetMapping("/room/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) throws RoomNotFoundException {
        Room room= roomService.getRoomByID(id);
        model.addAttribute("room", room);
        return "room_edit_form";
    }

    @PostMapping("/room/edit/save")
    public String saveGuest(Room room, RedirectAttributes ra) {
        roomService.updateRoom(room);
        ra.addFlashAttribute("message", "The guest has been updated successfully.");
        return "redirect:/rooms";
    }

}
