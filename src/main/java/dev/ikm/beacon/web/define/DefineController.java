package dev.ikm.beacon.web.define;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/define")
public class DefineController {

	@GetMapping()
	public String getDefinePage(Model model) {
		model.addAttribute("pageTitle", "Beacon Define");
		model.addAttribute("activePage", "define");
		boolean newNotifications = false; // Set to true or false
		model.addAttribute("hasNotifications", newNotifications);
		return "define";
	}
}
