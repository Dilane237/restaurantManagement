package com.javaee.project.dao;

import com.javaee.project.model.Setting;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDao {

    private Connection connection;

    public SettingDao() {
        connection = Database.getConnection();
    }

    public void checkSetting(Setting setting) {
        try {
            PreparedStatement ps = connection.prepareStatement("select uname from settings where uname = ?");
            ps.setString(1, setting.getRestaurant_name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateSetting(setting);
            } else {
                addSetting(setting);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addSetting(Setting setting) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into setting(restaurant_name, number_of_table, number_of_person_table, address, email, phone_number, reservation_fee, duration_time_for_reservation, opening_time, closing_time) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, setting.getRestaurant_name());
            preparedStatement.setInt(2, setting.getNumber_of_table());
            preparedStatement.setInt(3, setting.getNumber_person_table());
            preparedStatement.setString(4, setting.getAddress());
            preparedStatement.setString(5, setting.getEmail());
            preparedStatement.setString(6, setting.getPhone_number());
            preparedStatement.setInt(7, setting.getReservation_fee());
            preparedStatement.setInt(8, setting.getDuration_time_reservation());
            preparedStatement.setTime(9, setting.getOpening_time());
            preparedStatement.setTime(10, setting.getClosing_time());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSetting(String settingId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from setting where restaurant_name=?");
            // Parameters start with 1
            preparedStatement.setString(1, settingId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSetting(Setting setting) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update setting set restaurant_name=?, number_of_table=?, number_of_person_table, address=?, email=?, phone_number=?, reservation_fee=?, duration_time_for_reservation=?, opening_time=?, closing_time=?"
                    + "where restaurant_name=?");
            // Parameters start with 1
            preparedStatement.setString(1, setting.getRestaurant_name());
            preparedStatement.setInt(2, setting.getNumber_of_table());
            preparedStatement.setInt(3, setting.getNumber_person_table());
            preparedStatement.setString(4, setting.getAddress());
            preparedStatement.setString(5, setting.getEmail());
            preparedStatement.setString(6, setting.getPhone_number());
            preparedStatement.setInt(7, setting.getReservation_fee());
            preparedStatement.setInt(8, setting.getDuration_time_reservation());
            preparedStatement.setTime(9, setting.getOpening_time());
            preparedStatement.setTime(10, setting.getClosing_time());
            preparedStatement.setString(11, setting.getRestaurant_name());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Setting> getAllSettings() {
        List<Setting> settings = new ArrayList<Setting>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from setting");
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setRestaurant_name(rs.getString("restaurant_name"));
                setting.setNumber_of_table(rs.getInt("number_of_table"));
                setting.setNumber_person_table(rs.getInt("number_of_person_table"));
                setting.setAddress(rs.getString("address"));
                setting.setEmail(rs.getString("email"));
                setting.setPhone_number(rs.getString("phone_number"));
                setting.setReservation_fee(rs.getInt("reservation_fee"));
                setting.setDuration_time_reservation(rs.getInt("duration_time_for_reservation"));
                setting.setOpening_time(rs.getTime("opening_time"));
                setting.setClosing_time(rs.getTime("closing_time"));
                settings.add(setting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return settings;
    }

    public Setting getSettingById(String settingId) {
        Setting setting = new Setting();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from setting where restaurant_name=?");
            preparedStatement.setString(1, settingId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                setting.setRestaurant_name(rs.getString("restaurant_name"));
                setting.setNumber_of_table(rs.getInt("number_of_table"));
                setting.setNumber_person_table(rs.getInt("number_of_person_table"));
                setting.setAddress(rs.getString("address"));
                setting.setEmail(rs.getString("email"));
                setting.setPhone_number(rs.getString("phone_number"));
                setting.setReservation_fee(rs.getInt("reservation_fee"));
                setting.setDuration_time_reservation(rs.getInt("duration_time_for_reservation"));
                setting.setOpening_time(rs.getTime("opening_time"));
                setting.setClosing_time(rs.getTime("closing_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return setting;
    }

}
