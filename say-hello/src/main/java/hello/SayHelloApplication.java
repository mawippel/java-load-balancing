package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class SayHelloApplication {

  private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);
  
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
  
  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping(value = "/getAPOD")
  public String greet() {
    log.info("Access /getAPOD");
    return restTemplate.getForObject("https://api.nasa.gov/planetary/apod?api_key=I1coKUmuLOskX5xb9FkHQ5a4283xpB6aNk0omv8b", String.class);
  }

  @RequestMapping(value = "/")
  public String home() {
    log.info("Ribbon verificando a saude da instancia...");
    return "Olá!";
  }

  public static void main(String[] args) {
    SpringApplication.run(SayHelloApplication.class, args);
  }
}
