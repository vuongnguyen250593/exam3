package controller;

import dao.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void action(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "showCreateForm":
                showCreateForm(request,response);
                break;
            case "postInsert":
                insertProduct(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "updateProduct":
                updateUser(request, response);
            default:
                getAllProduct (request, response);
                break;
        }
    }

    private void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        ArrayList<Product> products = productDAO.getAllProduct();
        request.setAttribute("listProduct", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product newProduct = new Product(id, name, price,quantity, color, description, category);
        productDAO.insertProduct(newProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductServlet");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

        ArrayList<Product> products = productDAO.getAllProduct();
        request.setAttribute("listProduct", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductServlet");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));

        Product product = new Product(id, name, price, quantity, color, description, category);
        productDAO.updateProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductServlet");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingUser = productDAO.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        request.setAttribute("product", existingUser);
        dispatcher.forward(request, response);

    }
}

