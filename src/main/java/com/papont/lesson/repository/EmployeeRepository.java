package com.papont.lesson.repository;

import com.papont.lesson.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByFirstNameContaining(String firstName);

//    @Query("select e from Employee e where e.firstName = ?1 and  e.salary = ?2")
//    @Query("select e from Employee e where e.firstName = :name and e.salary = :salary")

    @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary", nativeQuery = true)
    List<Employee> findAllByFirstNameAndSalary(@Param("name") String firstName,
                                               @Param("salary") Integer salary);
}
