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
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.platform.search.SearchProcessRequest;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;

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
    public PersonOutDto createPerson(PersonInDto person) {
        return restTemplate.postForObject(URI.create(srsUri + "/person"), person, PersonOutDto.class);
    }

    @Override
    public List<RoleOutDto> rolesDictionary() {
        return restTemplate.exchange(
                URI.create(srsUri + "/person/roles"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RoleOutDto>>() {
                }).getBody();
    }

    @Override
    public RequestOutDto submitRequest(RequestInDto request) {
        return restTemplate.postForObject(URI.create(srsUri + "/request"), request, RequestOutDto.class);
    }

    @Override
    public RequestOutDto findRequest(String requestId) {
        return restTemplate.getForObject(URI.create(srsUri + "/request/" + requestId), RequestOutDto.class);
    }

    @Override
    public void assignRequest(String requestId, String supervisorId) {
        restTemplate.put(URI.create(srsUri + "/request/" + requestId + "/assign?supervisorId=" + supervisorId), null);
    }

    @Override
    public void approveRequest(String requestId, String supervisorId) {
        restTemplate.put(URI.create(srsUri + "/request/" + requestId + "/approve?supervisorId=" + supervisorId), null);
    }

    @Override
    public void approveRequestItem(String requestItemId) {
        restTemplate.put(URI.create(srsUri + "/request-item/" + requestItemId + "/approve"), null);
    }

    @Override
    public SearchProcessOutDto search(String requestId) {
        SearchProcessRequest request = new SearchProcessRequest();
        request.setRequestId(requestId);
        return restTemplate.postForObject(srsUri + "/search-process/search", request, SearchProcessOutDto.class);
    }
}
