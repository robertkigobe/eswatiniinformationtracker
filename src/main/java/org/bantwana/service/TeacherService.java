package org.bantwana.service;

import java.util.List;

import org.bantwana.entity.Teacher;

public interface TeacherService {

	public List<Teacher> getTeachers();

	public void saveTeacher(Teacher theTeacher);

	public void deleteTeacher(int theId);

	public  Teacher getTeacher(int theId);

}
