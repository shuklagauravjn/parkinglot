package parkingLot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main initialization application class
 */
@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "parkingLot.*")
@EnableSwagger2
@PropertySource("classpath:application.properties")
@RestController
public class Application {
	/*
	 * @Autowired ParkingLotController parkingLotController;
	 * 
	 * @RequestMapping("/setParkingSize")
	 * 
	 * @GetMapping(value = "/{size}") public String
	 * setParkinglotSize(@RequestParam(value = "size") String size) { return
	 * "Code project for Backend Engineer, GO-Life/Commerce"+size; }
	 */
	@RequestMapping("/")
	public String home() {
		return "Welcome to Parking lot";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
