package com.example.imbee.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

	public static final String EXCHANGE = "notification.exchange";

	public static final String NOTIFICATION_FCM = "notification.fcm";
	public static final String NOTIFICATION_DONE = "notification.done";

	@Bean
	public Declarables topicBindings() {
		Queue fcmQueue = new Queue(NOTIFICATION_FCM, false);
		Queue doneQueue = new Queue(NOTIFICATION_DONE, false);

		TopicExchange topicExchange = new TopicExchange(EXCHANGE, false, false);

		return new Declarables(
				fcmQueue,
				doneQueue,
				topicExchange,
				BindingBuilder
						.bind(fcmQueue)
						.to(topicExchange)
						.with(NOTIFICATION_FCM),
				BindingBuilder
						.bind(doneQueue)
						.to(topicExchange)
						.with(NOTIFICATION_DONE)
		);
	}

}
