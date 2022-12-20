package com.caranda.student.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caranda.student.db.model.Student;

public interface StudentRpository extends JpaRepository<Student,Long> {
	
}
