package com.javaee.project.dao;

import com.javaee.project.model.Tables;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablesDao {

    private Connection connection;

    public TablesDao() {
        connection = Database.getConnection();
    }

    public void checkTables(Tables tables) {
        try {
            PreparedStatement ps = connection.prepareStatement("select places_available from tables where places_available = ?");
            ps.setInt(1, tables.getPlaces_available());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateTables(tables);
            } else {
                addTables(tables);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addTables(Tables tables) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tables(places_available, currently_free) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, tables.getPlaces_available());
            preparedStatement.setBoolean(2, false);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTables(String tablesId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from tables where places_available=?");
            // Parameters start with 1
            preparedStatement.setString(1, tablesId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTables(Tables tables) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tables set places_available=?, currently_free=?"
                    + "where places_available=?");
            // Parameters start with 1
            preparedStatement.setInt(1, tables.getPlaces_available());
            preparedStatement.setBoolean(2, tables.isCurrently_free());
            preparedStatement.setInt(3, tables.getPlaces_available());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tables> getAllTabless() {
        List<Tables> tabless = new ArrayList<Tables>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tables");
            while (rs.next()) {
                Tables tables = new Tables();
                tables.setPlaces_available(rs.getInt("places_available"));
                tables.setCurrently_free(rs.getBoolean("currently_free"));
                tabless.add(tables);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabless;
    }

    public Tables getTablesById(String tablesId) {
        Tables tables = new Tables();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tables where places_available=?");
            preparedStatement.setString(1, tablesId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tables.setPlaces_available(rs.getInt("places_available"));
                tables.setCurrently_free(rs.getBoolean("currently_free"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }

}
