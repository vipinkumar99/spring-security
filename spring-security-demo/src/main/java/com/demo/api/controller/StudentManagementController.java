package com.demo.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.StudentPojo;

@RestController()
@RequestMapping("/management/api/v1/student")
public class StudentManagementController {
	public static List<StudentPojo> students = Arrays.asList(new StudentPojo(1, "Vipin"), new StudentPojo(2, "Nitin"));

	// hasRole('ROLE_'),hasAnyRole('ROLE_'),hasAuthority('permission'),hasAnyAuthority('permission')

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
	public List<StudentPojo> getAll() {
		return students;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody StudentPojo student) {
		System.out.println("add student : " + student);
	}

	@DeleteMapping(path = "/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("delete student : " + studentId);
	}

	@PutMapping(path = "/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody StudentPojo student) {
		System.out.println("update student : " + String.format(" Id %s : %s", studentId, student));
	}

}
