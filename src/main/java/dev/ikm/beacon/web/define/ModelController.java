package dev.ikm.beacon.web.define;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelController {

	// 1. READ (List)
	@GetMapping("/define/models")
	public String listModels(Model model) {
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/models :: list";
	}

	// 2. CREATE (Show Empty Form)
	@GetMapping("/define/models/new")
	public String newModelForm(Model model) {
		// model attribute is null, Thymeleaf uses the boilerplate JSON
		return "fragments/define/models :: editor";
	}

	// 3. UPDATE (Show Populated Form)
	@GetMapping("/define/models/{id}/edit")
	public String editModelForm(@PathVariable Long id, Model model) {
//		model.addAttribute("model", dataModelRepository.findById(id).orElseThrow());
		return "fragments/define/models :: editor";
	}

	// 4. SAVE (Handles both Create and Update)
	@PostMapping("/api/define/models")
	public String saveModel(@ModelAttribute String dataModel, Model model) {
		// If dataModel.getId() is null, Hibernate creates. If not, it updates.
//		dataModelRepository.save(dataModel);

		// After saving, immediately return the updated list view
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/models :: list";
	}

	// 5. DELETE
	@DeleteMapping("/api/define/models/{id}")
	public String deleteModel(@PathVariable Long id, Model model) {
//		dataModelRepository.deleteById(id);

		// After deleting, return the updated list view
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/models :: list";
	}
}
