package in.fssa.sundaratravels;

import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

public class App {

	public static void main(String[] args) throws Exception {

		RouteServices dao = new RouteServices();

		Route route = new Route();

		route.setFrom_location("null");
		route.setTo_location(null);

		dao.createRoute(route);

	}

}
