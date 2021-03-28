package com.papont.lesson.repository;

import com.papont.lesson.IntegrationTestBase;
import com.papont.lesson.entity.Employee;
import com.papont.lesson.projection.EmployeeNameView;
import com.papont.lesson.projection.EmployeeNativeView;
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

import static org.hamcrest.MatcherAssert.assertThat;
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
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employeeNameViews = repository.findAllBySalaryGreaterThan(500);
        assertThat(employeeNameViews, hasSize(2));
    }


    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = repository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomarQuery() {
        List<Employee> employees = repository.findCustomQuery();
        assertThat(employees, hasSize(0));
    }
}