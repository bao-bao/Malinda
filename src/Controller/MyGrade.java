package Controller;

/* Created by AMXPC on 2017/3/3. */

import Model.Dao.DAOFactory;
import Model.Vo.DbCourse;
import Model.Vo.DbTake;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.FAILED;
import static Model.Dao.DAOFactory.SUCCESS;

@WebServlet(name = "MyGrade", urlPatterns = {"/mygrade"})
public class MyGrade extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        if(name != null) {
            ArrayList<DbTake> dbTakes = new ArrayList<>();
            int message = DAOFactory.getControlCourseDAO().getAllRegisteredCourse(name, dbTakes);
            switch(message) {
                case SUCCESS:
                    request.setAttribute("take", dbTakes);
                    request.getRequestDispatcher( "/table-student-grade.jsp").forward(request,response);
                    break;
                case FAILED:
                    request.setAttribute("take", dbTakes);
                    request.getRequestDispatcher( "/table-student-grade.jsp").forward(request,response);
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
