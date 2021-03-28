package com.papont.lesson.repository;

import com.papont.lesson.entity.Employee;
import com.papont.lesson.projection.EmployeeNameView;
import com.papont.lesson.projection.EmployeeNativeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeCustomRepository {

    Optional<Employee> findByFirstNameContaining(String firstName);

//    @Query("select e from Employee e where e.firstName = ?1 and  e.salary = ?2")
//    @Query("select e from Employee e where e.firstName = :name and e.salary = :salary")

    @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary", nativeQuery = true)
    List<Employee> findAllByFirstNameAndSalary(@Param("name") String firstName,
                                               @Param("salary") Integer salary);


    List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);

    @Query(value = "select e.id as id, " +
            "e.first_name || ' ' || e.last_name as fullName " +
            "from employee e " +
            "where e.salary > :salary", nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);

}

