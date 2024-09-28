package com.tr.employee.entity;

import com.tr.department.entity.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Formula(value = "concat(name, ' ', surname)")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name, String surname, Integer age, Department department) {
        this.age = age;
        this.department = department;
        this.name = name;
        this.surname = surname;
    }

}
