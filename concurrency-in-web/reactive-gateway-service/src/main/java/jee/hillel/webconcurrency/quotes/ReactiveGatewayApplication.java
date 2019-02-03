package jee.hillel.webconcurrency.quotes;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import jee.hillel.webconcurrency.domain.Quote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@RestController
public class ReactiveGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveGatewayApplication.class, args);
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

    CompletableFuture<List<Quote>> combinedResult = CompletableFuture.allOf(cf1, cf2, cf3)
        .thenApplyAsync(aVoid -> List.of(cf1.join(), cf2.join(), cf3.join()));

    return combinedResult;
  }

  @GetMapping("/collectQuotes4")
  public Mono<List<Quote>> runAsMono() {
    return Mono.just(
        List.of(
          getSlowRandomQuote(),
          getSlowRandomQuote(),
          getFastRandomQuote()
      )
    );
  }

  @GetMapping("/collectQuotes5")
  public Flux<Quote> runAsFlux() {
    return Flux.just(
        getSlowRandomQuote(),
        getSlowRandomQuote(),
        getFastRandomQuote()
    );
  }

  @GetMapping("/collectQuotes6")
  public Flux<Quote> runAsFlux_elasticScheduler() {
    return Flux.just(
        getSlowRandomQuote(),
        getSlowRandomQuote(),
        getFastRandomQuote()
    ).publishOn(Schedulers.elastic());
  }

  @GetMapping("/collectQuotes7")
  public Flux<Quote> runAsFlux_merge() {
    Mono<Quote> m1 = Mono.just(getSlowRandomQuote()).publishOn(Schedulers.elastic());
    Mono<Quote> m2 = Mono.just(getSlowRandomQuote()).publishOn(Schedulers.elastic());
    Mono<Quote> m3 = Mono.just(getFastRandomQuote()).publishOn(Schedulers.elastic());

    return Flux.merge(m1, m2, m3).publishOn(Schedulers.newElastic("my-pool-elastic"));
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

  @GetMapping(path = "/streamQuotes", produces = "application/stream+json")
  Flux<Quote> quotesStream() {

    return Flux.interval(Duration.ofSeconds(1))
        .map(i -> this.getFastRandomQuote());
  }

}
