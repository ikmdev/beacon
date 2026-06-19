package dev.ikm.beacon.analyze;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyzeController {

	@GetMapping("/analyze")
	public String getAnalyzePage(Model model) {
		model.addAttribute("pageTitle", "Beacon Analyze");
		model.addAttribute("activePage", "analyze");
		boolean newNotifications = false; // Set to true or false
		model.addAttribute("hasNotifications", newNotifications);
		return "analyze";
	}

}
