package com.payback.payback_pirog.service;

import com.payback.payback_pirog.contrtoller.RequestDto;
import com.payback.payback_pirog.contrtoller.ResponseDto;
import com.payback.payback_pirog.model.Request;
import com.payback.payback_pirog.model.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestService.class);
    private final RequestRepository requestRepository;
    private final RequestQueue requestQueue;

    public RequestService(RequestRepository requestRepository, RequestQueue requestQueue) {
        this.requestRepository = requestRepository;
        this.requestQueue = requestQueue;
    }

    public ResponseDto process(RequestDto dto) {
        requestQueue.add(dto);

        RequestDto element;
        try {
            element = requestQueue.take();
        } catch (InterruptedException e) {
            log.error("EMPTY QUEUE");
            return new ResponseDto("QUEUE ERROR");
        }

        switch (element.getType()) {
            case "1" -> {
                saveToDatabase(element);
                return new ResponseDto("SAVED TO DATABASE");
            }
            case "2" -> {
                return new ResponseDto("REJECTED");
            }
            case "3" -> {
                saveToFile(element);
                return new ResponseDto("SAVED TO FILE");
            }
            case "4" -> {
                log.warn("LOGGED TO CONSOLE TYPE: {}", element.getType());
                return new ResponseDto("LOGGED TO CONSOLE");
            }
            default -> {
                return new ResponseDto("WRONG TYPE");
            }
        }
    }

    private void saveToDatabase(RequestDto dto) {
        Request request = new Request();
        request.setType(dto.getType());
        requestRepository.save(request);
    }

    private void saveToFile(RequestDto dto) {
        String fileName = "requests.txt";
        String generatedId = UUID.randomUUID().toString();
        String line = "[" + LocalDateTime.now() + "] ID: " + generatedId + " | TYPE: " + dto.getType();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
            log.info("Saved to file: {}", line);
        } catch (IOException e) {
            log.error("Error writing to file", e);
        }
    }
}
