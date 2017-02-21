package com.cidic.sdx;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.hpgl.model.Gender;
import com.cidic.sdx.hpgl.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class RedisTest {
	// inject the actual template
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name="redisTemplate")
    HashOperations<String, String, String> hashOperations;

    private final HashMapper<Student, String, String> mapper =
    	     new DecoratingStringHashMapper<Student>(new BeanUtilsHashMapper<Student>(Student.class));
    
    public void writeHash(String key, Student person) {

      Map<String, String> mappedHash = mapper.toHash(person);
      hashOperations.putAll(key, mappedHash);
    }

    public Student loadHash(String key) {

      Map<String, String> loadedHash = hashOperations.entries(key);
      return (Student) mapper.fromHash(loadedHash);
    }
    
    public void addLink(String userId, String url) {
        listOps.leftPush(userId, url);
        // or use template directly
        //redisTemplate.boundListOps(userId).leftPush(url);
    }
    
    //@Test
    public void test(){
    	this.addLink("66666666",  "���");
    }
    
    @Test
    public void testHash(){
    	/*
    	Student person = new Student();
    	person.setId("kkkkk");
    	person.setName("jack bauer");
    	person.setGender(Gender.MALE);
    	this.writeHash("hashtest", person);
    	*/
    	Student student = this.loadHash("hashtest");
    	System.out.println(student.getName());
    }
}
