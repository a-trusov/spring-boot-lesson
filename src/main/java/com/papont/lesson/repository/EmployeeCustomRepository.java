package com.papont.lesson.repository;

import com.papont.lesson.dto.EmployeeFilter;
import com.papont.lesson.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {


    List<Employee> findByFilter(EmployeeFilter filter);
}
