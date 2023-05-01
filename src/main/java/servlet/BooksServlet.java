package servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books")
public class BooksServlet extends HttpServlet {

    AuthorManager authorManager = new AuthorManager();
    BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Author> authors = authorManager.getAll();
//        req.setAttribute("authors",authors);
        List<Book> books = bookManager.getAll();
        for (Book book : books) {
            List<Author> authors = authorManager.getAuthorsByBookId(book.getId());
            book.setAuthors(authors);
        }
        req.setAttribute("books",books);
        req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req,resp);
    }
}
