package com.example.demo.service.repository.dao;

import com.example.demo.entity.Layer;
import com.example.demo.service.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LayerDAO {
    public Layer findById(int layerId) throws SQLException {
        String sql = "SELECT * FROM layers WHERE layer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, layerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLayer(rs);
                }
            }
        }
        return null;
    }
    
    public List<Layer> findByWaveguideId(int waveguideId) throws SQLException {
        List<Layer> layers = new ArrayList<>();
        String sql = "SELECT * FROM layers WHERE waveguides_id = ? ORDER BY layer_index";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, waveguideId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    layers.add(mapResultSetToLayer(rs));
                }
            }
        }
        return layers;
    }
    
    public void save(Layer layer) throws SQLException {
        String sql = "INSERT INTO layers (waveguides_id, layer_index, e, re_eps, im_eps, d) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, layer.getWaveguidesId());
            stmt.setInt(2, layer.getLayerIndex());
            stmt.setDouble(3, layer.getE());
            stmt.setDouble(4, layer.getReEps());
            stmt.setDouble(5, layer.getImEps());
            stmt.setDouble(6, layer.getD());
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    layer.setLayerId(generatedKeys.getInt(1));
                }
            }
        }
    }
    
    private Layer mapResultSetToLayer(ResultSet rs) throws SQLException {
        Layer layer = new Layer();
        layer.setLayerId(rs.getInt("layer_id"));
        layer.setWaveguidesId(rs.getInt("waveguides_id"));
        layer.setLayerIndex(rs.getInt("layer_index"));
        layer.setE(rs.getDouble("e"));
        layer.setReEps(rs.getDouble("re_eps"));
        layer.setImEps(rs.getDouble("im_eps"));
        layer.setD(rs.getDouble("d"));
        return layer;
    }
}