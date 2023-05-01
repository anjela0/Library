package manager;

import db.DBConnectionProvider;
import lombok.SneakyThrows;
import model.Author;
import model.Book;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AuthorManager {

    private BookManager bookManager = new BookManager();
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Author author) {
        String sql = "insert into author(name,surname,email,age,profile_pic) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setString(5, author.getProfilePic());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAuthorById(int id) {
        String sql = "delete from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAll() {
        String sql = "select * from author";
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getById(int id) {
        String sql = "select * from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void join(int authorId,int bookId) {
        String sql = "insert into book_author(book_id,author_id) VALUES(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,bookId);
            ps.setInt(2,authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancel(int authorId,int bookId) {
        String sql = "delete from book_author WHERE author_id=? and book_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,authorId);
            ps.setInt(2,bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> getAuthorsByBookId(int bookId) {
        String authorBookSql = "select author_id from book_author where book_id=?";
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(authorBookSql);
            ps.setInt(1,bookId);
            ResultSet authorsResultSet = ps.executeQuery();
            while (authorsResultSet.next()) {
                authors.add(getById(authorsResultSet.getInt(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    public List<Author> search(String keyword) {
        String sql = "select * from author where name like '%" + keyword + "%' or place like '%" + keyword + "%'";
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public List<Author> filter(double minPrice,double maxPrice) {
        String sql = "select * from author where price > " + minPrice + " and price < " + maxPrice;
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @SneakyThrows
    private Author getAuthorFromResultSet(ResultSet resultSet) {
        Author author = Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getInt("age"))
                .profilePic(resultSet.getString("profile_pic"))
                .build();

        String authorBookSql = "select book_id from book_author where author_id=?";
        PreparedStatement ps = connection.prepareStatement(authorBookSql);
        ps.setInt(1,author.getId());
        ResultSet bookResultSet = ps.executeQuery();
        List<Book> books = new ArrayList<>();
        while (bookResultSet.next()) {
            books.add(bookManager.getById(bookResultSet.getInt(1)));
        }
        author.setBooks(books);

        return author;
    }
}
