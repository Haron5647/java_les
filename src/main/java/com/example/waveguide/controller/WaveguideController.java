package com.example.waveguide.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.service.entity.Layer;
import com.example.demo.service.entity.Waveguide;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

@WebServlet("/waveguide/calculate")
public class WaveguideController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // Check authentication
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
            return;
        }
        
        int userId = (int) session.getAttribute("userId");
        
        try {
            // Read JSON from request body
            List<Layer> layers = objectMapper.readValue(request.getReader(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Layer.class));
                
            // Validate input
            if (layers == null || layers.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No layers provided");
                return;
            }
            
            // Create waveguide record
            Waveguide waveguide = new Waveguide();
            waveguide.setUserId(userId);
            waveguide.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            
            // Run calculation
            ProcessBuilder builder = new ProcessBuilder("python", "python/waveguide_runner.py");
            Process process = builder.start();

            // Write JSON to stdin
            try (OutputStream stdin = process.getOutputStream()) {
                objectMapper.writeValue(stdin, layers);
            }

            // Wait for process with timeout
            if (!process.waitFor(30, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                response.sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT, "Calculation timeout");
                return;
            }

            // Check process exit code
            if (process.exitValue() != 0) {
                try (InputStream stderr = process.getErrorStream()) {
                    String error = new String(stderr.readAllBytes(), StandardCharsets.UTF_8);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                        "Calculation failed: " + error);
                    return;
                }
            }

            // Read and return results
            try (InputStream stdout = process.getInputStream()) {
                String resultJson = new String(stdout.readAllBytes(), StandardCharsets.UTF_8);
                
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(resultJson);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Calculation interrupted");
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Error processing request: " + e.getMessage());
        }
    }
}