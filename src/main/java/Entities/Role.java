package Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Role")
public class Role {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "role_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "role_description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @OneToMany(mappedBy = "role")
    Set<Employee> employees;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return name;
    }

    public void setRoleName(String name) {
        this.name = name;
    }

    public String getRoleDescription() {
        return description;
    }

    public void setRoleDescription(String description) {
        this.description = description;
    }
}
