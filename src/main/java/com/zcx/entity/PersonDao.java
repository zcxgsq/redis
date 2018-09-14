package com.zcx.entity;
import com.zcx.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
@SuppressWarnings("ALL")
@Repository
public class PersonDao {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate; //1 Spring Boot已为我们配置StringRedisTemplate，在此处可以直接注入。
	
	@Resource(name="stringRedisTemplate")
	ValueOperations<String,String> valOpsStr; //3 可以使用@Resource注解指定stringRedisTemplate，可注入基于字符串的简单属性操作方法。
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate; //2 Spring Boot已为我们配置RedisTemplate，在此处可以直接注入。
	
	@Resource(name="redisTemplate")
    ValueOperations<Object, Object> valOps; //4 可以使用@Resource注解指定redisTemplate，可注入基于对象的简单属性操作方法；
	
	public void stringRedisTemplateDemo(){ //5 通过set方法，存储字符串类型。
		valOpsStr.set("zz", "mm");
	}
	public void save(Person person){ //6 通过set方法，存储对象类型。
		valOps.set(person.getId(),person);
	}
	
	public String getString(){//7 通过get方法，获得字符串。
		return valOpsStr.get("xx");
	}
	
	public Person getPerson(){//8 通过get方法，获得对象。
		System.out.println((Person) valOps.get("2"));
		return (Person) valOps.get("2");
	}
}
