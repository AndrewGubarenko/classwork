package jee.hillel.webconcurrency.quotes.web;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import jee.hillel.webconcurrency.quotes.services.QuotesSlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class QuotesRequestHandler {
  private final QuotesSlowService quotesService;

  @Autowired
  public QuotesRequestHandler(QuotesSlowService quotesService) {
    this.quotesService = quotesService;
  }

  public Mono<ServerResponse> getRandomQuoteFn(ServerRequest request) {
    return Mono.delay(Duration.ofSeconds(4))
        .then(
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(quotesService.getRandomQuote())
        );
  }

  private int delay() {
    return ThreadLocalRandom.current()
        .ints(1, 10)
        .findFirst()
        .orElse(5);
  }
}
