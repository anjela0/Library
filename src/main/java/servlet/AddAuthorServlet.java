package servlet;

import manager.AuthorManager;
import model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/authors/add")
public class AddAuthorServlet extends HttpServlet {

    private static String IMAGE_PATH = "C:\\Users\\sky\\IdeaProjects\\Java2022\\modular\\my_library\\projectImage\\";

    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = req.getParameter("age") == null ? 0 : Integer.parseInt(req.getParameter("age"));

        Part profilePicPart = req.getPart("profilePic");
        String fileName = null;
        if(profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }

        Author author = Author.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .profilePic(fileName)
                .build();

        authorManager.add(author);
        resp.sendRedirect("/authors");
    }
}
