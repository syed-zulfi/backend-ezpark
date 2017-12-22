package com.apptech.apps.easypark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.apptech.apps.easypark.dao.HSQLEmbadedDBServer;

@SpringBootApplication(scanBasePackages={"com.apptech.apps.easypark"})
@EntityScan({"com.apptech.apps.easypark.dao.entity"})
public class Easypark {
	private static final Logger log = LoggerFactory.getLogger(Easypark.class);
	public static void main(String[] args) {
		log.info("Starting easy park application");
		SpringApplication.run(Easypark.class, args);
		HSQLEmbadedDBServer.run();
		log.info("HSQL DB started");
		
	}
}
