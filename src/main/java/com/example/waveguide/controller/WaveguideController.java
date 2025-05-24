package com.example.waveguide.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.service.entity.Layer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet("/waveguide/calculate")
public class WaveguideController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // Read JSON from request body
        List<Layer> layers = objectMapper.readValue(request.getReader(), 
            objectMapper.getTypeFactory().constructCollectionType(List.class, Layer.class));

        ProcessBuilder builder = new ProcessBuilder("python", "python/waveguide_runner.py");
        Process process = builder.start();

        // Write JSON to stdin
        try (OutputStream stdin = process.getOutputStream()) {
            objectMapper.writeValue(stdin, layers);
        }

        try {
            process.waitFor(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Read stdout
        try (InputStream stdout = process.getInputStream()) {
            String resultJson = new String(stdout.readAllBytes(), StandardCharsets.UTF_8);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resultJson);
        }
    }
}