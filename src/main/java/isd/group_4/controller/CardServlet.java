package isd.group_4.controller;

import isd.group_4.Card;
import isd.group_4.User;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CardServlet")
public class CardServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        try {
            List<Card> cardList = database.Cards().getCardsByUserId(loggedInUser.getUserID());
            req.setAttribute("cardList", cardList);

            String action = req.getParameter("action");
            if ("edit".equals(action)) {
                int cardID = Integer.parseInt(req.getParameter("cardID"));
                Card card = database.Cards().get(cardID);
                req.setAttribute("card", card);
                req.getRequestDispatcher("editCardDetails.jsp").forward(req, resp);
            } else if ("add".equals(action)) {
                req.getRequestDispatcher("add-card.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("card-list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("failText", "Error: " + e.getMessage());
            req.getRequestDispatcher("card-list.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                Card card = extractCardFromRequest(req, loggedInUser.getUserID());
                database.Cards().add(card);
                session.setAttribute("successMessage", "Card added successfully!");
            } else if ("edit".equals(action)) {
                Card card = extractCardFromRequest(req, loggedInUser.getUserID());
                card.setCardID(Integer.parseInt(req.getParameter("cardID")));
                database.Cards().update(card.getCardID(), card);
                session.setAttribute("successMessage", "Card updated successfully!");
            } else if ("delete".equals(action)) {
                int cardID = Integer.parseInt(req.getParameter("cardID"));
                database.Cards().delete(cardID);
                session.setAttribute("successMessage", "Card deleted successfully!");
            }
            else if ("editPage".equals(action)) {
                int cardID = Integer.parseInt(req.getParameter("cardID"));
                Card card = database.Cards().get(cardID);
                req.setAttribute("card", card);
                req.getRequestDispatcher("editCardDetails.jsp").forward(req, resp);
                return; // Don't redirect after forward!
            }
            resp.sendRedirect("CardServlet");
        } catch (Exception e) {
            session.setAttribute("failText", "Error: " + e.getMessage());
            resp.sendRedirect("CardServlet");
        }
    }

    public Card extractCardFromRequest(HttpServletRequest req, int userID) {
        Card card = new Card();
        card.setUserID(userID);
        card.setCardTypeID(Integer.parseInt(req.getParameter("cardTypeID")));
        card.setBankName(req.getParameter("bankName"));
        card.setCardNumber(req.getParameter("cardNumber"));
        card.setCardHolderName(req.getParameter("cardHolderName"));
        card.setCardExpiryDate(req.getParameter("cardExpiryDate"));
        return card;
    }
}
