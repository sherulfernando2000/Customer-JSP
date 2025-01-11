package lk.ijse.customerjsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "CustomerDeleteServlet", value = "/customer-delete")
public class CustomerDeleteServlet extends HttpServlet {

    String DB_URL = "jdbc:mysql://localhost:3306/customerDB";
    String DB_USER = "root";
    String DB_PASSWORD = "Ijse@123";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inDoPost of delete servlet");
        String customerId = req.getParameter("customer_id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );

            String sql = "DELETE FROM customer WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, customerId);
            int effectedRow = pstm.executeUpdate();
            if (effectedRow > 0) {
                resp.sendRedirect(
                        "customer-delete.jsp?message=customer has been deleted successfully."
                );
            }else {
                resp.sendRedirect(
                        "customer-delete.jsp?error=customer has not been deleted successfully."
                );
            }


        } catch (Exception e) {
           e.printStackTrace();

        }

    }
}
