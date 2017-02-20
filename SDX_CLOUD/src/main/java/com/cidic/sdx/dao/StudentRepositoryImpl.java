package com.cidic.sdx.dao;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	private static final String KEY = "Student";

	private RedisTemplate<String, Student> redisTemplate;
	private HashOperations<String, String, Student> hashOps;

	@Autowired
	private StudentRepositoryImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
	
	@Override
	public <S extends Student> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Student> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Student> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Student entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Student> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
