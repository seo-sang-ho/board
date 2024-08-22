package article.projectNum1.write.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import article.projectNum1.member.entity.Member;
import article.projectNum1.member.service.MemberService;
import article.projectNum1.write.entity.Write;
import article.projectNum1.write.entity.WriteForm;
import article.projectNum1.write.entity.WriteUpdateForm;
import article.projectNum1.write.service.WriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WriteController {

	private final WriteService writeService;
	private final MemberService memberService;

	@GetMapping("/writes/new")
	public String createWriteForm(Model model) {
		model.addAttribute("WriteForm", new WriteForm());
		return "writes/writeCreateForm";
	}

	@PostMapping("/writes/new")
	public String createWrite(@Valid WriteForm writeForm) {
		Write write = new Write();
		write.setTitle(writeForm.getTitle());
		write.setContent(writeForm.getContent());
		write.setCreate_at(LocalDateTime.now());
		Member writeMember = memberService.findById(writeForm.getMemberId());
		write.setMember(writeMember);
		writeService.save(write);
		return "redirect:/";
	}

	@GetMapping("/writes/update/{id}")
	public String updateWriteForm(@PathVariable("id") Long id, Model model) {
		Write findWrite = writeService.findById(id);

		WriteUpdateForm form = new WriteUpdateForm();
		form.setTitle(findWrite.getTitle());
		form.setContent(findWrite.getContent());
		form.setMemberId(findWrite.getMember().getId());

		model.addAttribute("findWrite", findWrite);
		model.addAttribute("WriteUpdateForm", form);
		return "writes/writeUpdateForm";
	}


	@PostMapping("/writes/update/{id}")
	public String updateWrite(@PathVariable("id") Long id, @Valid WriteUpdateForm writeUpdateForm) {
		writeService.update(id, writeUpdateForm);
		return "redirect:/";
	}

	@GetMapping("/writes/{id}")
	public String write(@PathVariable("id") Long id, Model model) {
		Write findWrite = writeService.findById(id);
		model.addAttribute("write", findWrite);
		return "writes/writeInfo";
	}

	@GetMapping("/writes/list")
	public String writeList(Model model) {
		List<Write> writes = writeService.findAll();
		model.addAttribute("writes", writes);
		return "writes/writeList";
	}

	@PostMapping("/writes/delete/{id}")
	public String deleteWrite(@PathVariable("id") Long id) {
		Write findWrite = writeService.findById(id);
		writeService.delete(findWrite);
		
		return "redirect:/writes/list";
	}
}
