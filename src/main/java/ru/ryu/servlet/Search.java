package ru.ryu.servlet;

import ru.ryu.connection.ConnectionUtil;
import ru.ryu.dataBase.DataBaseUtil;
import ru.ryu.entity.Word;
import ru.ryu.service.WorkWithWord;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/Search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<String> listWords = WorkWithWord.splitText(req.getParameterMap().get("searchingQuery")[0].toLowerCase());
        HashMap<String, ArrayList<Word>> map = new HashMap<>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            for (String elementList : listWords) {
                map.put(elementList, new ArrayList<>(DataBaseUtil.getBySearchWord(connection, elementList)));
            }
            ConnectionUtil.closeQuietly(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("keys", listWords);
        req.setAttribute("searchResult", map);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/hello.jsp");
        requestDispatcher.forward(req, resp);
    }
}
