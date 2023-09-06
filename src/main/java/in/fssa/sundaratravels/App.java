package in.fssa.sundaratravels;

import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception {

		RouteServices routeDAO = new RouteServices();
		BusServices busServices = new BusServices();
		BookingDAO bookingDAO = new BookingDAO();

//		Route route = new Route();
//
//		route.setFromLocation("Chennnai");
//		route.setToLocation("Salem");
//		route.setBasePrice(BigDecimal.valueOf(100.50));
//
//		routeDAO.createRoute(route);


//		 route = routeDAO.getByFromLocationAndTolocation("chennai","salem");

		List<Route> list = routeDAO.getAllRoutes();
		System.out.println(list);

		 Booking booking = bookingDAO.getBookingByBusAndDate(1, Date.valueOf("2023-09-14"));


//		System.out.println(route.toString());


	}

}
