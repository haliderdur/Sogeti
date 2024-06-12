package api.zippopotam.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZippoPojo {

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    @JsonProperty("country")
    private String country;

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("places")
    private List<Places> places;


    // Nested Place class
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Places {

        @JsonProperty("place name")
        private String placeName;

        @JsonProperty("longitude")
        private String longitude;

        @JsonProperty("latitude")
        private String latitude;

        @JsonProperty("state")
        private String state;

        @JsonProperty("state abbreviation")
        private String stateAbbreviation;

        @JsonProperty("post code")
        private String postCode;
    }
}

