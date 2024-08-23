package article.projectNum1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import article.projectNum1.write.entity.Write;
import article.projectNum1.write.service.WriteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final WriteService writeService;

	@GetMapping("/")
	public String mainPage(Model model){
		List<Write> writes = writeService.findAll();
		model.addAttribute("writes", writes);
		return "writes/writeList";
	}
}
