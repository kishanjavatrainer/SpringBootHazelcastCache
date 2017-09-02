package com.infotech.book.ticket.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TicketBookingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingManagementApplication.class, args);
	}
}
