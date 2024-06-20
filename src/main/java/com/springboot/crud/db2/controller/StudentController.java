package com.springboot.crud.db2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.db2.entity.Student;
import com.springboot.crud.db2.service.StudentService;

@RestController
@RequestMapping("/student/management")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// Get all students handler
		@GetMapping("/all")
		public ResponseEntity<List<Student>> getStudents() {
			List<Student> list= studentService.getAllStudents();
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
			}
			else {
				return ResponseEntity.of(Optional.of(list));
			}
		}
		// Get single student handler
		@GetMapping("/all/{rollNumber}")
		public ResponseEntity<Student> getStudent(@PathVariable("rollNumber") int rollNumber) {
			Student student = studentService.getStudentsByID(rollNumber);
			if(student == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
			}
			else {
				return ResponseEntity.of(Optional.of(student));
			}
		}
		// add student handler
		@PostMapping("/add/student")
		public ResponseEntity<Student> addStudent(@RequestBody Student student) {
			Student s = null;
			try {
				s = studentService.addStudent(student);
				return ResponseEntity.of(Optional.of(student));
			}
			catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}
					}
		
		//delete Student handler
		@DeleteMapping("/delete/student/{roolNumber}")
		public ResponseEntity<Void> deleteStudent(@PathVariable("roolNumber") int roolNumber) {
			try {
				studentService.deleteStudent(roolNumber);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}
		}
		
		//update Student handler
		@PutMapping("/update/student/{roolNumber}")
		public ResponseEntity<Student> updateBook(@PathVariable("roolNumber") int roolNumber, @RequestBody Student student) {
			studentService.updateStudent(roolNumber, student);
			try {
				studentService.updateStudent(roolNumber, student);
				return ResponseEntity.of(Optional.of(student));	
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		

}
