package com.service.booking.rental.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256;

@SpringBootApplication
public class ServiceBookingRentalPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBookingRentalPlatformApplication.class, args);

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("", 8, 185000, PBKDF2WithHmacSHA256);

		encoders.put("pbkdf2", pbkdf2PasswordEncoder);

		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

		String pass = passwordEncoder.encode("123456");
		System.out.println("hash: " + pass);
	}

}
