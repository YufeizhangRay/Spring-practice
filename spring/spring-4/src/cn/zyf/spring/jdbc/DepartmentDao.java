package cn.zyf.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 不推荐使用 JdbcDaoSupport，而推荐直接使用 JdbcTemplate 作为 DAO 类的成员
 * @author ray_cn
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport{

	@Autowired
	public void setDateSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public Department get(Integer id) {
		String sql = "SELECT ID,Dept_Name name FROM departments WHERE ID = ?";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		return getJdbcTemplate().queryForObject(sql, rowMapper,id);	 
	}
	
}
