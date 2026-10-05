package com.ejemplo.servlet;

import com.ejemplo.model.Tarea;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TareasServlet", urlPatterns = {"/tareas"})
public class TareasServlet extends HttpServlet {

    // Estado compartido de toda la aplicación (no es dato de una petición)
    private final List<Tarea> tareas = new ArrayList<>();
    private int contadorId = 1;

    @Override
    public void init() throws ServletException {
        tareas.add(new Tarea(contadorId++, "Leer documentación de Servlets"));
        tareas.add(new Tarea(contadorId++, "Implementar ciclo GET/POST"));
    }

    /** GET /tareas — mostrar lista */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tareas", tareas);
        req.getRequestDispatcher("/WEB-INF/views/tareas.jsp")
                .forward(req, resp);
    }

    /** POST /tareas — agregar o eliminar tarea */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");

        if ("agregar".equals(accion)) {
            String titulo = req.getParameter("titulo");
            if (titulo == null || titulo.isBlank()) {
                req.setAttribute("error", "El título no puede estar vacío");
                req.setAttribute("tareas", tareas);
                req.getRequestDispatcher("/WEB-INF/views/tareas.jsp")
                        .forward(req, resp);
                return;
            }
            tareas.add(new Tarea(contadorId++, titulo.trim()));

        } else if ("eliminar".equals(accion)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                tareas.removeIf(t -> t.getId() == id);
            } catch (NumberFormatException e) {
                // id inválido: se ignora y se redirige igualmente
            }
        }

        // Patrón PRG
        resp.sendRedirect(req.getContextPath() + "/tareas");
    }
}