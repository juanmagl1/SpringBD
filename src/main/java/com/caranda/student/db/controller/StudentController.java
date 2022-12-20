package com.caranda.student.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caranda.student.db.model.Student;
import com.caranda.student.db.service.StudentService;


@Controller
public class StudentController {
	@Autowired
	StudentService servicio;

	@GetMapping("listStudent")
	public String listStudent(Model model) {
		model.addAttribute("lista", servicio.getStudents());
		return "listStudents";
	}

	@GetMapping("addStudent")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("estudiante", student);
		return "addStudents";
	}

	@PostMapping("/addStudent/submit")
	public String addStudent(@Validated @ModelAttribute("estudiante") Student aux,
			BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "addStudents";
		}else {
			
			servicio.add(aux);
			return "redirect:/listStudent";
		}
	}

	@GetMapping("delStudent")
	public String delStudent(Model model, @RequestParam(name = "id") Long id) {
		Student estudiante = servicio.getStudent(id);
		model.addAttribute("estudiante", estudiante);
		return "deleteStudents";

	}
	
	@PostMapping("execDel")
	public String deleteStudent(@ModelAttribute("estudiante")Student estudiante) {
		servicio.remove(estudiante);
		return "redirect:/listStudents";

	}
	
	@GetMapping("updateStudent")
	public String updateStudent(Model model, @RequestParam(name = "id") Long id) {
		Student estudiante = servicio.getStudent(id);
		model.addAttribute("estudiante", estudiante);
		return "updateStudents";

	}
	
	@PostMapping("execUpdate")
	public String updateStudent(@ModelAttribute("estudiante")Student estudiante) {
		servicio.update(estudiante);
		return "redirect:/listStudents";

	}
//	@GetMapping("login")
//	public String login() {
//		return "login";
//	}
	

}
