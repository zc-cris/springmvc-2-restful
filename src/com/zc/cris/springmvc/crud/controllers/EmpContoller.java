package com.zc.cris.springmvc.crud.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.prism.impl.BaseMesh.FaceMembers;
import com.zc.cris.springmvc.crud.dao.DeptDao;
import com.zc.cris.springmvc.crud.dao.EmpDao;
import com.zc.cris.springmvc.crud.entities.Employee;
import com.zc.cris.springmvc.crud.exception.NotFindUserNameAndPasswordException;

@Controller
public class EmpContoller {
	
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private ResourceBundleMessageSource resoure;
	
	/**
	 * 
	 * @MethodName: testSimpleMappingExceptionResolver
	 * @Description: TODO (测试通过 xml 配置的方式统一处理异常（通过 SimpleMappingExceptionResolver 类）)
	 * @param i
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i) {
		
		String[] strs = new String[10];
		System.out.println(strs[i]);
		
		return "success";
	}
	
	
	/**
	 * 
	 * @MethodName: testDefaultHandlerExceptionResolver
	 * @Description: TODO (测试 DefaultHandlerExceptionResolver 类对于常见异常的处理)
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="testDefaultHandlerExceptionResolver", method=RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver() {
		System.out.println("正常执行！");
		return "success";
	}
	
	
	
	/**
	 * 
	 * @MethodName: testNotFindUserNameAndPasswordException
	 * @Description: TODO (测试通过 @responseStatus 注解处理抛出的自定义异常)
	 * @param i
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("testNotFindUserNameAndPasswordException")
	public String testNotFindUserNameAndPasswordException(@RequestParam("i") Integer i) {
		
		if(i == 10) {
			throw new NotFindUserNameAndPasswordException();
		}
		
		System.out.println("没有异常，正常执行");
		return "success";
	}
	
	
	/**
	 * 
	 * @MethodName: handException
	 * @Description: TODO (测试异常的优先级，越具体的异常优先级越高)
	 * @param exception
	 * @return
	 * @Return Type: ModelAndView
	 * @Author: zc-cris
	 */
//	@ExceptionHandler({Exception.class})
//	public ModelAndView handException(Exception exception) {
//		System.out.println("-----------------" + exception.getMessage());
//		
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", exception);
//		return mv;
//		
//	}
	
