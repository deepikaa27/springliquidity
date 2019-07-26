package com.tb.service;

import java.util.List;

import com.tb.model.Group;

public interface GroupService {
	public int add(Group group);

	public int update(Group group);

	public Group get(int groupId);

	public List<Group> list();

	public int delete(int groupId);
}
