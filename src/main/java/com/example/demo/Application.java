package com.example.demo;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContextEvent;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Application startup logic here
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup logic here
    }
}