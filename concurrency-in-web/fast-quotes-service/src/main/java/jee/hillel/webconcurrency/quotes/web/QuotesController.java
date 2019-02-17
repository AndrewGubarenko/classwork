package jee.hillel.webconcurrency.quotes.web;

import jee.hillel.webconcurrency.domain.Quote;
import jee.hillel.webconcurrency.quotes.services.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotesController {

  private final QuotesService quotesService;

  @Autowired
  public QuotesController(QuotesService quotesService) {
    this.quotesService = quotesService;
  }

  @GetMapping(path = "/quotes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Quote getRandomQuote() {
    return quotesService.getRandomQuote();
  }
}
