package com.zcx.controller;

import com.zcx.dao.Person;
import com.zcx.entity.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class DataController {
	
	@Autowired
	PersonDao personDao;

	@RequestMapping("/set") //1
	public void set(){
		Person person = new Person("2","zcx", 25);
		personDao.save(person);
		personDao.stringRedisTemplateDemo();
	}

	@RequestMapping("/setStr")
	public void setstringRedisTemplateDemo() {
		personDao.stringRedisTemplateDemo();
	}

	@RequestMapping("/getStr") //2
	public String getStr(){
		return personDao.getString();
	}

	@RequestMapping("/getPerson") //3
	public Person getPerson(){
		return personDao.getPerson();
	}
}
