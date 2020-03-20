package com.grupoq.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner{
//	@Autowired
//	IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);		
	}
	@Override
	public void run(String... args)throws Exception{
		//probador
		String password="12345";
		for(int i=0; i<4; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.print(bcryptPassword);
		}
	}

}
