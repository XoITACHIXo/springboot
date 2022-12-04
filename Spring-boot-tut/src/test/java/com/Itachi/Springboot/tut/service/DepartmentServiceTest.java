package com.Itachi.Springboot.tut.service;

import com.Itachi.Springboot.tut.entity.Department;
import com.Itachi.Springboot.tut.error.DepartmentNotFoundException;
import com.Itachi.Springboot.tut.repository.DepartmentRespository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
 private  DepartmentService departmentService;
    @MockBean
    private DepartmentRespository departmentRespository;
    @BeforeEach
    void setUp() {
        Department department = Department.builder().

                departmentName("IT").
                departmentAddress("Ahemdabad").
                departmentId(1l).
                build();
        Mockito.when(departmentRespository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }
    @DisplayName("Get data based on valid Department name ")
    @Test
    public void WhenValidDepartmentName_theDepartmentShouldFound(){
        String departmentName="IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }

}