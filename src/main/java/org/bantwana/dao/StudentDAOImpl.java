package org.bantwana.dao;

import java.util.List;

import org.bantwana.entity.Student;
import org.bantwana.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				// create a query ... sort by last name
				Query<Student> theQuery = currentSession.createQuery("from Student", Student.class);
		
				// execute query and get result list
				List<Student> students = theQuery.getResultList();

				// return the results
				return students;
	}

}
