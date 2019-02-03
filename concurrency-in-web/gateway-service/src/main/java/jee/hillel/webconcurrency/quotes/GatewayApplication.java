package jee.hillel.webconcurrency.quotes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import jee.hillel.webconcurrency.domain.Quote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @GetMapping("/collectQuotes")
  public List<Quote> runSynchronously() {
    return List.of(
        getSlowRandomQuote(),
        getSlowRandomQuote(),
        getFastRandomQuote()
    );
  }

  @GetMapping("/collectQuotes2")
  public Callable<List<Quote>> runAsynchronously_sequence() {
    return () -> List.of(
        getSlowRandomQuote(),
        getSlowRandomQuote(),
        getFastRandomQuote()
    );
  }

  @GetMapping("/collectQuotes3")
  public CompletableFuture<List<Quote>> runAsynchronously_parallel() {
    CompletableFuture<Quote> cf1 = CompletableFuture.supplyAsync(
        () -> getSlowRandomQuote()
    );
    CompletableFuture<Quote> cf2 = CompletableFuture.supplyAsync(
        () -> getSlowRandomQuote()
    );
    CompletableFuture<Quote> cf3 = CompletableFuture.supplyAsync(
        () -> getFastRandomQuote()
    );

//    List.of(1, 2, 3).parallelStream().reduce(0, (a, b) -> a * b).longValue();

    CompletableFuture<List<Quote>> combinedResult = CompletableFuture.allOf(cf1, cf2, cf3)
        .thenApplyAsync(aVoid -> List.of(cf1.join(), cf2.join(), cf3.join()));

    return combinedResult;
  }

  private Quote getSlowRandomQuote() {
    try {
      RestTemplate restTemplate = new RestTemplate();
      RequestEntity request = RequestEntity
        .get(new URI("http://localhost:9191/quotes"))
        .accept(MediaType.APPLICATION_JSON)
        .build();

      return restTemplate.exchange(request, Quote.class).getBody();
    } catch (URISyntaxException e) {
      return Quote.NULL;
    }
  }

  private Quote getFastRandomQuote() {
    try {
      RestTemplate restTemplate = new RestTemplate();
      RequestEntity request = RequestEntity
          .get(new URI("http://localhost:9292/quotes"))
          .accept(MediaType.APPLICATION_JSON)
          .build();

      return restTemplate.exchange(request, Quote.class).getBody();
    } catch (URISyntaxException e) {
      return Quote.NULL;
    }
  }



}
