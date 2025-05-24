package com.example.demo.service.repository.dao;

import com.example.demo.entity.Waveguide;
import com.example.demo.service.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaveguideDAO {
    public Waveguide findById(int waveguideId) throws SQLException {
        String sql = "SELECT * FROM waveguides WHERE waveguides_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, waveguideId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWaveguide(rs);
                }
            }
        }
        return null;
    }
    
    public List<Waveguide> findByUserId(int userId) throws SQLException {
        List<Waveguide> waveguides = new ArrayList<>();
        String sql = "SELECT * FROM waveguides WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    waveguides.add(mapResultSetToWaveguide(rs));
                }
            }
        }
        return waveguides;
    }
    
    public void save(Waveguide waveguide) throws SQLException {
        String sql = "INSERT INTO waveguides (user_id, n_eff_min, n_eff_max, created_at) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, waveguide.getUserId());
            stmt.setDouble(2, waveguide.getnEffMin());
            stmt.setDouble(3, waveguide.getnEffMax());
            stmt.setTimestamp(4, waveguide.getCreatedAt());
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    waveguide.setWaveguidesId(generatedKeys.getInt(1));
                }
            }
        }
    }
    
    private Waveguide mapResultSetToWaveguide(ResultSet rs) throws SQLException {
        Waveguide waveguide = new Waveguide();
        waveguide.setWaveguidesId(rs.getInt("waveguides_id"));
        waveguide.setUserId(rs.getInt("user_id"));
        waveguide.setnEffMin(rs.getDouble("n_eff_min"));
        waveguide.setnEffMax(rs.getDouble("n_eff_max"));
        waveguide.setCreatedAt(rs.getTimestamp("created_at"));
        return waveguide;
    }
}