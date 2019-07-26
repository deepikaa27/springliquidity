package com.tb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tb.dao.GroupDao;
import com.tb.model.Group;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Override
	public int add(Group group) {
		return groupDao.add(group);
	}

	@Override
	public int update(Group group) {
		return groupDao.update(group);
	}

	@Override
	public Group get(int groupId) {
		return groupDao.get(groupId);
	}

	@Override
	public List<Group> list() {
		return groupDao.list();
	}

	@Override
	public int delete(int groupId) {
		return groupDao.delete(groupId);
	}

}
