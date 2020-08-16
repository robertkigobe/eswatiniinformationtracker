package org.bantwana.service;

import java.util.List;

import javax.transaction.Transactional;

import org.bantwana.dao.SchoolDAO;
import org.bantwana.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	public SchoolDAO schoolDAO;

	@Override
	@Transactional
	public List<School> getSchools() {
		// TODO Auto-generated method stub
		return schoolDAO.getSchools();
	}

}
