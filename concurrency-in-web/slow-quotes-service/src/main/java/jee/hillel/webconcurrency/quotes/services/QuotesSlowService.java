package jee.hillel.webconcurrency.quotes.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.PostConstruct;
import jee.hillel.webconcurrency.domain.Quote;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class QuotesSlowService {

  private List<String> quotes = new ArrayList<>();

  @PostConstruct
  public void loadQuotes() throws IOException {
    Resource resource = new ClassPathResource("/book.txt");
    Files.lines(Path.of(resource.getURI()), StandardCharsets.ISO_8859_1)
        .filter(line -> !line.isBlank())
        .forEach(quotes::add);
  }

  public Quote getRandomQuote() {
    int randomIndex = ThreadLocalRandom
        .current()
        .ints(1, quotes.size() - 1)
        .findFirst()
        .orElse(quotes.size() - 1);

    String quoteText = quotes.get(randomIndex);
    return new Quote(0L, quoteText);
  }
}
