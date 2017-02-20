package com.cidic.sdx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import com.cidic.sdx.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {


}
