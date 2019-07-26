package com.scb.guass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.guass.model.Groups;
import com.scb.guass.repository.GroupRepository;

@RestController
public class Controller {
	
	@Autowired
	GroupRepository custRes;
	
	@RequestMapping("/findall")
	public List<Groups> findAll()
	{
		return custRes.findAllOrderWithItmes();
		
	}
	
	@RequestMapping("/find/{id}")
	public Groups find(Long id)
	{
		return custRes.findOrderWithItems(id);
		
	}
	

}
