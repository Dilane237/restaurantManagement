package com.javaee.project.dao;

import com.javaee.project.model.Services;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesDao {

    private Connection connection;

    public ServicesDao() {
        connection = Database.getConnection();
    }

    public void checkServices(Services services) {
        try {
            PreparedStatement ps = connection.prepareStatement("select name from service where name = ?");
            ps.setString(1, services.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateServices(services);
            } else {
                addServices(services);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addServices(Services services) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into service(name, description) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, services.getName());
            preparedStatement.setString(2, services.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteServices(String servicesId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from service where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, servicesId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateServices(Services services) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update service set name=?, description=?"
                    + "where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, services.getName());
            preparedStatement.setString(2, services.getDescription());
            preparedStatement.setString(3, services.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Services> getAllServicess() {
        List<Services> servicess = new ArrayList<Services>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from service");
            while (rs.next()) {
                Services services = new Services();
                services.setName(rs.getString("name"));
                services.setDescription(rs.getString("description"));
                servicess.add(services);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicess;
    }

    public Services getServicesById(String servicesId) {
        Services services = new Services();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from service where name=?");
            preparedStatement.setString(1, servicesId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                services.setName(rs.getString("name"));
                services.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

}
