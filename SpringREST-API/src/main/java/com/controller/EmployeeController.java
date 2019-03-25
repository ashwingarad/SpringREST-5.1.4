package com.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.service.EmployeeService;

/**
 * @author Ashwin
 *
 */

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

	@Resource
	private EmployeeService employeeService;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public ResponseEntity<List<Employee>> getHome() {
		var employeeList = this.getList();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Employee getEmp(@PathVariable final Long id) {
		Employee employee = null;
		if (id != null) {
			employee = employeeService.getService(id);
		}
		return employee;
	}

	@PostMapping(value = "/save", headers = "Accept=application/json")
	public ResponseEntity<String> saveAndUpdateEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
		String str = null;
		HttpStatus httpStatus = null;
		if (result.hasErrors()) {
			str = "Binding error";
			httpStatus = HttpStatus.BAD_REQUEST;
		} else {
			Long i = employee.getId();
			boolean status = employeeService.saveService(employee);
			if (!status) {
				httpStatus = HttpStatus.OK;
				if (i == null) {
					str = "Saved Successfully..";
				} else {
					str = "Updated Successfully..";
				}
			} else {
				str = "Something went wrong...";
				httpStatus = HttpStatus.RESET_CONTENT;
			}
		}
		return new ResponseEntity<>(str, httpStatus);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long id) {
		String str = null;
		HttpStatus httpStatus = null;
		if (id != null) {
			httpStatus = HttpStatus.OK;
			boolean status = employeeService.deleteService(id);
			if (!status)
				str = "Deleted Successfully..";
		} else {
			str = "Something went wrong...";
			httpStatus = HttpStatus.RESET_CONTENT;
		}
		return new ResponseEntity<>(str, httpStatus);
	}

	public List<Employee> getList() {
		return employeeService.getListService();
	}
}
