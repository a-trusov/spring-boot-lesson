package com.papont.lesson.repository;

import com.papont.lesson.dto.EmployeeFilter;
import com.papont.lesson.entity.Employee;
import com.papont.lesson.entity.QEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{

    private final EntityManager entityManager;

    @Override
    public List<Employee> findByFilter(EmployeeFilter filter) {
        QEmployee employee = QEmployee.employee;

        return new JPAQuery<Employee>(entityManager)
                .select(employee)
                .from(employee)
                .where(employee.firstName.containsIgnoreCase(filter.getFirstName()))
                .fetch();
    }
}
