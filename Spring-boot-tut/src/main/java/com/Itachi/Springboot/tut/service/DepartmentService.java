package com.Itachi.Springboot.tut.service;

import com.Itachi.Springboot.tut.entity.Department;
import com.Itachi.Springboot.tut.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
   public List<Department> fetchDepartmentList();

   public Department saveDepartment(Department department);

  public  Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

   public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

   public  Department fetchDepartmentByName(String departmentName);
}
