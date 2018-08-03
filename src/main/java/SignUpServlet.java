import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null || pass == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

            if (accountService.isUserRegistered(login)) {
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            accountService.addNewUser(new UserProfile(login, pass));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(accountService.getUserByLogin(login).getLogin());
    }
}
