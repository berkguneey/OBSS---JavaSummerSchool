package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> getUsers() {
		String sql = "SELECT * FROM user";
		List<User> users = new ArrayList<User>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			user.setUser_id((int) (row.get("user_id")));
			user.setUser_name((String) (row.get("user_name")));
			user.setUser_surname((String) (row.get("user_surname")));
			user.setUser_email((String) (row.get("user_email")));
//			user.setUser_password((String) (row.get("user_password")));
			user.setUser_role((String) (row.get("user_role")));
			users.add(user);
		}
		return users;
	}

	@Override
	public Boolean insertUser(User u) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(u.getUser_password());  
		u.setUser_password(encodedPassword);
		String sql = "INSERT INTO user "
				+ "(user_name, user_surname, user_email, user_password, user_role) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, new Object[] { u.getUser_name(), u.getUser_surname(), u.getUser_email(),
				u.getUser_password(), u.getUser_role() });
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateUser(int id, User u) {
		String sql = "UPDATE user SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ? WHERE user_id=?";
		// String sql = "UPDATE user SET user_name= '" + u.getUser_name() + "',
		// user_surname='" + u.getUser_surname()
		// + "', user_email='" + u.getUser_email() + "', user_password='" +
		// u.getUser_password() + "', user_role='"
		// + u.getUser_role() + "' WHERE user_id='" + id;
		int result = jdbcTemplate.update(sql, new Object[] { u.getUser_name(), u.getUser_surname(), u.getUser_email(),
				u.getUser_password(), u.getUser_role(), id });
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean deleteUser(int id) {
		String sql = "DELETE FROM user WHERE user_id='" + id + "'";
		int result = jdbcTemplate.update(sql);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getUserIdByEmail(String email) {
		String sql = "SELECT user_id FROM user WHERE user_email='" + email + "'";
		int id = jdbcTemplate.queryForObject(sql, int.class);
		return id;
	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE user_id='"+id+"' LIMIT 1";
		Map<String, Object> row = null;
		try {
			row = jdbcTemplate.queryForMap(sql);
		}catch(Exception e) {
			return null;
		}
		if(row==null) {
			return null;
		}
		User user = new User();
		user.setUser_id((int) (row.get("user_id")));
		user.setUser_name((String) row.get("user_name"));
		user.setUser_surname((String) row.get("user_surname"));
		user.setUser_email((String) row.get("user_email"));
		user.setUser_password((String) row.get("user_password"));
		user.setUser_role((String) row.get("user_role"));
		return user;
	}

	@Override
	public List<User> getUserByName(String username) {
		String sql = "SELECT * FROM user WHERE user_name LIKE '%"
				+ username + "%' or user_surname LIKE '%" + username + "%'";
		List<User> books = new ArrayList<User>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			user.setUser_id((int) (row.get("user_id")));
			user.setUser_name((String) (row.get("user_name")));
			user.setUser_surname((String) (row.get("user_surname")));
			user.setUser_email((String) (row.get("user_email")));
			user.setUser_password((String) row.get("user_password"));
			user.setUser_role((String) row.get("user_role"));
			books.add(user);
		}
		return books;
	}

}
