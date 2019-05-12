package ru.ryu.servlet;

import ru.ryu.connection.ConnectionUtil;
import ru.ryu.dataBase.DataBaseUtil;
import ru.ryu.entity.Word;
import ru.ryu.service.PackageScanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/hello")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PackageScanner packageScanner = new PackageScanner();
        List<Word> wordsFromCatalog = packageScanner.scan();

        try {
            Connection connection = ConnectionUtil.getConnection();
            DataBaseUtil.checkOrSetWord(connection, wordsFromCatalog);
            ConnectionUtil.closeQuietly(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/hello.jsp");
    }
}
