package hello;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class UserApplication {

  @LoadBalanced
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/getAPOD")
  public RedirectView hi() {
    String json = this.restTemplate.getForObject("http://say-hello/getAPOD", String.class);
    
    ObjectMapper obj = new ObjectMapper();
    APODPojo readValue;
    RedirectView redirectView = new RedirectView();
	try {
		readValue = obj.readValue(json, APODPojo.class);
		redirectView.setUrl(readValue.getUrl());
	} catch (IOException e) {
		e.printStackTrace();
	}
    return redirectView;
  }

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}

