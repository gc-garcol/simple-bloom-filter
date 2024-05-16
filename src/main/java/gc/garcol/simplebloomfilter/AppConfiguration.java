package gc.garcol.simplebloomfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public BloomFilter<String> textFilter() {
    return new BloomFilter<>(100,
        text -> text.hashCode() * 31,
        text -> {
          final int prime = 37;
          int hashCode = 0;
          for (int i = 0; i < text.length(); i++) {
            hashCode = prime * hashCode + text.charAt(i);
          }
          return hashCode;
        },
        String::hashCode
    );
  }

}
