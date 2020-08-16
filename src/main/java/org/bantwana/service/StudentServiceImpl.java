package org.bantwana.service;


import java.util.List;

import org.bantwana.dao.StudentDAO;
import org.bantwana.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	public StudentDAO studentDAO;

	@Override
	@Transactional
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentDAO.getStudents();
	}

}
