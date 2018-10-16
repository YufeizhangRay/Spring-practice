package cn.zyf.spring.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

class JDBCTest {

	private ApplicationContext applicationContext = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	
	{ 
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		employeeDao = applicationContext.getBean(EmployeeDao.class);
		departmentDao = applicationContext.getBean(DepartmentDao.class);
		namedParameterJdbcTemplate = applicationContext.getBean(NamedParameterJdbcTemplate.class);
	}
	
	/**
	 * 实际项目常用:
	 * 使用具名参数时 可以使用 update(String sql, SqlParameterSource paramSource) 方法进行更新操作
	 * 1. SQL 语句中的参数名和类的属性一致
	 * 2. 使用 SqlParameterSource 的 BeanPropertySqlParameterSource 实现类作为参数
	 */
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "INSERT INTO employees(Last_Name,Email,Dept_ID) "
				+ "VALUES(:lastName,:email,:deptID)";
		
		Employee employee = new Employee();
		employee.setLastName("ABC");
		employee.setEmail("abc@163.com");
		employee.setDeptID(3);
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	/**
	 * 可以为参数起名字
	 * 1.好处:若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
	 * 2.缺点:较为麻烦
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "INSERT INTO employees(Last_Name,Email,Dept_ID) VALUES(:ln,:email,:dept_id)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ln", "FF");
		paramMap.put("email", "ff@163.com");
		paramMap.put("dept_id", 2);
		
		namedParameterJdbcTemplate.update(sql, paramMap);
		
	}
	
	@Test
	public void testDepartmentDao() {
		System.out.println(departmentDao.get(1));
	}
	
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.get(1));
	}
	
	/**
	 * 获取单个列的值或做统计查询
	 * 使用 queryForObject(String sql, Class<Long> requiredType) 方法
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "SELECT count(ID) FROM employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	/**
	 * 查到实体类的集合
	 * 注意调用的不是 QueryForList 方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "SELECT ID,Last_Name lastName,Email FROM employees WHERE ID > ?"; 
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 5);
		
		System.out.println(employees);
	}
	
	/**
	 * 从数据库中获取一条记录，实际得到对应的一个对象
	 * 注意不是调用 queryForObject(String sql, Class<Employee> requiredType, Object... args) 方法
	 * 而需要调用 queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1.其中的 RowMapper 指定如何去映射结果集的行，常用的实现类为 BeanPropertyRowMapper
	 * 2.使用 SQL 中列的别名完成列名和类的属性名的映射 例如 Last_Name lastName
	 * 3.不支持级联属性.JdbcTemplate 到底是一个 JDBC 的小工具，不是 ORM 框架  
	 */
	@Test
	public void testQueryForObject() {
		String sql = "SELECT ID,Last_Name lastName,Email,Dept_ID From employees WHERE ID = ?";
		
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		
		System.out.println(employee);
	}
	
	/**
	 * 执行批量更新: 批量的 insert update delete
	 * 后面最后一个参数是 Object[] 的 List 类型: 因为修改一条记录需要一个 Object 的数组,多条则需要多个 Object 的数组
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO employees(Last_Name,Email,Dept_ID) VALUES(?,?,?)";
		
		List<Object[]> batchArgs = new ArrayList<>();
		
		batchArgs.add(new Object[] {"AA","aa@163.com",1});
		batchArgs.add(new Object[] {"BB","bb@163.com",2});
		batchArgs.add(new Object[] {"CC","cc@163.com",3});
		batchArgs.add(new Object[] {"DD","dd@163.com",3});
		batchArgs.add(new Object[] {"EE","ee@163.com",2});
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 执行 insert update delete
	 */
	@Test
	public void upDate() {
		String sql = "UPDATE employees SET Last_Name = ? where ID = ?";
		jdbcTemplate.update(sql, "Jack", 5);
	}
	
	@Test
	void testDataSource() throws SQLException {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
