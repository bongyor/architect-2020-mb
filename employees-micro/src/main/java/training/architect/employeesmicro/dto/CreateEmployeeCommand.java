package training.architect.employeesmicro.dto;

import lombok.Data;
import training.architect.employeesmicro.dao.Name;

@Data
public class CreateEmployeeCommand {
    private Name nameOfEmployee;
    private Name nameOfMother;
}