	/**
	 * 
	 * @MethodName: getArithmeticException
	 * @Description: TODO (处理算数异常的方法)
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	/*
	 * 1. 在 @ExceptionHandler 注解方法的入参中可以加入 Exception 类型的参数，该参数对应捕获的异常对象类型
	 * 2. 在@ExceptionHandler 方法的入参中，不能使用 Map类型的参数，如果希望把异常信息显示在jsp页面上，需要
	 * 		使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级的问题
	 * 4. 实际开发中，都是将异常方法独立出来，放在特定的异常类中，而不会放在我们的controller中,
	 * 		这个特定处理异常的类我们使用 @ControllerAdvice 标记，即如果在当前 controller 中无法找到 @ExceptionHandler
	 * 		方法处理异常，就会去 @ControllerAdvice 标记的类中查找  @ExceptionHandler 标记的方法来处理异常
	 */
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handArithmeticException(Exception exception) {
		
		System.out.println(exception.getMessage());
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", exception);
		return mv;
	}
	
	
	/**
	 * 
	 * @MethodName: testExceptionHandler
	 * @Description: TODO (测试 springMVC 处理异常的流程)
	 * @param i
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("testExceptionHandler")
	public String testExceptionHandler(@RequestParam("i") Integer i) {
		int a = 10/i;
		System.out.println(a);
		return "success";
	}
	
	
	
	/**
	 * 
	 * @MethodName: testFileUpload
	 * @Description: TODO (通过springMVC 实现文件的上传)
	 * @param desc
	 * @param file
	 * @return
	 * @throws IOException 
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, 
			@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		System.out.println("desc:" + desc);
		System.out.println("file's originalName:"+file.getOriginalFilename());
		System.out.println("file's inputStream:" + file.getInputStream());
		System.out.println("file's size:" + file.getSize());
		
		//将上传文件保存到指定的目录
		ServletContext servletContext = session.getServletContext();
		String path = servletContext.getRealPath("/file/"+file.getOriginalFilename());
		FileOutputStream outputStream = new FileOutputStream(path);
		InputStream inputStream = file.getInputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		inputStream.close();
		return "success";
	}
	
	
	
	/**
	 * 
	 * @MethodName: testi18n
	 * @Description: TODO (测试从前台获取locale并且打印想要的国家化消息)
	 * @param locale
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("i18n2")
	public String testi18n(Locale locale) {
		String message = resoure.getMessage("i18n.name", null, locale);
		System.out.println(message);
		return "i18n";
	}
	
	
	/**
	 * 
	 * @MethodName: testResponseEntity
	 * @Description: TODO (测试springMVC 根据ResponseEntity<T> 的泛型选择对应的HttpMessageConverter 实现类来处理后台返回给前台的数据)
	 * @param session
	 * @return
	 * @throws IOException
	 * @Return Type: ResponseEntity<byte[]>
	 * @Author: zc-cris
	 */
	@RequestMapping("testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		
		//读取文件并且放入 ResponseEntity 中
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/file/a.txt");
		body = new byte[in.available()];
		in.read(body);
		
		//设置响应头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		//设置响应玛
		HttpStatus status = HttpStatus.OK;
		
		ResponseEntity<byte[]> responseEntity = 
				new ResponseEntity<byte[]>(body, headers, status);
				
		return responseEntity;
	}
	
	/**
	 * 
	 * @MethodName: testHttpMessageConverter
	 * @Description: TODO (测试使用@ResponseBody 和 @RequestBody 注解完成前后端数据交互，模拟文件上传即后台回应)
	 * @param body
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@ResponseBody
	@RequestMapping("testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		//将上传的文件转换为String类型打印出来
		System.out.println(body);
		return "hello!"+new Date();
	}
	
	
	
	
	
	/**
	 * 
	 * @MethodName: testJson
	 * @Description: TODO (向前台返回json格式的数据)
	 * @return
	 * @Return Type: Collection<Employee>
	 * @Author: zc-cris
	 */
	@ResponseBody
	@RequestMapping("testJson")
	public Collection<Employee> testJson(){
		return this.empDao.getEmps();
	}
	
	
	/**
	 * 
	 * @MethodName: initBinder
	 * @Description: TODO (测试初始化binder的时候，指定springMVC框架不对表单中的某个属性赋值给对应的 pojo，而是我们自己处理
	 * 						类似于添加用户时的角色id由我们自己生成对应的role集合)
	 * @param binder
	 * @Return Type: void
	 * @Author: zc-cris
	 */
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setDisallowedFields("name");
//	}
	
	
	
	/**
	 * 
	 * @MethodName: testConversion
	 * @Description: TODO (测试自定义的类型转换器好不好用)
	 * @param employee
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping("testConversion")
	public String testConversion(@RequestParam("employee") Employee employee) {
		System.out.println("conversion:" + employee);
		this.empDao.save(employee);
		return "redirect:/list";
	}
	
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
	public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map) {
		//如果数据类型格式化失败，那么错误对象会被放入到 BindingResult 中
		if(result.getErrorCount() > 0) {
			for(FieldError error : result.getFieldErrors()) {
				//打印错误字段和默认的错误提示消息
				System.out.println(error.getField()+"----------"+error.getDefaultMessage());
			}
			//若验证失败，就转向定制的页面
			map.put("depts", deptDao.getDepts());
			return "input";
			
		}
		
		empDao.save(employee);
		System.out.println(employee);
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
