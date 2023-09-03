package com.moviebooking.theatre.theatreonboard;

import com.moviebooking.theatre.theatreonboard.configuration.BookingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(BookingProperties.class)
public class TheatreonboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheatreonboardApplication.class, args);
	}

}
