package com.papont.lesson.repository;

import com.papont.lesson.IntegrationTestBase;
import com.papont.lesson.entity.Employee;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest extends IntegrationTestBase {

    public static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindById() {
        Optional<Employee> employee = repository.findById(IVAN_ID);
        assertTrue(employee.isPresent());

        employee.ifPresent(entity -> {
            assertEquals("Ivan", entity.getFirstName());
            assertEquals("Ivanov", entity.getLastName());
        });
    }

    @Test
    void testFindByFirstName() {
        Optional<Employee> employee = repository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindAllByFirstNameAndSalary() {
        List<Employee> employees = repository.findAllByFirstNameAndSalary("Ivan", 1000);
        MatcherAssert.assertThat(employees, hasSize(1));
    }
}