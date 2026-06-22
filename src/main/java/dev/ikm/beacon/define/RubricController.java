package dev.ikm.beacon.define;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RubricController {

	// 1. READ (List)
	@GetMapping("/define/rubrics")
	public String listModels(Model model) {
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/rubrics :: list";
	}

	// 2. CREATE (Show Empty Form)
	@GetMapping("/define/rubrics/new")
	public String newModelForm(Model model) {
		// model attribute is null, Thymeleaf uses the boilerplate JSON
		return "fragments/define/rubrics :: editor";
	}

	// 3. UPDATE (Show Populated Form)
	@GetMapping("/define/rubrics/{id}/edit")
	public String editModelForm(@PathVariable Long id, Model model) {
//		model.addAttribute("model", dataModelRepository.findById(id).orElseThrow());
		return "fragments/define/rubrics :: editor";
	}

	// 4. SAVE (Handles both Create and Update)
	@PostMapping("/api/define/rubrics")
	public String saveModel(@ModelAttribute String dataModel, Model model) {
		// If dataModel.getId() is null, Hibernate creates. If not, it updates.
//		dataModelRepository.save(dataModel);

		// After saving, immediately return the updated list view
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/rubrics :: list";
	}

	// 5. DELETE
	@DeleteMapping("/api/define/rubrics/{id}")
	public String deleteModel(@PathVariable Long id, Model model) {
//		dataModelRepository.deleteById(id);

		// After deleting, return the updated list view
//		model.addAttribute("models", dataModelRepository.findAll());
		return "fragments/define/rubrics :: list";
	}
}
