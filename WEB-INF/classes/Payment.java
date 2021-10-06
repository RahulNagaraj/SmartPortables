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

@WebServlet("/Payment")

public class Payment extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet

		HttpSession session=request.getSession();
		String streetAddress=request.getParameter("streetAddress");
		String cityAddress=request.getParameter("cityAddress");
		String stateAddress=request.getParameter("stateAddress");
		String zipcode=request.getParameter("zipcode");
		String customerName=request.getParameter("customerName");
		boolean isWarrantyIncluded = request.getParameter("hasWarranty").equals("true");
		String deliveryMethod = request.getParameter("deliveryMethod");
		String creditCardNo=request.getParameter("creditCardNo");
		String userName = session.getAttribute("username").toString();

		double orderTotal = Double.parseDouble(request.getParameter("orderTotal"));
		double warrantyPrice = Double.parseDouble(request.getParameter("warrantyPrice"));
		double discountPrice = Double.parseDouble(request.getParameter("discount"));

		List<Store> storeList = MySqlDataStoreUtilities.getAllStores();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String deliveryDate = sdf.format(cal.getTime());
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 9);
		String maxOrderCancellationDate = sdf.format(cal.getTime());
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
		String maxPickupDate = sdf.format(cal.getTime());
		System.out.print(creditCardNo);
		if(!creditCardNo.isEmpty() ) {
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders()) {

				//set the parameter for each column and execute the prepared statement

				Customer customer = new Customer(
						customerName,
						streetAddress,
						cityAddress,
						stateAddress,
						zipcode
				);

				CustomerOrder order = new CustomerOrder(
						orderId,
						userName,
						oi.getName(),
						oi.getPrice(),
						isWarrantyIncluded,
						discountPrice,
						orderTotal,
						warrantyPrice
				);

				System.out.println("customer order: " + order.toString());

				utility.storePayment(order);
				utility.storeCustomer(customer);
			}

			//remove the order details from cart after processing

			OrdersHashMap.orders.remove(utility.username());
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order Confirmation</a>");
			pw.print("</h2><div class='entry'>");

			if (deliveryMethod.equals("In Store Pickup")) {

				request.setAttribute("customerName", customerName);

				pw.print("<form method='post' action='OrderConfirmation'>");
				pw.print("<table  class='gridtable'><tr><td>Pickup Store Name:</td><td>");
				pw.print("<select name='pickupStoreName'>");
				for (Store store : storeList) {
					pw.print("<option name='"+ store.getStoreId() +"'>" + store.getStoreId() + "</option>");
				}

				pw.print("</select>");
				pw.print("</td></tr>");
				pw.print("</table>" +
						"<input type='hidden' name='orderId' value='" + orderId + "' class='btnbuy'>" +
						"<input type='hidden' name='userName' value='" + userName + "' class='btnbuy'>" +
						"<input type='hidden' name='customerName' value='" + customerName + "' class='btnbuy'>" +
						"<input type='hidden' name='streetAddress' value='" + streetAddress + "' class='btnbuy'>" +
						"<input type='hidden' name='cityAddress' value='" + cityAddress + "' class='btnbuy'>" +
						"<input type='hidden' name='stateAddress' value='" + stateAddress + "' class='btnbuy'>" +
						"<input type='hidden' name='zipcode' value='" + zipcode + "' class='btnbuy'>" +
						"<input type='hidden' name='creditCardNo' value='" + creditCardNo + "' class='btnbuy'>" +
						"<input type='hidden' name='deliveryMethod' value='" + deliveryMethod + "' class='btnbuy'>" +
						"<input type='hidden' name='orderDate' value='" + orderDate + "' class='btnbuy'>" +
						"<input type='hidden' name='deliveryDate' value='" + deliveryDate + "' class='btnbuy'>" +
						"<input type='hidden' name='maxOrderCancellationDate' value='" + maxOrderCancellationDate + "' class='btnbuy'>" +
						"<input type='hidden' name='maxPickupDate' value='" + maxPickupDate + "' class='btnbuy'>" +

						"<br />" +
						"<input type='submit' name='order' value='Order' class='btnbuy'>" +
						"</form>");
			} else {
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
						null,
						orderDate,
						deliveryDate,
						maxOrderCancellationDate,
						null
				);
				MySqlDataStoreUtilities.insertTransaction(transaction);

				pw.print("<h2>Your Order");
				pw.print("&nbsp&nbsp");
				pw.print("is stored ");
				pw.print("<br>Your Order No is "+(orderId));
				pw.print("<br>Your Order Date is "+(orderDate));
				pw.print("<br>Your Delivery Date is "+(deliveryDate));
				pw.print("<br>You can cancel your order before "+(maxOrderCancellationDate));
				pw.print("</h2>");
			}
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


	}
}
