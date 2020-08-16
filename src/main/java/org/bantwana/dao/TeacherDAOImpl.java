package org.bantwana.dao;

import java.util.List;

import org.bantwana.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Teacher> getTeachers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Teacher> theQuery = currentSession.createQuery("from Teacher", Teacher.class);

		// execute query and get result list
		List<Teacher> teachers = theQuery.getResultList();

		// return the results
		return teachers;

	}

	@Override
	public void saveTeacher(Teacher theTeacher) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theTeacher);

	}

	@Override
	public void deleteTeacher(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Teacher where id=:teacherId");
		theQuery.setParameter("teacherId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public Teacher getTeacher(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Teacher theTeacher = currentSession.get(Teacher.class, theId);

		return theTeacher;

	}

}
