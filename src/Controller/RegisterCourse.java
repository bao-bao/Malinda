package Controller;

/* Created by AMXPC on 2017/3/3. */

import Model.Dao.DAOFactory;
import Model.Vo.DbCourse;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.FAILED;
import static Model.Dao.DAOFactory.SUCCESS;

@WebServlet(name = "RegisterCourse", urlPatterns = {"/register"})
public class RegisterCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        String course = request.getParameter("courseName");
        if(course != null && name != null) {
            int message = DAOFactory.getControlCourseDAO().registerCourse(name, course);
            switch(message) {
                case SUCCESS:
                    response.getWriter().append("register success for [" + course + "]");
                    request.getRequestDispatcher( "/table-student.jsp").forward(request,response);
                    break;
                case FAILED:
                    request.getRequestDispatcher( "/table-student.jsp").forward(request,response);
                    break;
                default:
                    break;
            }
        }
        else {
            System.out.println("There is no parameter.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        if(name != null) {
            ArrayList<DbCourse> dbCourses = new ArrayList<>();
            int message = DAOFactory.getControlCourseDAO().getAllCanRegisterCourse(name, dbCourses);
            switch(message) {
                case SUCCESS:
                    request.setAttribute("course", dbCourses);
                    ArrayList<Integer> courseStudentNumber = new ArrayList<>();
                    for (DbCourse dbCourse : dbCourses) {
                        int message1 = DAOFactory.getControlCourseDAO().getCourseStudentNumber(dbCourse.getName(), courseStudentNumber);
                        if (message1 == FAILED) {
                            response.getWriter().append("web wrong");
                            break;
                        }
                    }
                    request.setAttribute("studentNumber", courseStudentNumber);
                    request.getRequestDispatcher( "/table-student.jsp").forward(request,response);
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
