package in.fssa.sundaratravels;

import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

import java.math.BigDecimal;
import java.sql.Time;

public class App {

	public static void main(String[] args) throws Exception {

		RouteServices dao = new RouteServices();
		BusServices busServices = new BusServices();
		

		Route route = new Route();

//		route.setFrom_location("Chennai");
//		route.setTo_location("Salem");
//		route.setBasePrice(BigDecimal.valueOf(100.50));

		//dao.createRoute(route);

		 route = dao.getByFromLocationAndTolocation("chennai","salem");

		System.out.println(route.toString());
//
//        Bus bus = new Bus();
//
//        bus.setBusNo("BUS12kffkfk3");
//        bus.setDeparture_time(Time.valueOf("08:00:00"));
//        bus.setArrival_time(Time.valueOf("11:00:00"));
//        bus.setSchedule_id(1);
//        bus.setIs_ac(true);
//        bus.setIs_active(true);
//        bus.setRoute_id(1);
//
//        busServices.createBus(bus);

	}

}
