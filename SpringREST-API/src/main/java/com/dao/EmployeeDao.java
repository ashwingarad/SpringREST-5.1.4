/**
 * SpringMVC-AjaxWithHibernate
 */
package com.dao;

import java.util.List;

import com.model.Employee;

/**
 * @author Ashwin
 *
 */
public interface EmployeeDao {
    public boolean saveDao(Employee employee);

    public boolean deleteDao(Long id);

    public Employee getDao(Long id);

    public List<Employee> getListDao();
}
