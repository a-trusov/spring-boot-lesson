package com.papont.lesson.repository;

import com.papont.lesson.IntegrationTestBase;
import com.papont.lesson.dto.EmployeeFilter;
import com.papont.lesson.entity.Employee;
import com.papont.lesson.projection.EmployeeNameView;
import com.papont.lesson.projection.EmployeeNativeView;
import com.papont.lesson.utils.QPredicates;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.papont.lesson.entity.QEmployee.employee;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testFindByFilterQuery() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .build();

//        if (filter.getFirstName() != null) {
//            //add predicate
//        }
//
//        if (filter.getLastName() != null) {
//            //add predicate
//        }

        List<Employee> employees = repository.findByFilter(filter);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testQueryDslPredicates() {
        BooleanExpression predicate = employee.firstName.containsIgnoreCase("ivaN")
                .and(employee.salary.goe(1000));
        Page<Employee> page = repository.findAll(predicate, Pageable.unpaged());
        assertThat(page.getContent(), hasSize(1));
    }

    @Test
    void testQPredicates() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .salary(1000)
                .build();

        //на уровне сервисов
        final var predicate = QPredicates.builder()
                .add(filter.getFirstName(), employee.firstName::containsIgnoreCase)
                .add(filter.getLastName(), employee.lastName::containsIgnoreCase)
                .add(filter.getSalary(), employee.salary::goe)
                .buildAnd();


        Iterable<Employee> result = repository.findAll(predicate);
        assertTrue(result.iterator().hasNext());
    }

}