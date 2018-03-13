package com.zc.cris.springmvc.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.prism.impl.BaseMesh.FaceMembers;
import com.zc.cris.springmvc.crud.dao.DeptDao;
import com.zc.cris.springmvc.crud.dao.EmpDao;
import com.zc.cris.springmvc.crud.entities.Employee;

@Controller
public class EmpContoller {
	
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DeptDao deptDao;
	
	/**
	 * 
	 * @MethodName: getEmp
	 * @Description: TODO (每访问目标带有 pojo 入参的方法时，就会先调用这个带有 @ModelAttribute 注解的方法，来将 pojo类型的
	 * 						对象（空白对象或者是从数据库查询的对象）率先放进springMVC的独有的map容器中)
	 * @param id
	 * @param map
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	@ModelAttribute
	public void getEmp(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if(id != null) {
			//说明前台传来的是修改请求
			Employee employee = this.empDao.getEmp(id);
			//将从数据库查询出来的对象放入到map容器中，默认会将空白的Employee 对象放进 Map
			map.put("employee", employee);
		}
	}
	
	
	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新用户数据)
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp", method=RequestMethod.PUT)
	public String update(Employee employee) {
		//这个目标方法的 pojo 类型参数如果没有 @ModelAttribute 注解修饰指定value，默认会以 pojo 第一个字母小写作为 key 去map容器中查找 该pojo 类型的对象
		this.empDao.save(employee);
		return "redirect:/list";
	}
	
	/**
	 * 
	 * @MethodName: input
	 * @Description: TODO (先将用户查询出来并显示在页面上)
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		Employee employee = empDao.getEmp(id);
		map.put("employee", employee);
		map.put("depts", this.deptDao.getDepts());
		return "input";
	}
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (将前台传来的经过转换后的delete请求中的id参数放入目标方法的入参中，并根据id删除员工)
	 * @param id
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		empDao.delete(id);
		return "redirect:/list";
	}
	
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: TODO (保存新员工信息)
	 * @param employee
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp",method=RequestMethod.POST)
	public String save(Employee employee) {
		empDao.save(employee);
		return "redirect:/list";
	}
	
	/**
	 * 
	 * @MethodName: input
	 * @Description: TODO (跳转至新增员工页面)
	 * @param map
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		//需要从数据库查询部门信息并且放入到requestScope中
		map.put("depts", deptDao.getDepts());
		//需要为前台的jsp页面的form标签提供 modelAttribute 属性值
		map.put("employee", new Employee());
		return "input";
	}
	
	
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: TODO (查询所有员工)
	 * @param map
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("list")
	public String list(Map<String, Object> map) {
		map.put("emps", this.empDao.getEmps());
		return "list";
	}
	
	
	
}
