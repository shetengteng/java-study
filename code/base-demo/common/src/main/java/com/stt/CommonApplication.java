package com.stt;

import com.stt.compiler.DynamicCompiler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
		System.out.println(DynamicCompiler.DEFAULT_COMPLIE_DIRECTORY);
	}

}

