package dev.ikm.beacon.web.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigurationController {

	@GetMapping("/configuration")
	public String getConfigurationPage(Model model) {
		model.addAttribute("pageTitle", "Beacon Configuration");
		model.addAttribute("activePage", "configuration");
		boolean newNotifications = false; // Set to true or false
		model.addAttribute("hasNotifications", newNotifications);
		return "configuration";
	}
}
