package com.javaee.project.dao;

import com.javaee.project.model.Menu;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {

    private Connection connection;

    public MenuDao() {
        connection = Database.getConnection();
    }

    public void checkMenu(Menu menu) {
        try {
            PreparedStatement ps = connection.prepareStatement("select name from menu where name = ?");
            ps.setString(1, menu.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateMenu(menu);
            } else {
                addMenu(menu);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addMenu(Menu menu) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into menu(name, description, image, price) values (?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setBlob(3, menu.getImage());
            preparedStatement.setInt(4,menu.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(String menuId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from menu where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, menuId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenu(Menu menu) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update menu set name=?, description=?, image=?, price=?"
                    + "where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setBlob(3, menu.getImage());
            preparedStatement.setInt(4,menu.getPrice());
            preparedStatement.setString(5,menu.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<Menu>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from menu");
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setName(rs.getString("name"));
                menu.setDescription(rs.getString("description"));
                menu.setImage(rs.getBlob("image"));
                menu.setPrice(rs.getInt("price"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menus;
    }

    public Menu getMenuById(String menuId) {
        Menu menu = new Menu();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from menu where name=?");
            preparedStatement.setString(1, menuId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                menu.setName(rs.getString("name"));
                menu.setDescription(rs.getString("description"));
                menu.setImage(rs.getBlob("image"));
                menu.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }


}
