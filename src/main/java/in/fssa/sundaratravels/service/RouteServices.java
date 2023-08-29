package in.fssa.sundaratravels.service;

import java.sql.SQLException;
import java.util.List;

import in.fssa.sundaratravels.exception.ServiceException;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.dao.RouteDAO;
import in.fssa.sundaratravels.validator.RouteValidator;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.exception.PersistenceException;

public class RouteServices {

	RouteDAO routeDAO = new RouteDAO();

	public void createRoute(Route route) throws ServiceException {
		try {
			RouteValidator.validate(route);

			routeDAO.createRoute(route);
		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Route> getAllRoutes() throws ServiceException {
		try {
			List<Route> list = routeDAO.getAllRoutes();
			return list;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Route> getByFromLocation(String from) throws ServiceException {
		try {
			List<Route> list = routeDAO.getByFromLocation(from);
			return list;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Route> getByToLocation(String to) throws ServiceException {
		try {
			List<Route> list = routeDAO.getByToLocation(to);
			return list;
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Route getByFromLocationAndTolocation(String from, String to) throws ServiceException {
		try {
			Route route = routeDAO.getByFromLocationAndToLocation(from, to);
			return route;
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteRoute(int id) throws ServiceException {
		try {
			routeDAO.deleteRoute(id);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Route getRouteById(int id) throws ServiceException {
		try {
			return routeDAO.getRouteById(id);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
