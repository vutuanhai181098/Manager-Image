package com.example.managerfile;

import com.example.managerfile.entities.User;
import com.example.managerfile.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagerFileApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Test
	void save_users() {
		Faker faker = new Faker();
		for (int i = 0; i < 20; i++) {
			User user = new User(
					null,
					faker.name().fullName()
			);
			userRepository.save(user);
		}
	}

}
