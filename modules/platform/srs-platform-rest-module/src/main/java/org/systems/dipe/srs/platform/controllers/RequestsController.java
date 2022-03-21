package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/api/request/{requestId}")
    public RequestOutDto findRequest(@PathVariable String requestId) {
        return requestFacade.findRequest(requestId);
    }

    @PutMapping("/api/request/{requestId}/assign")
    public void assignRequest(@PathVariable String requestId, @RequestParam String supervisorId) {
        //TODO take supervisorId from security context
        requestFacade.assign(requestId, supervisorId);
    }

    @PutMapping("/api/request/{requestId}/approve")
    public void approveRequest(@PathVariable String requestId, @RequestParam String supervisorId) {
        //TODO take supervisorId from security context
        requestFacade.approveItem(requestId, supervisorId);
    }

    @PutMapping("/api/request-item/{requestItemId}/approve")
    public void approveRequestItem(@PathVariable String requestItemId) {
        requestFacade.approveItem(requestItemId);
    }

    @PutMapping("/api/request-item/{requestItemId}/dismiss")
    public void dismissRequestItem(@PathVariable String requestItemId) {
        requestFacade.dismissItem(requestItemId);
    }

}
