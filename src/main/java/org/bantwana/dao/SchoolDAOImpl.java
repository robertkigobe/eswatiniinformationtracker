package org.bantwana.dao;

import java.util.List;

import org.bantwana.entity.School;
import org.bantwana.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDAOImpl implements SchoolDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<School> getSchools() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by last name
		Query<School> theQuery = currentSession.createQuery("from School", School.class);

		// execute query and get result list
		List<School> schools = theQuery.getResultList();

		// return the results
		return schools;
	}

}
