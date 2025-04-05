package com.payback.payback_pirog.contrtoller;

import com.payback.payback_pirog.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> process(@RequestBody RequestDto request) {
        ResponseDto response = requestService.process(request);
        return ResponseEntity.ok(response);
    }
}
