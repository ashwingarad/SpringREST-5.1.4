package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.model.Employee;

/**
 * @author Ashwin
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.service.EmployeeService#saveService(com.model.Employee)
     */
    @Override
    public boolean saveService(Employee employee) {
	return employeeDao.saveDao(employee);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.service.EmployeeService#deleteService(java.lang.Long)
     */
    @Override
    public boolean deleteService(Long id) {
	return employeeDao.deleteDao(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.service.EmployeeService#getService(java.lang.Long)
     */
    @Override
    public Employee getService(Long id) {
	return employeeDao.getDao(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.service.EmployeeService#getListService()
     */
    @Override
    public List<Employee> getListService() {
	return employeeDao.getListDao();
    }

}
