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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/authors")
public class AuthorsServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        double minPrice = req.getParameter("minPrice") == null ? 0 : Double.parseDouble(req.getParameter("minPrice"));
        double maxPrice = req.getParameter("maxPrice") == null ? 0 : Double.parseDouble(req.getParameter("maxPrice"));
        List<Author> authorList;
        if (keyword != null && !keyword.equals("")) {
            req.setAttribute("keyword", keyword);
            authorList = authorManager.search(keyword);
        } else if (minPrice > 0 || maxPrice > 0 && minPrice < maxPrice) {
            req.setAttribute("keyword", "");
            authorList = authorManager.filter(minPrice, maxPrice);
        } else {
            req.setAttribute("keyword", "");
            authorList = authorManager.getAll();
        }
        for (Author author : authorList) {
            List<Book> books = bookManager.getBooksByAuthorId(author.getId());
            author.setBooks(books);
        }
//        List<Book> books = bookManager.getAll();
//        req.setAttribute("books",books);
        req.setAttribute("authors", authorList);
        req.getRequestDispatcher("/WEB-INF/authors.jsp").forward(req, resp);
    }
}
