package com.Itachi.Springboot.tut.repository;

import com.Itachi.Springboot.tut.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRespositoryTest {
    @Autowired
    private DepartmentRespository departmentRespository;
    @Autowired
     private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Department department = Department.builder().
                departmentName("Mechanical Engineering").
                departmentCode("ME-06").
                departmentAddress("Delhi").build();
        entityManager.persist(department);

    }
    @Test
    public  void WhenFindById_thenReturnDepartment(){
        Department department = departmentRespository.findById(1l).get();
        assertEquals(department.getDepartmentName(),"Mechanical Engineering");

    }
}