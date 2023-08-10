package in.fssa.sundaratravels.service;


import java.util.List;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.dao.RouteDAO;
import in.fssa.sundaratravels.validator.RouteValidator;



public class RouteServices {
	
	RouteDAO routeDAO = new RouteDAO();
	
	public void createRoute(Route route) throws Exception {

		RouteValidator.validate(route);
		
		routeDAO.createRoute(route);
		
	}
	
	public List<Route> getAllRoutes() throws Exception{
		List<Route> list = routeDAO.getAllRoutes();
		return list;
	}
	
	public List<Route> getByFromLocation(String from) throws Exception{
		List<Route> list = routeDAO.getByFromLocation(from);
		return list;
	}
	
	public List<Route> getByToLocation(String to) throws Exception{
		List<Route> list = routeDAO.getByToLocation(to);
		return list;
	}
	
	public List<Route> getByFromLocationAndTolocation(String from, String to) throws Exception {
		List<Route> list = routeDAO.getByFromLocationAndTolocation(from, to);
		return list;
	}

	public void deleteRoute(int id) throws Exception {
		routeDAO.deleteRoute(id);
	}
	

}
