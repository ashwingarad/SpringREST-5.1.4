/**
 * SpringMVC-AjaxWithHibernate
 */
package com.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.Employee;

/**
 * @author Ashwin
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDaoImpl implements EmployeeDao {

	@Resource
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.EmployeeDao#saveDao(com.model.Employee)
	 */
	@Override
	public boolean saveDao(Employee employee) {
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (employee.getId() == null)
				session.save(employee);
			else
				session.update(employee);
			transaction.commit();
			status = (transaction.getStatus().equals(TransactionStatus.COMMITTED));
		} finally {
			session.close();
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.EmployeeDao#deleteDao(java.lang.Long)
	 */
	@Override
	public boolean deleteDao(Long id) {
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			Employee employee = session.get(Employee.class, id);
			transaction = session.beginTransaction();
			session.delete(employee);
			transaction.commit();
			status = transaction.getStatus() == TransactionStatus.COMMITTED;
		} finally {
			session.close();
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.EmployeeDao#getDao(java.lang.Long)
	 */
	@Override
	public Employee getDao(Long id) {
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			employee = session.get(Employee.class, id);
		} finally {
			session.close();
		}
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.EmployeeDao#getListDao()
	 */
	@Override
	public List<Employee> getListDao() {
		List<Employee> employeeList = null;
		try {
			session = sessionFactory.openSession();

			/** deprecated (since 5.2) use NativeQuery instead. */
			String qry = "Select * from employee";
			SQLQuery<Employee> queryString = session.createSQLQuery(qry);
			queryString.addEntity(Employee.class);

			/** NativeQuery */
			NativeQuery<Employee> nativeQuery = session.createNativeQuery(qry);
			nativeQuery.addEntity(Employee.class);

			/** deprecated (since 5.2) use org.hibernate.query.Query instead */
			org.hibernate.Query<Employee> hqlquery = session.createQuery("FROM Employee");
			hqlquery.list();

			/** using org.hibernate.query.Query */
			org.hibernate.query.Query<Employee> hqlquery1 = session.createQuery("FROM Employee");
			hqlquery1.list();

			/**
			 * session.createCriteria(); deprecated (since 5.2) for Session, use the JPA
			 * Criteria
			 */
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.list();

			/**
			 * Introduced JPA Criteria for queries. i.e CriteriaBuilder, CriteriaQuery,
			 * Root, Where, From, TypedQuery etc.
			 * --------------------------------------------------------------------------
			 * You can define criteria in 2 ways. 1) Using Session bean 2) Using
			 * EnitityManager. Both examples are declared
			 */
			CriteriaQuery<Employee> query = session.getCriteriaBuilder().createQuery(Employee.class);
			query.from(Employee.class);
			employeeList = session.createQuery(query).getResultList();

			EntityManager entityManager = sessionFactory.createEntityManager();
			CriteriaQuery<Employee> query2 = entityManager.getCriteriaBuilder().createQuery(Employee.class);
			query2.from(Employee.class);
			employeeList = entityManager.createQuery(query2).getResultList();

		} finally {
			session.close();
		}
		return employeeList;
	}

}
