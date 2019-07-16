package com.javaee.project.controller;

import com.javaee.project.dao.TypeOfMenuDao;
import com.javaee.project.model.TypeOfMenu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeMenuController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/command/typemenu.jsp";
    private static String LIST_TYPE_MENU = "/command/listtypemenu.jsp";
    private TypeOfMenuDao dao;

    public TypeMenuController() {
        super();
        dao = new TypeOfMenuDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            String typeofmenuId = request.getParameter("typeofmenuId");
            dao.deleteTypeOfMenu(typeofmenuId);
            forward = LIST_TYPE_MENU;
            request.setAttribute("typeofmenus", dao.getAllTypeOfMenus());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String typeofmenuId = request.getParameter("typeofmenuId");
            TypeOfMenu typeofmenu = dao.getTypeOfMenuById(typeofmenuId);
            request.setAttribute("typeofmenu", typeofmenu);
        } else if (action.equalsIgnoreCase("listTypeOfMenu")){
            forward = LIST_TYPE_MENU;
            request.setAttribute("typeofmenus", dao.getAllTypeOfMenus());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeOfMenu typeofmenu = new TypeOfMenu();
        typeofmenu.setName(request.getParameter("name"));
        typeofmenu.setDescription(request.getParameter("description"));
//        typeofmenu.setImage(request.getParameter("image"));
        dao.checkTypeOfMenu(typeofmenu);
        RequestDispatcher view = request.getRequestDispatcher(LIST_TYPE_MENU);
        request.setAttribute("typeofmenus", dao.getAllTypeOfMenus());
        view.forward(request, response);
    }

}
