package net.javaguides.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication	(exclude= {DataSourceAutoConfiguration.class})
public class StudentDatabaseManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDatabaseManagementSystemApplication.class, args);
	}

}
