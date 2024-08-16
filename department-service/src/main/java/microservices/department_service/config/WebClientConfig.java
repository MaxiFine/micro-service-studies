package microservices.department_service.config;


import microservices.department_service.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl("http://employee-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public EmployeeClient employeeClient() {
        HttpServiceProxyFactory proxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(employeeWebClient()))
                .build();
        return proxyFactory.createClient(EmployeeClient.class);
    }

//    @Bean
//    public EmployeeClient employeeClient() {
//        // Use the static factory method 'HttpServiceProxyFactory.create()'
//        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(WebClient.builder().baseUrl("http://localhost:8080").build())
//                .build();
//        return proxyFactory.createClient(EmployeeClient.class);
//    }


}


