package training.architect.employeesmicro.dto;

import lombok.Data;
import training.architect.employeesmicro.dao.Name;

@Data
public class EmployeeDto {

    private Long id;

    private Name nameOfEmployee;

    private Name nameOfMother;
}
