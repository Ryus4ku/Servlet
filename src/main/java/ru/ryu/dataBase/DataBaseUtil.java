package ru.ryu.dataBase;

import ru.ryu.entity.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {
    public static void checkOrSetWord(Connection connection, List<Word> words) throws SQLException {
        for (Word word : words) {
            if (!isWordInDB(connection, word)) {
                setWordInDB(connection, word);
            }
        }
    }

    private static boolean isWordInDB(Connection connection, Word word) throws SQLException {
        String sql = "SELECT count(*) FROM words WHERE parent = ? AND file_name = ? and word = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word.getParent());
        preparedStatement.setString(2, word.getFileName());
        preparedStatement.setString(3, word.getWord());

        return getResult(preparedStatement) > 0;
    }

    private static int getResult(PreparedStatement preparedStatement) throws SQLException {
        ResultSet result = preparedStatement.executeQuery();
        int count = 0;
        while (result.next()) {
            count = result.getInt("count");
        }
        return count;
    }

    private static void setWordInDB(Connection connection, Word word) throws SQLException {
        String sql = "INSERT INTO words (parent, file_name, word, count) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word.getParent());
        preparedStatement.setString(2, word.getFileName());
        preparedStatement.setString(3, word.getWord());
        preparedStatement.setInt(4, word.getCount());

        preparedStatement.execute();
    }

    public static int getCountWords(Connection connection) throws SQLException {
        String sql = "SELECT count(*) FROM words";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return getResult(preparedStatement);
    }

    public static List<Word> getBySearchWord(Connection connection, String word) throws SQLException {
        String sql = "SELECT * FROM words WHERE word = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word);
        ResultSet result = preparedStatement.executeQuery();

        List<Word> words = new ArrayList<>();
        while (result.next()) {
            words.add(new Word(
                    result.getInt("id"),
                    result.getString("parent"),
                    result.getString("file_name"),
                    result.getString("word"),
                    result.getInt("count")
            ));
        }
        return words;
    }
}
