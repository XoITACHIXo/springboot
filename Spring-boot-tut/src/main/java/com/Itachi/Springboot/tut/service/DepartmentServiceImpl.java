package com.Itachi.Springboot.tut.service;

import com.Itachi.Springboot.tut.entity.Department;
import com.Itachi.Springboot.tut.error.DepartmentNotFoundException;
import com.Itachi.Springboot.tut.repository.DepartmentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
 private DepartmentRespository departmentRespository;

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRespository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRespository.save(department);
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
       Optional<Department> department= departmentRespository.findById(departmentId);
       if(!department.isPresent()){
           throw new DepartmentNotFoundException("Department not Available");

       }
       return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRespository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDb = departmentRespository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName())&&!"".equalsIgnoreCase(department.getDepartmentName())){
            depDb.setDepartmentName(department.getDepartmentName());
        }if(Objects.nonNull(department.getDepartmentCode())&&!"".equalsIgnoreCase(department.getDepartmentCode())){
            depDb.setDepartmentCode(department.getDepartmentCode());
        }if(Objects.nonNull(department.getDepartmentAddress())&&!"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDb.setDepartmentAddress(department.getDepartmentAddress());
        }
        return  departmentRespository.save(depDb);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRespository.findByDepartmentNameIgnoreCase(departmentName);
    }

}
