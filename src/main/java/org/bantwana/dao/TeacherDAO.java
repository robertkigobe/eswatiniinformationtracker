package org.bantwana.dao;

import java.util.List;

import org.bantwana.entity.Teacher;

public interface TeacherDAO {

	List<Teacher> getTeachers();

	void saveTeacher(Teacher theTeacher);

	void deleteTeacher(int theId);

	Teacher getTeacher(int theId);

}
