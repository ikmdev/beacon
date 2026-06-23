package dev.ikm.beacon.web.define.sam;

import dev.ikm.beacon.web.define.sam.details.SamAdvanceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class SamController {

	private final SamService samService;

	@Autowired
	public SamController(SamService samService) {
		this.samService = samService;
	}

	@ModelAttribute
	public void addCommonModelAttributes(Model model) {
		model.addAttribute("inputTypes", SamAdvanceDetails.InputType.values());
		model.addAttribute("executionTypes", SamAdvanceDetails.ExecutionType.values());
		model.addAttribute("parameterTypes", SamAdvanceDetails.ParameterType.values());
	}

	@GetMapping("/define/sams")
	public String getSAMListContent(Model model) {
		model.addAttribute("sams", samService.findAll());
		return "fragments/define/sams :: listContent";
	}

	@GetMapping("/define/sams/new")
	public String getSamEditorContentForNewSam(Model model) {
		model.addAttribute("sam", samService.createEmptySamRecord());
		return "fragments/define/sams :: editorContent";
	}

	@GetMapping("/define/sams/{id}/edit")
	public String getSamEditorContentById(@PathVariable UUID id, Model model) {
		samService.findById(id).ifPresent(record -> model.addAttribute("sam", record));
		return "fragments/define/sams :: editorContent";
	}

	@PostMapping("/define/sams")
	public String postSam(@ModelAttribute SamRecord samRecord, Model model) {
		samService.create(samRecord);
		model.addAttribute("sams", samService.findAll());
		return "fragments/define/sams :: listContent";
	}

	@PostMapping("/define/sams/add-parameter")
	public String addParameter(@ModelAttribute SamRecord sam, Model model) {
		if (sam.samAdvanceDetails() != null && sam.samAdvanceDetails().parameters() != null) {
			sam.samAdvanceDetails().parameters().add(new SamAdvanceDetails.Parameter(null, null, null, false, null, null));
		}
		model.addAttribute("sam", sam);
		return "fragments/define/sams :: parametersFragment";
	}

	@PostMapping("/define/sams/remove-parameter")
	public String removeParameter(@ModelAttribute SamRecord sam, @RequestParam("removeIndex") int index, Model model) {
		if (sam.samAdvanceDetails() != null && sam.samAdvanceDetails().parameters() != null) {
			if (index >= 0 && index < sam.samAdvanceDetails().parameters().size()) {
				sam.samAdvanceDetails().parameters().remove(index);
			}
		}
		model.addAttribute("sam", sam);
		return "fragments/define/sams :: parametersFragment";
	}

	/**     * HTMX action for saving the parameter list back to the server.     */
	@PostMapping("/define/sams/update-parameters")
	public String updateParameter(@ModelAttribute SamRecord sam, Model model) {
		if (sam.samBasicDetails() != null && sam.samBasicDetails().uniqueId() != null 
				&& sam.samAdvanceDetails() != null) {
			samService.updateParameters(sam.samBasicDetails().uniqueId(), sam.samAdvanceDetails().parameters());
		}
		model.addAttribute("sam", sam);

		return "fragments/define/sams :: parametersFragment";
	}

	@DeleteMapping("/define/sams/{id}")
	public String deleteSamById(@PathVariable UUID id, Model model) {
		samService.deleteById(id);
		model.addAttribute("sams", samService.findAll());
		return "fragments/define/sams :: listContent";
	}
}