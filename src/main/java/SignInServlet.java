import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
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

            if (accountService.isUserRegistered(login)) {
                if (pass.equals(accountService.getUserByLogin(login).getPass())) {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().println("Authorized: " + login);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    accountService.addSession(req.getSession().getId(), accountService.getUserByLogin(login));
                }

            } else {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().println("Unauthorized");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

    }
}