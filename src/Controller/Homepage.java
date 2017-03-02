package Controller;/* Created by AMXPC on 2017/3/1. */

import Model.Dao.DAOFactory;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Homepage", urlPatterns = {"/homepage"})
public class Homepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Integer> studentNumber = new ArrayList<>();
        ArrayList<Integer> professorNumber = new ArrayList<>();
        ArrayList<Integer> courseNumber = new ArrayList<>();
        DAOFactory.getStatisticsDAO().getNumber(DbUser.STUDENT, studentNumber);
        DAOFactory.getStatisticsDAO().getNumber(DbUser.PROFESSOR, professorNumber);
        DAOFactory.getStatisticsDAO().getNumber(DbUser.COURSE, courseNumber);
        request.setAttribute("studentNumber", studentNumber.get(0));
        request.setAttribute("professorNumber", professorNumber.get(0));
        request.setAttribute("courseNumber", courseNumber.get(0));
        request.getRequestDispatcher( "/homepage.jsp").forward(request,response);
    }
}
