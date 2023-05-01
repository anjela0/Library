package servlet;

import manager.AuthorManager;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/authors/cancel")
public class CancelAuthorServlet extends HttpServlet {

    private AuthorManager authorManager= new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Book book = (Book) req.getSession().getAttribute("book");
        authorManager.cancel(authorId,book.getId());
        resp.sendRedirect("/authors");
    }
}
