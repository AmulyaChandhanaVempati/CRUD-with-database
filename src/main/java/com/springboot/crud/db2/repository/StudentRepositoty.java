package com.springboot.crud.db2.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.crud.db2.entity.Student;

public interface StudentRepositoty extends CrudRepository<Student, Integer>{
	public Student findById(int roolNumber);

}
