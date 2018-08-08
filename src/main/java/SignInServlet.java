import dbSevice.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null || pass == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

            if (dbService.isUserRegistered(login)) {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().print("Authorized: " + login);
                    resp.setStatus(HttpServletResponse.SC_OK);

            } else {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().println("Unauthorized");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

    }
}
