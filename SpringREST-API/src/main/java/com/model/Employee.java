package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String ename;

    @Column
    @NotNull
    private Integer salary;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEname() {
	return ename;
    }

    public void setEname(String ename) {
	this.ename = ename;
    }

    public Integer getSalary() {
	return salary;
    }

    public void setSalary(Integer salary) {
	this.salary = salary;
    }

}
