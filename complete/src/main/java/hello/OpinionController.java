package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class OpinionController {

	@Autowired
	protected OpinionService opinionService;

	private static final String OPINION_FORM = "Opinions";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayOpinion( Model model ) {

		model.addAttribute(ENTRIES_KEY, opinionService.findAll());
		model.addAttribute(FORM_KEY, new OpinionsEntry());

		return OPINION_FORM;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeOpinion(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) OpinionsEntry form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, opinionService.findAll());
			return OPINION_FORM;
		}

		opinionService.save(form);

		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteEntry (Model model, @PathVariable Long id) {

		opinionService.delete (id);

		return "redirect:/";
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
	public String editEntry (Model model, @PathVariable Long id) {
		model.addAttribute (FORM_KEY, opinionService.findOne (id));
		return OPINION_FORM;
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
	public String editOpinion (Model model,
									 @PathVariable Long id,
									 @Valid @ModelAttribute(FORM_KEY) OpinionsEntry form,
									 BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, opinionService.findAll());
			return OPINION_FORM;
		}

		OpinionsEntry existing = opinionService.findOne (id);
		existing.setName (form.getName());
		existing.setComment(form.getComment());
		opinionService.save (existing);

		return "redirect:/";
	}


}