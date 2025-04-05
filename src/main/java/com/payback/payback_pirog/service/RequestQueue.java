package com.payback.payback_pirog.service;

import com.payback.payback_pirog.contrtoller.RequestDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RequestQueue {

    private final BlockingQueue<RequestDto> queue = new LinkedBlockingQueue<>();

    public void add(RequestDto dto) {
        queue.offer(dto);
    }

    public RequestDto take() throws InterruptedException {
        return queue.take();
    }
}
