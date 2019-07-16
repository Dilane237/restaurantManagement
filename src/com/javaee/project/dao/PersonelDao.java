package com.javaee.project.dao;

import com.javaee.project.model.Personel;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonelDao {

    private Connection connection;

    public PersonelDao() {
        connection = Database.getConnection();
    }

    public void checkPersonel(Personel personel) {
        try {
            PreparedStatement ps = connection.prepareStatement("select name from personel where name = ?");
            ps.setString(1, personel.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updatePersonel(personel);
            } else {
                addPersonel(personel);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addPersonel(Personel personel) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into personel(name, surname, phone_number) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, personel.getName());
            preparedStatement.setString(2, personel.getSurname());
            preparedStatement.setString(3, personel.getPhone_number());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePersonel(String personelId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from personel where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, personelId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePersonel(Personel personel) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update personel set name=?, surname=?, phone_number=?"
                    + "where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, personel.getName());
            preparedStatement.setString(2, personel.getSurname());
            preparedStatement.setString(3,personel.getPhone_number());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personel> getAllPersonels() {
        List<Personel> personels = new ArrayList<Personel>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from personel");
            while (rs.next()) {
                Personel personel = new Personel();
                personel.setName(rs.getString("name"));
                personel.setSurname(rs.getString("surname"));
                personel.setPhone_number(rs.getString("phone_number"));
                personels.add(personel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personels;
    }

    public Personel getPersonelById(String personelId) {
        Personel personel = new Personel();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from personel where name=?");
            preparedStatement.setString(1, personelId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                personel.setName(rs.getString("name"));
                personel.setSurname(rs.getString("surname"));
                personel.setPhone_number(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personel;
    }


}
