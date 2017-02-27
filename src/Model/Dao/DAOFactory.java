package Model.Dao;

/* Created by AMXPC on 2017/2/27. */

public class DAOFactory {
    static public final int EXCEPTION = -1;
    static public final int SUCCESS = 1;
    static public final int FAILED = -2;

    static public MaintainUserDAO getMaintainStudentDAO() {
        return new MaintainUserDAO();
    }

    static public RegisterDAO getRegisterDAO() {
        return new RegisterDAO();
    }

    static public GradeDAO getGradeDAO() {
        return new GradeDAO();
    }

    static public TeachDAO getTeachDAO() {
        return new TeachDAO();
    }

    static public ControlCourseDAO getControlCourseDAO() {
        return new ControlCourseDAO();
    }
}
