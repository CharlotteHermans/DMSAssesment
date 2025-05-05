package nl.svb.dms.assessment.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "customer api", version = "1.0", description = "API voor het opvragen van klant gegevens",
		contact = @Contact(name = "IT Sociaal")),
		tags = {@Tag(name = "customer-controller")
		})
@ComponentScan(basePackages = {"nl.svb.dms"})

public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
