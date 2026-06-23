package dev.ikm.beacon.web.evaluate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvaluateController {

	@GetMapping("/evaluate")
	public String getEvaluatePage(Model model) {
		model.addAttribute("pageTitle", "Beacon Evaluate");
		model.addAttribute("activePage", "evaluate");
		boolean newNotifications = false; // Set to true or false
		model.addAttribute("hasNotifications", newNotifications);
		return "evaluate";
	}

}
