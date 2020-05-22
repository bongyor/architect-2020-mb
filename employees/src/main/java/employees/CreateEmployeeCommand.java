package employees;

import lombok.Data;

@Data
public class CreateEmployeeCommand {
    private Name nameOfEmployee;
    private Name nameOfMother;
}
