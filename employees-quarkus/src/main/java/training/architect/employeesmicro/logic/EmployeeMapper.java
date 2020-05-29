package training.architect.employeesmicro.logic;

import org.mapstruct.Mapper;
import training.architect.employeesmicro.dao.Employee;
import training.architect.employeesmicro.dao.Name;
import training.architect.employeesmicro.dto.CreateEmployeeCommand;
import training.architect.employeesmicro.dto.EmployeeDto;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    Employee commandToEmployee(CreateEmployeeCommand command);

    Name nameToName(Name name);

}
