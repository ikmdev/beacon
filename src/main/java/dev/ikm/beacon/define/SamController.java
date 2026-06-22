package dev.ikm.beacon.define;

import dev.ikm.beacon.shared.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class SamController {

	private final List<SamRecord> samRecords = createStubSamRecords();

	private void addCommonModelAttributes(Model model) {
		model.addAttribute("inputTypes", SamRecord.InputType.values());
		model.addAttribute("executionTypes", SamRecord.ExecutionType.values());
	}

	@GetMapping("/define/sams")
	public String showSams(Model model) {
		model.addAttribute("sams", samRecords);
		model.addAttribute("view", "list"); // Tell the template to show the list
		addCommonModelAttributes(model);
		return "fragments/define/sams :: listContent"; // Return the main template
	}

	@GetMapping("/define/sams/new")
	public String showNewForm(Model model) {
		model.addAttribute("view", "editor"); // Tell the template to show the editor
		addCommonModelAttributes(model);
		// 'sam' attribute is intentionally null for a new form
		return "fragments/define/sams :: editorContent";
	}

	@GetMapping("/define/sams/{id}/edit")
	public String showEditForm(@PathVariable String id, Model model) {
		SamRecord sam = samRecords.stream()
				.filter(s -> s.uniqueId().trim().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("SAM not found with id: " + id));

		model.addAttribute("sam", sam);
		model.addAttribute("view", "editor"); // Tell the template to show the editor
		addCommonModelAttributes(model);
		return "fragments/define/sams :: editorContent"; // Return the fragment
	}

	@PostMapping("/define/sams")
	public String saveSam(@ModelAttribute SamRecord samRecord, Model model) {
		System.out.println("Saving SAM: " + samRecord.name());
		// In a real app, you would persist and then reload.
		// For now, just redirect to the list view.
		model.addAttribute("sams", samRecords);
		model.addAttribute("view", "list");
		return "fragments/define/sams :: listContent"; // Return the fragment
	}

	@DeleteMapping("/define/sams/{id}")
	public String deleteSam(@PathVariable String id, Model model) {
		System.out.println("Deleting SAM with ID: " + id);
		// Return the updated list fragment
		model.addAttribute("sams", samRecords);
		return "fragments/define/sams :: listContent";
	}

	private List<SamRecord> createStubSamRecords() {
		SamRecord builtInSam = new SamRecord(
				"Attr_IsPopulated", SamRecord.SamType.BUILT_IN, "5ea00021-24ce-40c2-a1f7-02875b7732f0",
				"Attribute is Populated", "Populated", "Not Populated",
				"Determines if a simple attribute has a value.", SamRecord.InputType.SIMPLE_ATTRIBUTE,
				List.of(), null, "Accuracy.Completeness",
				"Accuracy.Completeness", SamRecord.ExecutionType.PRIMITIVE_LOGIC, "exists()",
				"2023-01-01T12:00:00Z", "2023-01-01T12:00:00Z",
				List.of(new SamRecord.Source("PIQI Alliance", "f0f3de6d-c3d8-483c-a8ff-004a66dbc551"))
		);

		SamRecord customSam = new SamRecord(
				"Age_IsInRange", SamRecord.SamType.CUSTOM, UUID.randomUUID().toString(),
				"Patient Age is Within Range", "In Range", "Out of Range",
				"Checks if the patient's age is between a min and max value.", SamRecord.InputType.DATA_CLASS,
				List.of(
						new SamRecord.Parameter("minAge", "The minimum age (inclusive).", SamRecord.ParameterType.SIMPLE_TEXT_PARAMETER),
						new SamRecord.Parameter("maxAge", "The maximum age (inclusive).", SamRecord.ParameterType.SIMPLE_TEXT_PARAMETER)
				),
				new SamRecord.PiqiModel("Patient", "1.0", "Patient_v1"),
				"Attr_IsPopulated", "Accuracy.Value", SamRecord.ExecutionType.RESTFUL_SERVICE,
				"age >= minAge && age <= maxAge", "2024-05-10T09:00:00Z", "2024-05-10T09:00:00Z",
				List.of(new SamRecord.Source("Local Hospital System", UUID.randomUUID().toString()))
		);
		return List.of(builtInSam, customSam);
	}
}