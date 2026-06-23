package dev.ikm.beacon.web.configure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigureController {

	@GetMapping("/configure")
	public String getConfigurePage(Model model) {
		model.addAttribute("pageTitle", "Beacon Configure");
		model.addAttribute("activePage", "configure");
		boolean newNotifications = false; // Set to true or false
		model.addAttribute("hasNotifications", newNotifications);
		return "configure";
	}
}
