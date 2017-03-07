package Controller;/* Created by AMXPC on 2017/3/6. */

import Model.Dao.DAOFactory;
import Model.Vo.DbCourse;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.*;

@WebServlet(name = "MyCourse", urlPatterns = {"/mycourse"})
public class MyCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        if(name != null) {
            ArrayList<DbCourse> courses = new ArrayList<>();
            int message = DAOFactory.getControlCourseDAO().getTeachedCourse(name, courses);
            switch (message) {
                case SUCCESS:
                    request.setAttribute("course", courses);
                    request.getRequestDispatcher("/table-professor.jsp").forward(request, response);
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
}
