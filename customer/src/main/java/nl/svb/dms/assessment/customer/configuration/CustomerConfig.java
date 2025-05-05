package nl.svb.dms.assessment.customer.configuration;

import nl.svb.dms.api.ApiClient;
import nl.svb.dms.api.leaseacar.LeaseControllerApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    private static final String BASEPATH = "http://localhost:8081";
    @Bean
    public LeaseControllerApi leaseApi() {
        return new LeaseControllerApi(apiClient());
    }

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(BASEPATH);
        return apiClient;
    }

}
