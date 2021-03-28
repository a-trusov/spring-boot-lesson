package com.papont.lesson.repository;

import com.papont.lesson.entity.Employee;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{

    private final EntityManager entityManager;

    @Override
    public List<Employee> findCustomQuery() {
        return Collections.emptyList();
    }
}
