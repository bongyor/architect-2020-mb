package training.architect.employeesmicro.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
}
