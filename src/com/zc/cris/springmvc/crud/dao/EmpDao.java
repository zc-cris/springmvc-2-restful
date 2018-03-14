package com.zc.cris.springmvc.crud.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zc.cris.springmvc.crud.entities.Employee;

@Repository
public class EmpDao {
	
	private static Map<String, Employee> emps = new HashMap<>();
	static {
		emps.put("1", new Employee(1, "james", "123@qq.com", 25, DeptDao.getDept("1"), "1"));
		emps.put("2", new Employee(2, "cris", "444@qq.com", 28, DeptDao.getDept("2"),"0"));
		emps.put("3", new Employee(3, "james", "123@qq.com", 25, DeptDao.getDept("3"),"0"));
		emps.put("4", new Employee(4, "james", "123@qq.com", 25, DeptDao.getDept("4"),"1"));
		emps.put("5", new Employee(5, "james", "123@qq.com", 25, DeptDao.getDept("4"),"1"));
		emps.put("6", new Employee(6, "james", "123@qq.com", 25, DeptDao.getDept("5"),"0"));
		
	}
	
	/**
	 * 
	 * @MethodName: getEmp
	 * @Description: TODO (根据id查询员工对象用于更新)
	 * @return
	 * @Return Type: Employee
	 * @Author: zc-cris
	 */
	public Employee getEmp(Integer id) {
		return (Employee) this.emps.get(Integer.toString(id));
	}
	
	private int initNum = 1001;
	/**
	 * 
	 * @MethodName: save
	 * @Description: TODO (模拟将前台新增的员工对象放入数据库或者根据用户id更新数据)
	 * @param employee
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	public void save(Employee employee) {
		//如果是新增用户，手动添加id值，如果更新用户，则进不到这个判断里面
		if(employee.getId() == null) {
			employee.setId(initNum++);
		}
		//员工此时的department 成员属性只有id属性，没有其他属性，所以需要从 DeptDao 中根据部门 id 获取
		employee.setDepartment(DeptDao.getDept(String.valueOf(employee.getDepartment().getId())));
		this.emps.put(Integer.toString(employee.getId()), employee);
	}
	
	
	/**
	 * 
	 * @MethodName: getEmps
	 * @Description: TODO (返回所有员工对象)
	 * @return
	 * @Return Type: Collection<Object>
	 * @Author: zc-cris
	 */
	public Collection<Employee> getEmps() {
		return EmpDao.emps.values();
	}


	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (根据id删除员工)
	 * @param id
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	public void delete(Integer id) {
		this.emps.remove(Integer.toString(id));
	}
	
	
	
}



