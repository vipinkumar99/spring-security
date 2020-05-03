package com.demo.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.StudentPojo;

@RestController()
@RequestMapping("/api/v1/student")
public class StudentController {
	public static List<StudentPojo> students = Arrays.asList(new StudentPojo(1, "Vipin"), new StudentPojo(2, "Nitin"));

	@GetMapping(path = "/{id}")
	public StudentPojo getById(@PathVariable("id") Integer id) throws IllegalStateException {
		return students.stream().filter(r -> r.getStudentId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalStateException("student does not exists :" + id));
	}

}
