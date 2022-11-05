package com.example.imbee;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@SpringBootApplication
@EnableWebMvc
public class ImbeeApplication {

	@Value(value = "${FirebaseName}")
	String firebaseName;

	public static void main(String[] args) {
		SpringApplication.run(ImbeeApplication.class, args);
	}

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions
				.builder()
				.setCredentials(googleCredentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, firebaseName);
		return FirebaseMessaging.getInstance(app);
	}

}
