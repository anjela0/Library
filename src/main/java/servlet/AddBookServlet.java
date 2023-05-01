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
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/add")
public class AddBookServlet extends HttpServlet {

    private static String IMAGE_PATH = "C:\\Users\\sky\\IdeaProjects\\Java2022\\modular\\my_library\\projectImage\\";

    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Author> authors = authorManager.getAll();
//        request.setAttribute("authors",authors);
        request.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price") == null ? "0" : request.getParameter("price"));
        //int authorId = Integer.parseInt(request.getParameter("authorId"));
        Part profilePicPart = request.getPart("profilePic");
        String fileName = null;
        if(profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }

        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
               // .author(authorManager.getById(authorId))
                .profilePic(fileName)
                .build();

        bookManager.add(book);
        response.sendRedirect("/books");
    }

}
