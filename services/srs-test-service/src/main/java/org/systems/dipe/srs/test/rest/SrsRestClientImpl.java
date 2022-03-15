package org.systems.dipe.srs.test.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.systems.dipe.srs.platform.people.PersonDto;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class SrsRestClientImpl implements SrsRestClient {

    private final RestTemplate restTemplate;
    private final URI srsUri;

    public SrsRestClientImpl(
            ObjectMapper objectMapper,
            RestTemplateBuilder restTemplateBuilder,
            @Value("${srs.platform.host}") String srsPlatformHost
    ) throws URISyntaxException {
        restTemplate = restTemplateBuilder
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
        srsUri = new URI(srsPlatformHost);
    }

    @Override
    public PersonDto registerNewPerson(PersonDto person) {
        URI uri = URI.create(srsUri + "/person");

        ResponseEntity<PersonDto> responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(uri, person, PersonDto.class);
        } catch (RestClientException e) {
            log.error("Cannot register new person [{}], cause {}", uri, e.getMessage());
            throw new IllegalArgumentException("Cannot register new person", e);
        }

        return responseEntity.getBody();
    }
}
