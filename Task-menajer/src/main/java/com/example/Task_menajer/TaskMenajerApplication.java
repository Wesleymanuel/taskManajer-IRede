package com.example.Task_menajer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TaskMenajerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMenajerApplication.class, args);
	}

	@GetMapping("/teste")
	public String teste(){
		return "teste";
	}

}
