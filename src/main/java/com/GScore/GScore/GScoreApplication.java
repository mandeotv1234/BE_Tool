package com.GScore.GScore;

import com.GScore.GScore.infrastructure.seeding.ExamResultSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GScoreApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(GScoreApplication.class, args);
		ExamResultSeeder seeder = context.getBean(ExamResultSeeder.class);
		seeder.seed(); // gọi seed ở đây
		context.close(); // đóng app sau khi seed
	}
}
