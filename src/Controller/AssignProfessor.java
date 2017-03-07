package Controller;/* Created by AMXPC on 2017/3/5. */

import Model.Dao.DAOFactory;
import Model.Vo.DbCourse;
import Model.Vo.DbTeach;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static Model.Dao.DAOFactory.FAILED;
import static Model.Dao.DAOFactory.SUCCESS;

@WebServlet(name = "AssignProfessor", urlPatterns = {"/assignprofessor"})
public class AssignProfessor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        String course = request.getParameter("courseName");
        String _state = request.getParameter("state");
        if (name != null && course != null) {
            String professor = request.getParameter("professorName");
            int message = DAOFactory.getControlCourseDAO().assignProfessor(professor, course);
            switch (message) {
                case SUCCESS:
                    request.setAttribute("tabletype", "course");
                    break;
                case FAILED:
                    response.getWriter().append("web wrong");
                default:
                    break;
            }
        }
        if(_state != null) {
        Integer status = Integer.valueOf(_state);
            int state = (status==1 ? 2:1);
            int message = DAOFactory.getControlCourseDAO().changeState(user.getName(), course, state);
            switch (message) {
                case SUCCESS:
                    break;
                case FAILED:
                response.getWriter().append("web wrong");
                default:
                    break;
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbUser user = (DbUser) request.getSession().getAttribute("loginuser");
        assert user != null;
        String name = user.getName();
        String table = request.getParameter("tabletype");
        if (name != null) {
            if (table == null || Objects.equals(table, "course")) {
                ArrayList<DbCourse> dbCourses = new ArrayList<>();
                int message = DAOFactory.getControlCourseDAO().getAllCourse(dbCourses);
                switch (message) {
                    case SUCCESS:
                        request.setAttribute("course", dbCourses);
                        ArrayList<DbTeach> teaches = new ArrayList<>();
                        for (DbCourse dbCourse : dbCourses) {
                            int message1 = DAOFactory.getControlCourseDAO().getTeachedProfessor(dbCourse.getName(), teaches);
                            if (message1 == FAILED) {
                                response.getWriter().append("web wrong");
                                break;
                            }
                        }
                        request.setAttribute("professor", teaches);
                        request.setAttribute("tabletype", "course");
                        request.getRequestDispatcher("/table-admin.jsp").forward(request, response);
                        break;
                    case FAILED:
                        response.getWriter().append("web wrong");
                        break;
                    default:
                        break;
                }
            } else if (Objects.equals(table, "student")) {
                ArrayList<DbUser> dbUsers = new ArrayList<>();
                int message = DAOFactory.getMaintainUserDAO().getAllStudent(dbUsers);
                switch (message) {
                    case SUCCESS:
                        request.setAttribute("student", dbUsers);
                        request.setAttribute("tabletype", "student");
                        request.getRequestDispatcher("/table-admin.jsp").forward(request, response);
                        break;
                    case FAILED:
                        response.getWriter().append("web wrong");
                        break;
                    default:
                        break;
                }
            } else if (Objects.equals(table, "professor")) {
                ArrayList<DbUser> dbUsers = new ArrayList<>();
                int message = DAOFactory.getMaintainUserDAO().getAllProfessor(dbUsers);
                switch (message) {
                    case SUCCESS:
                        request.setAttribute("professor", dbUsers);
                        request.setAttribute("tabletype", "professor");
                        request.getRequestDispatcher("/table-admin.jsp").forward(request, response);
                        break;
                    case FAILED:
                        response.getWriter().append("web wrong");
                        break;
                    default:
                        break;
                }
            } else {
                response.getWriter().append("web wrong");
            }
        } else {
            System.out.println("There is no parameter.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }
    }
}
