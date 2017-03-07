package Controller;/* Created by AMXPC on 2017/3/7. */

import Model.Dao.DAOFactory;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Model.Dao.DAOFactory.*;
import static Model.Vo.DbUser.*;

@WebServlet(name = "Maintain", urlPatterns = "/maintain")
public class Maintain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String admin = user.getName();
        if(admin != null) {
            String name = request.getParameter("name");
            String education = request.getParameter("education");
            String major = request.getParameter("major");
            Integer age = Integer.valueOf(request.getParameter("age"));
            String _level = request.getParameter("level");
            Integer level;
            if (_level.toLowerCase() == "administrator") {
                level = ADMINISTRATOR;
            } else if (_level.toLowerCase() == "student") {
                level = STUDENT;
            } else if (_level.toLowerCase() == "professor") {
                level = PROFESSOR;
            } else {
                level = STUDENT;
            }
            DbUser newUser = new DbUser();
            newUser.setName(name);
            newUser.setPassword(name);
            newUser.setEducation(education);
            newUser.setAge(age);
            newUser.setMajor(major);
            newUser.setLevel(level);
            int message = DAOFactory.getMaintainUserDAO().maintainUser(admin, newUser);
            switch (message) {
                case SUCCESS:
                    request.getRequestDispatcher("/table-admin.jsp").forward(request, response);
                    break;
                case FAILED:
                    response.getWriter().append("web wrong");
                    break;
                default:
                    break;
            }
        }
        else {
            System.out.println("There is no parameter.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
