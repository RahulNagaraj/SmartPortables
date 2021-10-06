import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

@WebServlet("/OrderConfirmation")
public class OrderConfirmation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String userName = request.getParameter("userName");
        String customerName=request.getParameter("customerName");
        String streetAddress=request.getParameter("streetAddress");
        String cityAddress=request.getParameter("cityAddress");
        String stateAddress=request.getParameter("stateAddress");
        String zipcode=request.getParameter("zipcode");
        String creditCardNo=request.getParameter("creditCardNo");
        String deliveryMethod = request.getParameter("deliveryMethod");

        String pickupStoreName=request.getParameter("pickupStoreName");
        String orderDate=request.getParameter("orderDate");
        String deliveryDate=request.getParameter("deliveryDate");
        String maxOrderCancellationDate = request.getParameter("maxOrderCancellationDate");
        String maxPickupDate = request.getParameter("maxPickupDate");


        Transaction transaction = new Transaction(
                orderId,
                userName,
                customerName,
                streetAddress,
                cityAddress,
                stateAddress,
                zipcode,
                creditCardNo,
                deliveryMethod,
                pickupStoreName,
                orderDate,
                null,
                maxOrderCancellationDate,
                maxPickupDate
        );

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order Confirmation</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h2>Your Order");
        pw.print("&nbsp&nbsp");
        pw.print("is stored ");
        pw.print("<br>Your Order No is "+(orderId));
        pw.print("<br>Your Order Date is "+(orderDate));
        pw.print("<br>Your Pickup Date is "+(maxPickupDate));
        pw.print("<br>You can cancel your order before "+(maxOrderCancellationDate));
        pw.print("</h2>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");

        MySqlDataStoreUtilities.insertTransaction(transaction);
    }
}
