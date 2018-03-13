package com.zc.cris.springmvc.crud.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zc.cris.springmvc.crud.entities.Department;

@Repository
public class DeptDao {
	
	private static Map<String, Object> depts = new HashMap<>();
	static {
		depts.put("1", new Department(1, "管理部"));
		depts.put("2", new Department(2, "市场部"));
		depts.put("3", new Department(3, "人力部"));
		depts.put("4", new Department(4, "前台部"));
		depts.put("5", new Department(5, "后勤部"));
	}
	/**
	 * 
	 * @MethodName: getDept
	 * @Description: TODO (根据id返回部门对象)
	 * @param id
	 * @return
	 * @Return Type: Department
	 * @Author: zc-cris
	 */
	public static Department getDept(String id) {
		return (Department) depts.get(id);
	}
	/**
	 * 
	 * @MethodName: getEmps
	 * @Description: TODO (返回所有部门对象)
	 * @return
	 * @Return Type: Collection<Object>
	 * @Author: zc-cris
	 */
	public Collection<Object> getDepts() {
		return this.depts.values();
	}
	
	
	
	
	
}
