package com.springboot.crud.db2.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.crud.db2.entity.Student;
import com.springboot.crud.db2.repository.StudentRepositoty;

@Component
public class StudentService {
	
	@Autowired
	private StudentRepositoty studentRepositoty;	

	public List<Student> getAllStudents(){
		return (List<Student>) studentRepositoty.findAll();
	}
	
	public Student getStudentsByID(int rollNumber) {
		Student s = null;
		try {
		 s = studentRepositoty.findById(rollNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public Student addStudent(Student s) {
		Student student = studentRepositoty.save(s);
		return student;
	}
	
	public void deleteStudent(int rollNumber) {
		studentRepositoty.deleteById(rollNumber);
	}

	public void updateStudent(int rollNumber, Student student) {
		//student.setRollNumber(rollNumber);
		studentRepositoty.delete(student);
		studentRepositoty.save(student);

	}

}
