package org.systems.dipe.srs.test.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
        return restTemplate.postForObject(URI.create(srsUri + "/person"), person, PersonDto.class);
    }

    @Override
    public List<RoleDto> rolesDictionary() {
        return restTemplate.exchange(
                URI.create(srsUri + "/person/roles"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RoleDto>>() {
                }).getBody();
    }
}
