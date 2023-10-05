package hu.progmatic.hotel.controller;

import hu.progmatic.hotel.service.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {
    private GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

}
