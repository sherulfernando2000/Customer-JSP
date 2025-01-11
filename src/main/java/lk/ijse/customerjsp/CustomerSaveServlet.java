package lk.ijse.customerjsp;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CustomerSaveServlet", value = "/customer-save")
public class CustomerSaveServlet extends HttpServlet {

    String DB_URL = "jdbc:mysql://localhost:3306/customerDB";
    String DB_USER = "root";
    String DB_PASSWORD = "Ijse@123";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("customer_name");
        String address = req.getParameter("customer_address");
        String email = req.getParameter("customer_email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );

            String sql = "INSERT INTO customer(name,address,email) VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,name);
            pstm.setString(2,address);
            pstm.setString(3,email);

            int effectedRowCount = pstm.executeUpdate();

            if (effectedRowCount > 0) {
                //success

                resp.sendRedirect("customer-save.jsp?message=Customer save successfully");


            }else {
                //fail
                resp.sendRedirect("customer-save.jsp?error=Customer save failed");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("customer-save.jsp?error=Customer save failed");
        }

    }


}













/*private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }*/