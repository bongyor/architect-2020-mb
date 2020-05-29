package training.architect.employeesmicro.logic;

import lombok.val;
import training.architect.employeesmicro.dao.Employee;
import training.architect.employeesmicro.dao.EmployeeRepository;
import training.architect.employeesmicro.dto.CreateEmployeeCommand;
import training.architect.employeesmicro.dto.EmployeeDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class EmployeeBean {

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    EmployeeMapper employeeMapper;

    public List<EmployeeDto> listEmployees() {
        val employees = employeeMapper.employeesToDtos(employeeRepository.listAll());
        System.out.println("List employees: " + employees);
        return employees;
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeCommand command) {
        System.out.println("Create employee: " + command);
        val employee = employeeMapper.commandToEmployee(command);
        System.out.println(System.identityHashCode((command.getNameOfEmployee())));
//        System.out.println(System.identityHashCode((employee.getNameOfEmployee())));
        System.out.println(command.getNameOfEmployee() == employee.getNameOfEmployee());
        employeeRepository.persist(employee);
        return employee;
    }

}
