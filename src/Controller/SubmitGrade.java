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

@WebServlet(name = "SubmitGrade", urlPatterns = {"/submitgrade"})
public class SubmitGrade extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        String course = request.getParameter("course");
        if(name != null && course != null) {
            ArrayList<DbUser> students = new ArrayList<>();
            int message = DAOFactory.getControlCourseDAO().getAllStudentInCourse(course, students);
            switch (message) {
                case SUCCESS:
                    request.setAttribute("student", students);
                    request.setAttribute("course", "course");
                    request.getRequestDispatcher("/table-grade.jsp").forward(request, response);
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
