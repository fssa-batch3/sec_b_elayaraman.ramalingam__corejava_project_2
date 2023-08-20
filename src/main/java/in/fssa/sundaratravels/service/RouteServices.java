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
	
	public Route getByFromLocationAndTolocation(String from, String to) throws Exception {
		Route route = routeDAO.getByFromLocationAndToLocation(from, to);
		return route;
	}

	public void deleteRoute(int id) throws Exception {
		routeDAO.deleteRoute(id);
	}
	public Route getRouteById(int id) throws Exception {
        return routeDAO.getRouteById(id);
    }
	

}
