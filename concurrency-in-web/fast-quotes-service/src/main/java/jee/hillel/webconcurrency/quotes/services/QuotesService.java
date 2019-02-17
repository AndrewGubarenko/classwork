package jee.hillel.webconcurrency.quotes.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import jee.hillel.webconcurrency.domain.Quote;
import jee.hillel.webconcurrency.quotes.storage.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class QuotesService {

  private final QuoteRepository quoteRepository;
  private final AtomicLong index = new AtomicLong(1);

  @Autowired
  public QuotesService(QuoteRepository quoteRepository) {
    this.quoteRepository = quoteRepository;
  }

  @PostConstruct
  public void loadQuotes() throws IOException {
    Resource resource = new ClassPathResource("/book.txt");
    Files.lines(Path.of(resource.getURI()), StandardCharsets.ISO_8859_1)
        .filter(line -> !line.isBlank())
        .forEach(this::load);
  }

  private void load(String quoteText) {
    Quote quote = new Quote(index.getAndIncrement(), quoteText);
    quoteRepository.save(quote);
  }

  public Quote getRandomQuote() {
    long maxIndex = index.longValue() - 1;
    long randomIndex = ThreadLocalRandom
        .current()
        .longs(1, maxIndex)
        .findFirst()
        .orElse(maxIndex);

    return quoteRepository.findById(randomIndex).orElseThrow();
  }
}
