package org.SWP391_FUHL_SE1825.FDMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.SWP391_FUHL_SE1825.FDMS.repository")
@EnableScheduling
public class FdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdmsApplication.class, args);
	}

}
