package org.bantwana.service;

import java.util.List;

import org.bantwana.dao.TeacherDAO;
import org.bantwana.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDAO teacherDAO;

	@Override
	@Transactional
	public List<Teacher> getTeachers() {

		return teacherDAO.getTeachers();

	}

	@Override
	@Transactional
	public void saveTeacher(Teacher theTeacher) {
		teacherDAO.saveTeacher(theTeacher);
		
	}

	@Override
	@Transactional
	public void deleteTeacher(int theId) {
		
		teacherDAO.deleteTeacher(theId);
		
	}
	
	@Override
	@Transactional
	public Teacher getTeacher(int theId) {
		
		return teacherDAO.getTeacher(theId);
	}

}
