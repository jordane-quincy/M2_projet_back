package org.istv.ske;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.istv.ske"})
public class SkeApplication {

	public static void main(String[] args){
		SpringApplication.run(SkeApplication.class, args); 
	}

}
