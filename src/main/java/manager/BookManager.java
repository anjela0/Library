package manager;

import db.DBConnectionProvider;
import lombok.SneakyThrows;
import model.Author;
import model.Book;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Book book) {
        String sql = "insert into book(title,description,price,profile_pic) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getDescription());
            ps.setDouble(3,book.getPrice());
            ps.setString(4,book.getProfilePic());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {
        String sql = "select * from book";
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }


    public Book getBookByTitle(String title) {
        String sql = "select * from book where title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getDouble("price"));
        book.setProfilePic(resultSet.getString("profile_pic"));

        String authorBookSql = "select author_id from book_author where book_id = ?";
        PreparedStatement ps = connection.prepareStatement(authorBookSql);
        ps.setInt(1,book.getId());
        ResultSet authorsResultSet = ps.executeQuery();
        List<Author> authors = new ArrayList<>();
        while (authorsResultSet.next()) {
            authors.add(getAuthorById(authorsResultSet.getInt(1)));
        }
        book.setAuthors(authors);

        return book;

    }
    public Author getAuthorById(int id) {
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

    private Author getAuthorFromResultSet(ResultSet resultSet) {
        Author author = null;
        try {
            author = Author.builder()
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
                books.add(getById(bookResultSet.getInt(1)));
            }
            author.setBooks(books);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return author;
    }

    public void removeBookById(int id) {
        String sql = "delete from book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getById(int id) {
        String sql = "select * from book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public User getUserByEmailAndPassword(String email, String password) {
//        String sql = "select * from book where email = ? and password = ?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                return getBookFromResultSet(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void edit(Book book) {
        String sql = "update book set title=?,description=?,price=?,profile_pic=? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getDescription());
            ps.setDouble(3,book.getPrice());
          //  ps.setInt(4,book.getAuthor().getId());
            ps.setString(4,book.getProfilePic());
            ps.setInt(5,book.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooksByAuthorId(int authorId) {
        String bookAuthorSql = "select book_id from book_author where author_id=?";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(bookAuthorSql);
            ps.setInt(1,authorId);
            ResultSet booksResultSet = ps.executeQuery();
            while (booksResultSet.next()) {
                books.add(getById(booksResultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
