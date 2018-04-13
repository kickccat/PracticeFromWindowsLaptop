package de.is2.demo.web;

import de.is2.demo.model.BeerExpert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class BeerSelect extends HttpServlet {

    @RequestMapping(method = RequestMethod.POST, path = "/result.jsp")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("Beer Selection Advice<br/>");

        String str = request.getParameter("color");
//        out.println("<br/>Got beer color " + str);

        BeerExpert beerExpert = new BeerExpert();
        List result = beerExpert.getBrands(str);

        request.setAttribute("styles", result);
        System.out.println("result: " + result);
        RequestDispatcher view = request.getRequestDispatcher("result");
        view.forward(request, response);
    }
}
