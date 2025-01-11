package lk.ijse.customerjsp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerListServlet", value = "/customer-list")
public class CustomerListServlet extends HttpServlet {

    String DB_URL = "jdbc:mysql://localhost:3306/customerDB";
    String DB_USER = "root";
    String DB_PASSWORD = "Ijse@123";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoGet");

        List<CustomerDTO> customerList = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );

            String sql = "select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(resultSet.getInt(1));
                customerDTO.setName(resultSet.getString(2));
                customerDTO.setAddress(resultSet.getString(3));
                customerDTO.setEmail(resultSet.getString(4));

                customerList.add(customerDTO);
            }

            req.setAttribute("customers",customerList);
            RequestDispatcher rd = req.getRequestDispatcher("customer-list.jsp");
            rd.forward(req,resp);


        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(
                    "customer-list.jsp?error= customers not load"
            );
        }
    }
}
