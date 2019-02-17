package jee.hillel.webconcurrency.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

  private final Long id;
  private final String quote;

  static public final Quote NULL = new Quote(-1L, "n/a");

  @JsonCreator
  public Quote(@JsonProperty("id") Long id, @JsonProperty("quote") String quote) {
    this.id = id;
    this.quote = quote;
  }

  public Long getId() {
    return id;
  }

  public String getQuote() {
    return quote;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "id=" + id +
        ", quote='" + quote + '\'' +
        '}';
  }
}
