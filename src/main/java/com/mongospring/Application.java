package com.mongospring;

import com.mongospring.model.User;
import com.mongospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UserRepository userRepo;

	@Override
	public void run(String... args){
		createUsers();
		displayAllUsers();
		updateUser("hitesh.narkhede");
		displayAllUsers();
		deleteAllUsers();
		//deleteById(UUID.fromString("6a39e560-b9a5-4b25-a165-3b796e0021f0"));
		//count();
		displayAllUsers();
	}

	private void count() {
		System.out.println("Users: "+ userRepo.count());
	}

	private void deleteById(UUID id) {
		userRepo.deleteById(id);
		System.out.println("User Deleted");
	}

	private void deleteAllUsers() {
		userRepo.deleteAll();
		System.out.println("All Users Deleted");
	}

	private void updateUser(String username) {
		User user = userRepo.findByUsername(username);
		user.setEmail("hitesh.narkhede@gmail.com");
		userRepo.save(user);
		System.out.println("User Updated");
	}

	private void displayAllUsers() {
		List<User> userList = userRepo.findAll();
		userList.forEach(System.out::println);
		System.out.println("Users: "+ userRepo.count());
	}

	private void createUsers() {

		List<User> userList = new ArrayList<>();

		userList.add(new User(UUID.randomUUID(), "hitesh.narkhede", "hitesh.test@gmail.com", "ABC", LocalDateTime.now()));
		userList.add(new User(UUID.randomUUID(), "piyush.narkhede", "piyush.narkhede@gmail.com", "ABC", LocalDateTime.now()));
		userList.add(new User(UUID.randomUUID(), "shubham.paunikar", "shubham.paunikar@gmail.com", "ABC", LocalDateTime.now()));
		userList.add(new User(UUID.randomUUID(), "abhishekh.shelar", "abhishekh.shelar@gmail.com", "ABC", LocalDateTime.now()));

		userRepo.saveAll(userList);
		System.out.println("Demo Users Created");
	}
}
