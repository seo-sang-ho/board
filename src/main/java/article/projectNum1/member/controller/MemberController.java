package article.projectNum1.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import article.projectNum1.member.entity.Member;
import article.projectNum1.member.entity.MemberForm;
import article.projectNum1.member.entity.MemberUpdateForm;
import article.projectNum1.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/members/new")
	public String joinMemberForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/memberCreateForm";
	}

	@PostMapping("/members/new")
	public String joinMember(@Valid MemberForm memberForm){
		Member member = new Member();
		member.setUsername(memberForm.getUsername());
		member.setPassword(memberForm.getPassword());
		member.setEmail(memberForm.getEmail());
		memberService.save(member);
		return "redirect:/";
	}
	@GetMapping("/members/update/{id}")
	public String updateMemberForm(@PathVariable("id") Long id, Model model){
		Member findMember = memberService.findById(id);
		model.addAttribute("updateMember", findMember);
		model.addAttribute("updateMemberForm", new MemberUpdateForm());
		return "members/updateMemberForm";
	}

	@PostMapping("/members/update/{id}")
	public String updateMember(@PathVariable("id") Long id, @Valid MemberUpdateForm memberUpdateForm){
		memberService.update(id, memberUpdateForm);
		return "redirect:/";
	}

	@GetMapping("/members/{id}")
	public String showMember(@PathVariable("id") Long id, Model model) {
		Member findMember = memberService.findById(id);
		model.addAttribute("member", findMember);
		return "members/memberInfo";
	}

	@GetMapping("/members/list")
	public String memberList(Model model) {
		List<Member> members = memberService.findAll();
		model.addAttribute("members", members);
		return "members/memberList";
	}

	@PostMapping("/members/delete/{id}")
	public String deleteMember(@PathVariable("id") Long id) {
		Member findMember = memberService.findById(id);
		memberService.delete(findMember);
		return "redirect:/members/list";
	}
}
