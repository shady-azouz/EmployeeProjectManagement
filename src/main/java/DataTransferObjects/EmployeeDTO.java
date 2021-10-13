package DataTransferObjects;

import Entities.Employee;
import Entities.Role;

public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalId;
    private Integer age;
    private Role role;

    public EmployeeDTO(Integer id, String firstName, String lastName, String email, String phoneNumber, String nationalId, Integer age, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.age = age;
        this.role = role;
    }

    public EmployeeDTO(Employee e){
        this.id = e.getId();
        this.firstName = e.getFirstName();
        this.lastName = e.getLastName();
        this.email = e.getEmail();
        this.phoneNumber = e.getPhoneNumber();
        this.age = e.getAge();
        this.nationalId = e.getNationalId();
        this.role = e.getRoleId();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public Integer getAge() {
        return age;
    }

    public String getRole() {
        return role.getRoleName();
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", age=" + age +
                ", roleId=" + role.getId() +
                ", roleName=" + role.getRoleName() +
                '}';
    }
}
