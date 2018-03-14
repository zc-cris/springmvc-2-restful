package com.zc.cris.springmvc.crud.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.zc.cris.springmvc.crud.dao.DeptDao;
import com.zc.cris.springmvc.crud.entities.Department;
import com.zc.cris.springmvc.crud.entities.Employee;

/**
 * 	
 * @ClassName：EmpConvertor.java
 * @Description：TODO (自定义的类型转换器，将前台特定格式的字符的转换为 Employee对象)
 * @Project Name：springmvc-2-restful
 * @Package Name: com.zc.cris.springmvc.crud.convertor
 * @Author：zc-cris
 * @version: v1.0
 * @Copyright: zc-cris
 * @email: 17623887386@163.com
 */
@Component
public class EmpConvertor implements Converter<String, Employee>{

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public Employee convert(String resource) {
		if(resource != null) {
			String[] vals = resource.split("-");
			if(vals != null && vals.length == 5) {
//				克里斯-cris.@qq.com-23-1-1
				String name = vals[0];
				String email = vals[1];
				Integer age = Integer.valueOf(vals[2]);
				String gender = vals[3];
				Department dept = this.deptDao.getDept(vals[4]);
				Employee employee = new Employee(null, name, email, age, dept, gender);
				System.out.println(resource + "===conversion===" + employee);
				return employee;
			}
		}
		return null;
	}
}
