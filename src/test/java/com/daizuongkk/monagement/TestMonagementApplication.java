package com.daizuongkk.monagement;

import org.springframework.boot.SpringApplication;

public class TestMonagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(MonagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
