package dev.ikm.beacon.web.home;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final DateTimeFormatter STATUS_TIMESTAMP = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a");

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("pageTitle", "Beacon Home");
        model.addAttribute("activePage", "home");
        boolean newNotifications = false; // Set to true or false
        model.addAttribute("hasNotifications", newNotifications);
        return "home";
    }
}

