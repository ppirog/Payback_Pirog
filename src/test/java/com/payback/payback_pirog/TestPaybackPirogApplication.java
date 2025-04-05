package com.payback.payback_pirog;

import org.springframework.boot.SpringApplication;

public class TestPaybackPirogApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaybackPirogApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
