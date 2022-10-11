package spi;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Wiekdokladny extends HttpServlet  {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate today = LocalDate.now();

        String rok = request.getParameter("rokur");
        String mie = request.getParameter("miesiacur");
        String dzie = request.getParameter("dzienur");
        if (rok == null||mie == null ) {

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<title>Wiek osoby</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Wiek osoby</h1>");
            out.println("<form method=\"POST\">");
            out.println("<p>rok urodzenia:");
            out.println("<input type=\"number\" min=\"1900\" max=\"" + today.getYear() +  "\" name=\"rokur\" />");
            out.println("<p>Miesiąć urodzenia:");
            out.println("<input type=\"number\" min=\"1\" max=\"12\" name=\"miesiacur\" />");
            out.println("<p>Dzień urodzenia:");
            out.println("<input type=\"number\" min=\"1\" max=\"31\" name=\"dzienur\" />");
            out.println("</p>");
            out.println("<p><input type=\"submit\" value=\"oblicz swój wiek\" /></p>");
            out.println("</form>");
        }
   else {
            int rokur = Integer.parseInt(rok);
            int miesiacur = Integer.parseInt(mie);
            int dzienur = Integer.parseInt(dzie);

            LocalDate birthday = LocalDate.of(rokur, miesiacur, dzienur);
            Period p = Period.between(birthday, today);
            int lata = p.getYears();
            int miesiace = p.getMonths();
            int dni = p.getDays();
            String l;
            if (lata == 1) l = "rok";
            else l = "lat";
            String m;
            if (miesiace == 1) m = "miesiąc";
            else if (miesiace == 2 || miesiace == 3 || miesiace == 4) m = "miesiące";
            else m = " miesięcy";
            String d;
            if (dni == 1) d = "dzień";
            else d = "dni";

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<title>Wiek osoby</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Wiek osoby</h1>");
            out.println("<form method=\"POST\">");
            out.println("<p>rok urodzenia:");
            out.println("<input type=\"number\" min=\"1900\" max=\"" + today.getYear() + "\" value=\"" + today.getYear() + "\" name=\"rokur\" />");
            out.println("<p>Miesiąć urodzenia:");
            out.println("<input type=\"number\" min=\"1\" max=\"12\" value=\"" + today.getMonth() + "\" name=\"miesiacur\" />");
            out.println("<p>Dzień urodzenia:");
            out.println("<input type=\"number\" min=\"1\" max=\"31\" value=\"" + today.getDayOfMonth() + "\" name=\"dzienur\" />");
            out.println("</p>");
            out.println("<p><input type=\"submit\" value=\"oblicz wiek\" /></p>");
            out.println("</form>");
            out.println("<p> Dzisiaj mamy:" + today.getDayOfMonth() + "  " + today.getMonth() + "  " + today.getYear() + " </p>");
            out.println("</p>");
            out.println("<h3> Twój wiek to:" + lata + " " + l+ " " + miesiace + " " + m+ " " + " i "+ " " + dni + " " + d + " <h3>");
            out.println("</p>");

            out.println("<h4>autor: " + getServletContext().getInitParameter("autor") + "<h4>");
            out.println("<h4>mail: " + getServletContext().getInitParameter("mail") + "<h4>");
            out.println("<hr />");
            out.println("<p><a href=\"index.html\">powrót</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }}