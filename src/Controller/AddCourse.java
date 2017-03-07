package Controller;/* Created by AMXPC on 2017/3/7. */

import Model.Dao.DAOFactory;
import Model.Vo.DbCourse;
import Model.Vo.DbUser;
import sun.security.pkcs11.wrapper.CK_LOCKMUTEX;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Model.Dao.DAOFactory.FAILED;
import static Model.Dao.DAOFactory.SUCCESS;
import static Model.Vo.DbCourse.CLOSE;
import static Model.Vo.DbUser.ADMINISTRATOR;
import static Model.Vo.DbUser.PROFESSOR;
import static Model.Vo.DbUser.STUDENT;

@WebServlet(name = "AddCourse", urlPatterns = {"/addcourse"})
public class AddCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String admin = user.getName();
        if(admin != null) {
            String name = request.getParameter("name");
            Integer year = Integer.valueOf(request.getParameter("year"));
            String time = request.getParameter("time");
            Integer credit = Integer.valueOf(request.getParameter("credit"));
            Integer number = Integer.valueOf(request.getParameter("number"));
            Integer state = CLOSE;

            DbCourse newCourse = new DbCourse();
            newCourse.setName(name);
            newCourse.setYear(year);
            newCourse.setTime(time);
            newCourse.setCredit(credit);
            newCourse.setNumber(number);
            newCourse.setState(state);
            int message = DAOFactory.getMaintainUserDAO().maintainCourse(admin, newCourse);
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
