package training.architect.employeesmicro.logic;

import lombok.val;
import training.architect.employeesmicro.dao.Employee;
import training.architect.employeesmicro.dao.EmployeeRepository;
import training.architect.employeesmicro.dto.CreateEmployeeCommand;
import training.architect.employeesmicro.dto.EmployeeDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
public class EmployeeBean {

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> listEmployees() {
        val employees = employeeMapper.employeesToDtos(employeeRepository.findAll());
        System.out.println("List employees: " + employees);
        return employees;
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeCommand command) {
        System.out.println("Create employee: " + command);
        val employee = employeeMapper.commandToEmployee(command);
        System.out.println(System.identityHashCode((command.getNameOfEmployee())));
        System.out.println(System.identityHashCode((employee.getNameOfEmployee())));
        System.out.println(command.getNameOfEmployee() == employee.getNameOfEmployee());
        val created = employeeRepository.save(employee);
        return created;
    }

}
