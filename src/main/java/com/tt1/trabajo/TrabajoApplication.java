package com.tt1.trabajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"servicios", "com.tt1.trabajo"})
public class TrabajoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabajoApplication.class, args);
	}

}

//ERRORES JAVA
//:8081/favicon.ico:1  Failed to load resource: the server responded with a status of 404 ()Understand this error
//70grid?tok=1800436932:45 Uncaught TypeError: Cannot read properties of null (reading 'style')
//    at HTMLInputElement.<anonymous> (grid?tok=1800436932:45:12)Understand this errorTurn on Console insights in Settings to receive AI assistance for understanding and addressing console warnings and errors. Learn more
//59grid?tok=1800436932:45 Uncaught TypeError: Cannot read properties of null (reading 'style')
//    at HTMLInputElement.<anonymous> (grid?tok=1800436932:45:12)