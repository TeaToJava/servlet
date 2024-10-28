package ru.clevertec.servlet;

import com.google.gson.Gson;

import ru.clevertec.domain.PaintingDto;
import ru.clevertec.service.PaintingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/paintings/*")
public class PaintingServlet extends HttpServlet {

    private final PaintingService paintingService = PaintingService.getInstance();
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getPathInfo().substring(1);
            UUID uuid = UUID.fromString(id);
            if (id != null) {
                PaintingDto paintingDto = paintingService.getPaintingById(uuid);
                response.setContentType(CONTENT_TYPE);
                response.setCharacterEncoding(ENCODING);

                String jsonString = new Gson().toJson(paintingDto);
                try (PrintWriter out = response.getWriter()) {
                    out.write(jsonString);
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getPathInfo().substring(1);
            if (id != null) {
                UUID uuid = UUID.fromString(id);
                paintingService.delete(uuid);
                response.getWriter().append("Project is deleted");
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
