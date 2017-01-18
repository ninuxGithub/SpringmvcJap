package com.hundsun.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hundsun.domain.Student;
import com.hundsun.repository.StudentRepository;

@RestController
public class TestAngularController {
	private static final SimpleDateFormat DEFAULT_DATE_FORMATE = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private StudentRepository studentService;

	@RequestMapping(value = "/s/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> findAll() {
		List<Student> list = studentService.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/s/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Student student, UriComponentsBuilder build) {
		System.out.println(student);
		List<Student> list = studentService.findByUserName(student.getName());
		if (list != null && list.size()>0) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		try {
			student.setBirthday(DEFAULT_DATE_FORMATE.parse(student.getBirth()));
		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		studentService.saveAndFlush(student);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/s/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
		Student s = studentService.findOne(id);
		if (null == s) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		s.setName(student.getName());
		try {
			s.setBirthday(DEFAULT_DATE_FORMATE.parse(student.getBirth()));
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		s.setAge(student.getAge());

		studentService.saveAndFlush(s);

		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}

	@RequestMapping(value = "/s/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
		Student s = studentService.findOne(id);
		if (null == s) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		studentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
