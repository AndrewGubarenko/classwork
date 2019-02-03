package jee.hillel.webconcurrency.quotes.storage;

import jee.hillel.webconcurrency.domain.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<Quote, Long> {

}
