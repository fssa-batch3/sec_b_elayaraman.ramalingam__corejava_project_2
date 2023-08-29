package in.fssa.sundaratravels;

import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

import java.math.BigDecimal;

public class App {

	public static void main(String[] args) throws Exception {

		RouteServices routeDAO = new RouteServices();
		BusServices busServices = new BusServices();
		

		Route route = new Route();

		route.setFromLocation("Salem");
		route.setToLocation("Chennai");
		route.setBasePrice(BigDecimal.valueOf(100.50));

		routeDAO.createRoute(route);

		 route = routeDAO.getByFromLocationAndTolocation("chennai","salem");

		System.out.println(route.toString());
	}

}
