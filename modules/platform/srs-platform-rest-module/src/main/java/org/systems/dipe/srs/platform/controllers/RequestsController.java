package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.systems.dipe.srs.platform.external.RequestFacade;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;

@RestController
@AllArgsConstructor
public class RequestsController {

    private final RequestFacade requestFacade;

    @PostMapping("/api/request")
    public RequestOutDto submitRequest(@RequestBody RequestInDto request) {
        return requestFacade.submitRequest(request);
    }

}
