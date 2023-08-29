package in.fssa.sundaratravels.service;

import java.util.List;

import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.dao.RouteDAO;
import in.fssa.sundaratravels.validator.RouteValidator;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.exception.PersistenceException;

public class RouteServices {

	final RouteDAO routeDAO = new RouteDAO();

	public void createRoute(Route route) throws ServicesException {
		try {
			RouteValidator.validate(route);

			routeDAO.createRoute(route);
		} catch (ValidationException | PersistenceException e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public List<Route> getAllRoutes() throws ServicesException {
		try {
            return routeDAO.getAllRoutes();
		} catch (Exception e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public List<Route> getByFromLocation(String from) throws ServicesException {
		try {
            return routeDAO.getByFromLocation(from);
		} catch (Exception e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public List<Route> getByToLocation(String to) throws ServicesException {
		try {
            return routeDAO.getByToLocation(to);
		} catch (PersistenceException e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public Route getByFromLocationAndTolocation(String from, String to) throws ServicesException {
		try {
            return routeDAO.getByFromLocationAndToLocation(from, to);
		} catch (PersistenceException e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public void deleteRoute(int id) throws ServicesException {
		try {
			routeDAO.deleteRoute(id);
		} catch (PersistenceException e) {
			throw new ServicesException(e.getMessage());
		}
	}

	public Route getRouteById(int id) throws ServicesException {
		try {
			return routeDAO.getRouteById(id);
		} catch (PersistenceException e) {
			throw new ServicesException(e.getMessage());
		}
	}
}
