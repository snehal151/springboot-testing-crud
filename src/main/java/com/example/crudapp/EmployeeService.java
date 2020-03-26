package com.example.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeList = (List<EmployeeEntity>) employeeRepository.findAll();

            return employeeList;
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException {
        if (entity.getId() == null) {
            return saveEmployee(entity);
        } else {
            Optional<EmployeeEntity> employee = employeeRepository.findById(entity.getId());

            if (employee.isPresent()) {
                EmployeeEntity newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                return saveEmployee(newEntity);
            } else {
                return saveEmployee(entity);
            }
        }
    }

    private EmployeeEntity saveEmployee(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}


