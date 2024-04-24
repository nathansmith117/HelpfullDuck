package duck.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A record for storing json data about a duck from the internet.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record InternetDuck(String message, String url)
{

}
