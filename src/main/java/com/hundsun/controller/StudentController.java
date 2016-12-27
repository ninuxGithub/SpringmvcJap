package com.hundsun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hundsun.domain.Student;
import com.hundsun.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping("/admin/students")
	public String list(ModelMap model) {
		List<Student> list = studentRepository.findAll();
		model.addAttribute("stuList", list);
		return "admin/students";
	}

	@RequestMapping("/admin/student/addP")
	public String addP(Student student) {
		if (null != student) {
			studentRepository.saveAndFlush(student);
		}
		return "redirect:/admin/students";
	}

	@RequestMapping("/admin/student/updateP")
	public String updateP(Student student) {
		if (null != student) {
			studentRepository.saveAndFlush(student);
		}
		return "redirect:/admin/students";
	}

	@RequestMapping("/admin/student/add")
	public String add() {
		return "admin/addStudent";
	}

	@RequestMapping(value = "/admin/student/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap model) {
		if (null != id) {
			Student stu = studentRepository.findOne(id);
			model.addAttribute("stu", stu);
		}
		return "admin/studentDetail";
	}

	@RequestMapping(value = "/admin/student/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		if (null != id) {
			studentRepository.delete(id);
		}
		studentRepository.flush();

		return "redirect:/admin/students";
	}

	@RequestMapping(value="/admin/student/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, ModelMap map) {
		if (null != id) {
			Student stu = studentRepository.findOne(id);
			map.addAttribute("stu", stu);
		}
		return "admin/updateStudent";
	}
}
