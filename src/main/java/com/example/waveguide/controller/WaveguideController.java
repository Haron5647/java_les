package com.example.waveguide.controller;

import org.springframework.web.bind.annotation3.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entity.Layer;

import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/waveguide")
public class WaveguideController {

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody List<Layer> layers) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("python", "python/waveguide_runner.py");
        Process process = builder.start();

        // Write JSON to stdin
        try (OutputStream stdin = process.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(stdin, layers);
        }

        // Read stdout
        process.waitFor(30, TimeUnit.SECONDS);
        try (InputStream stdout = process.getInputStream()) {
            String resultJson = new String(stdout.readAllBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(resultJson);
        }
    }
}