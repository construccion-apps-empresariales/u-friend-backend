package com.ufriend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UFriendApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(UFriendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "CREATE TABLE TEST_TABLE(" +
				"id INT PRIMARY KEY," +
				"name VARCHAR(30));" +
				"" +
				"INSERT INTO TEST_TABLE(id, name) VALUES (1, 'Test Value')";

		int rows = jdbcTemplate.update(sql);
		if (rows > 0) {
			System.out.println("A new row has been inserted.");
		}
	}
}
