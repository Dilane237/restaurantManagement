package com.javaee.project.dao;

import com.javaee.project.model.TypeOfMenu;
import com.javaee.project.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeOfMenuDao {

    private Connection connection;

    public TypeOfMenuDao() {
        connection = Database.getConnection();
    }

    public void checkTypeOfMenu(TypeOfMenu typeofmenu) {
        try {
            PreparedStatement ps = connection.prepareStatement("select name from type_of_menu where name = ?");
            ps.setString(1, typeofmenu.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateTypeOfMenu(typeofmenu);
            } else {
                addTypeOfMenu(typeofmenu);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
    public void addTypeOfMenu(TypeOfMenu typeofmenu) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into type_of_menu(name, description, image) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, typeofmenu.getName());
            preparedStatement.setString(2, typeofmenu.getDescription());
            preparedStatement.setBlob(3, typeofmenu.getImage());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeOfMenu(String typeofmenuId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from type_of_menu where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, typeofmenuId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTypeOfMenu(TypeOfMenu typeofmenu) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update type_of_menu set name=?, description=?, image=?"
                    + "where name=?");
            // Parameters start with 1
            preparedStatement.setString(1, typeofmenu.getName());
            preparedStatement.setString(2, typeofmenu.getDescription());
            preparedStatement.setBlob(3, typeofmenu.getImage());
            preparedStatement.setString(4,typeofmenu.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TypeOfMenu> getAllTypeOfMenus() {
        List<TypeOfMenu> typeofmenus = new ArrayList<TypeOfMenu>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from type_of_menu");
            while (rs.next()) {
                TypeOfMenu typeofmenu = new TypeOfMenu();
                typeofmenu.setName(rs.getString("name"));
                typeofmenu.setDescription(rs.getString("description"));
                typeofmenu.setImage(rs.getBlob("image"));
                typeofmenus.add(typeofmenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeofmenus;
    }

    public TypeOfMenu getTypeOfMenuById(String typeofmenuId) {
        TypeOfMenu typeofmenu = new TypeOfMenu();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from type_of_menu where name=?");
            preparedStatement.setString(1, typeofmenuId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                typeofmenu.setName(rs.getString("name"));
                typeofmenu.setDescription(rs.getString("description"));
                typeofmenu.setImage(rs.getBlob("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeofmenu;
    }

}
