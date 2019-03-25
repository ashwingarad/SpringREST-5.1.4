/**
 * SpringMVC-AjaxWithHibernate
 */
package com.service;

import java.util.List;

import com.model.Employee;

/**
 * @author Ashwin
 *
 */
public interface EmployeeService {
    public boolean saveService(Employee employee);

    public boolean deleteService(Long id);

    public Employee getService(Long id);

    public List<Employee> getListService();
}
