package com.payback.payback_pirog.service;

import com.payback.payback_pirog.contrtoller.RequestDto;
import com.payback.payback_pirog.model.Request;
import com.payback.payback_pirog.model.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void save(RequestDto dto) {
        Request request = new Request();
        request.setType(dto.getType());
        requestRepository.save(request);
    }
}
