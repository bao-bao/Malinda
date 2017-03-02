package Controller;/* Created by AMXPC on 2017/3/2. */

import Model.Dao.DAOFactory;
import Model.Vo.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.*;

@WebServlet(name = "SignIn", urlPatterns = {"/signin"})
public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(name != null && password != null) {
            ArrayList<DbUser> loginUserList = new ArrayList<DbUser>();
            int message = DAOFactory.getMaintainUserDAO().signIn(name, password, loginUserList);
            switch(message) {
                case SUCCESS:
                    HttpSession session = request.getSession(true);
                    DbUser loginUser = loginUserList.get(0);
                    session.setAttribute("loginuser", loginUser);
                    response.getWriter().append("login success: " + ((DbUser) session.getAttribute("loginuser")).getName());
                    request.getRequestDispatcher( "/homepage").forward(request,response);
                    break;
                case FAILED:
                    response.getWriter().append("user information wrong");
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
