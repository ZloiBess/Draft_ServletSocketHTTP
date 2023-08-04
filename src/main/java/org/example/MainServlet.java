package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private final List<Person> personList = new ArrayList<>(); //like a db

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        personList.forEach(i -> {
            pw.println("<h1>" + i.getFirstName() + " " + i.getLastName() + "</h1>");
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String json = br.lines().collect(Collectors.joining("\n"));
        Person person = MyParserJSON.jsonForObject(json, Person.class);
        personList.add(person);
    }
}