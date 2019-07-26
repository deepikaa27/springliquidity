package com.tb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.model.Group;
import com.tb.service.GroupService;

@RestController
@RequestMapping("group")
public class GroupApiController {

	@Autowired
	private GroupService groupService;
	
	static{
		System.out.println("controller");
	}

	/* Adding a resource */
	@PostMapping("/add")
	private int add(@RequestBody Group group) {
		return groupService.add(group);
	}

	/* Updating a resource */
	@PutMapping("/update")
	private int update(@RequestBody Group group) {
		return groupService.update(group);
	}

	/* Getting a resource */
	@GetMapping("/{id}")
	private Group get(@PathVariable int id) {
		return groupService.get(id);
	}

	/* Getting a list of resources */
	@GetMapping("/list")
	private Collection<Group> list() {
		return groupService.list();
	}

	/* Deleting a resource */
	@DeleteMapping("/{id}")
	private int delete(@PathVariable int id) {
		return groupService.delete(id);
	}

}
