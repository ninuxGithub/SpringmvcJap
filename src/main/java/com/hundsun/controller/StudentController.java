package com.hundsun.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.domain.Student;
import com.hundsun.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@ModelAttribute
	public void getStudent(@RequestParam(value = "id", required = false) Long id, ModelMap map) {
		if (null != id) {
			Student stu = studentRepository.findOne(id);
			map.addAttribute("stu", stu);
		}
	}

	@RequestMapping("/admin/students")
	public String list(ModelMap model) {
		List<Student> list = studentRepository.findAll();
		model.addAttribute("stuList", list);
		return "admin/students";
	}

	/**
	 * 
	 * @param student 在这个入参加入了@ModelAttribute("stu") 
	 * 	对request与对象中的属性指定了固定的名称 ，所以在from 表单的modelAttribute 
	 * 	要和这个名称一致，错误消息才会会显示到页面
	 * 	我么可以通过这个注解来自定义域对象中属性的名称
	 * 
	 * @param result
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/admin/student/addP", method = RequestMethod.POST)
	public String addP(@Valid @ModelAttribute("stu") Student student, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				
				System.err.println(error.getField() + ":" + error.getDefaultMessage());
			}
			map.addAttribute("stu", student);
			return "admin/addStudent";
		} else {
			if (null != student) {
				studentRepository.saveAndFlush(student);
			}
			return "redirect:/admin/students";
		}
	}

	@RequestMapping("/admin/student/updateP")
	public String updateP(Student student) {
		if (null != student) {
			studentRepository.saveAndFlush(student);
		}
		return "redirect:/admin/students";
	}

	@RequestMapping(value = "/admin/student/add", method=RequestMethod.GET)
	public String add(Map<String,Object> map) {
		map.put("stu", new Student());
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

	@RequestMapping(value = "/admin/student/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, ModelMap map) {
		if (null != id) {
			Student stu = studentRepository.findOne(id);
			map.addAttribute("stu", stu);
		}
		return "admin/updateStudent";
	}
}
