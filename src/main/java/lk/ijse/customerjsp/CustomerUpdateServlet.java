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
import java.sql.SQLException;

@WebServlet(name = "CustomerUpdateServlet", value="/customer-update")
public class CustomerUpdateServlet extends HttpServlet {

    String DB_URL = "jdbc:mysql://localhost:3306/customerDB";
    String DB_USER = "root";
    String DB_PASSWORD = "Ijse@123";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteServlet");

        String customerId = req.getParameter("customer_id");
        String customerName = req.getParameter("customer_name");
        String customerAddress = req.getParameter("customer_address");
        String customerEmail = req.getParameter("customer_email");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );

            String sql = "UPDATE customer SET name=?, address=?, email=? WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(4, customerId);
            pstm.setString(1, customerName);
            pstm.setString(2, customerAddress);
            pstm.setString(3, customerEmail);


            int effectedRow = pstm.executeUpdate();
            if (effectedRow > 0) {
                resp.sendRedirect(
                        "customer-update.jsp?message=customer has been updated successfully."
                );
            }else {
                resp.sendRedirect(
                        "customer-update.jsp?error=customer has not been updated successfully."
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(
                    "customer-update.jsp?error=customer has not been updated successfully."
            );
        }
    }
}
