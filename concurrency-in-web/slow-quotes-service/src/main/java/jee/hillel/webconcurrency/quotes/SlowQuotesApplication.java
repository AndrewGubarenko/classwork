package jee.hillel.webconcurrency.quotes;

import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import jee.hillel.webconcurrency.quotes.web.QuotesRequestHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
public class SlowQuotesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SlowQuotesApplication.class, args);
  }

  @Bean
  public RouterFunction<?> route(QuotesRequestHandler quotesRequestHandler) {
    return RouterFunctions.route(
        RequestPredicates.GET("/quotes"),
        quotesRequestHandler::getRandomQuoteFn
    );
  }
}
