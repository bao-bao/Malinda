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

@WebServlet(name = "Grade", urlPatterns = {"/grade"})
public class Grade extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        String student = request.getParameter("student");
        String course = request.getParameter("course");
        Double grade = Double.valueOf(request.getParameter("grade"));
        if(name != null && course != null) {
            int message = DAOFactory.getGradeDAO().submitGrade(name, student, course, grade);
            switch (message) {
                case SUCCESS:
                    request.getRequestDispatcher("/submitgrade").forward(request, response);
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
        doPost(request, response);
    }
}
