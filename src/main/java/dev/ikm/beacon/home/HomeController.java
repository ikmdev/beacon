package dev.ikm.beacon.home;

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
    public String home(Model model) {
        model.addAttribute("pageTitle", "Beacon Home");
        return "index";
    }

    @GetMapping(value = "/partials/status", produces = MediaType.TEXT_HTML_VALUE)
    public String statusPanel(Model model) {
        model.addAttribute("statusMessage", "The MVC shell is live and ready for feature work.");
        model.addAttribute("updatedAt", LocalDateTime.now().format(STATUS_TIMESTAMP));
        return "fragments/status-card :: statusCard";
    }
}

