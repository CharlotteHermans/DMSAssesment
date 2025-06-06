package nl.svb.dms.assessment.lease;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "lease a car api", version = "1.0", description = "API voor het opvragen van lease auto gegevens",
		contact = @Contact(name = "IT Sociaal")),
		tags = {@Tag(name = "lease-controller")
		})
@ComponentScan(basePackages = {"nl.svb.dms"})
public class LeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaseApplication.class, args);
	}

}
