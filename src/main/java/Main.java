import accounts.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        SignUpServlet signUpServlet = new SignUpServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(signUpServlet), "/signup");
        contextHandler.addServlet(new ServletHolder(signInServlet), "/signin");


        Server server = new Server(8080);
        server.setHandler(contextHandler);

        try {
            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
