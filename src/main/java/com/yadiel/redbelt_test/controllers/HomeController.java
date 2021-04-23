package com.yadiel.redbelt_test.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yadiel.redbelt_test.models.Ideas;
import com.yadiel.redbelt_test.models.User;
import com.yadiel.redbelt_test.services.UserService;
import com.yadiel.redbelt_test.validation.UserValidator;

@Controller
public class HomeController {
	
	private final UserService userserv;
	private final UserValidator userValidator;
	
	public HomeController(UserService userserv, UserValidator userValidator) {
		super();
		this.userserv = userserv;
		this.userValidator = userValidator;
	}

	@GetMapping("/")
	public String greeting(@ModelAttribute("user") User user) {
		
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		
		User u = this.userserv.registerUser(user);
		
		session.setAttribute("userid", u.getId());

		return "redirect:/ideas";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
		
		Boolean isLegit = this.userserv.authenticateUser(email, password);
		
		if(isLegit) {
			User user = this.userserv.findByEmail(email);
			session.setAttribute("userid", user.getId());
			return "redirect:/ideas";
		}
		redirectAttributes.addFlashAttribute("error", "Invalid login attempt");
		return "redirect:/";
	}
	
	
	@GetMapping("/ideas")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long)session.getAttribute("userid");
		User loggedinuser = this.userserv.findUserById(id);
		
		model.addAttribute("allideas", this.userserv.getAllIdeas());
		model.addAttribute("loggedinuser", loggedinuser);
		return "dashboard.jsp";
	}
	
	@GetMapping("/ideas/new")
	public String newIdea(@ModelAttribute("idea") Ideas idea) {
		return "newIdea.jsp";
	}
	
	
	@PostMapping("/ideas/create")
	public String createIdea(@Valid @ModelAttribute("idea") Ideas idea, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "newIdea.jsp";
		}
		Long id = (Long)session.getAttribute("userid");
		User loggedinuser = this.userserv.findUserById(id);
		
		idea.setCreator(loggedinuser);
		
		this.userserv.createIdea(idea);
		
		return "redirect:/ideas";
	}
	
	
	@GetMapping("/ideas/show/{id}")
	public String showIdea(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ideatoshow", this.userserv.getIdeaById(id));
		return "showIdea.jsp";
	}
	
	@GetMapping("/ideas/edit/{id}")
	public String editIdea(@PathVariable("id") Long id, Model model) {
		model.addAttribute("idea", this.userserv.getIdeaById(id));
		return "edit.jsp";
	}
	
	@PostMapping("/ideas/update/{id}")
	public String updateIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("idea") Ideas idea, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		
		Long loggedinuserid = (Long)session.getAttribute("userid");
		User loggedinuser = this.userserv.findUserById(loggedinuserid);
		
		idea.setCreator(loggedinuser);
		
		this.userserv.updateAnIdea(idea);
		
		return "redirect:/ideas";
	}
	
	@GetMapping("delete/{id}")
	public String deleteIdea(@PathVariable("id") Long id) {
		
		Ideas i = this.userserv.getIdeaById(id);
		this.userserv.deleteIdea(i);
		
		return "redirect:/ideas";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
