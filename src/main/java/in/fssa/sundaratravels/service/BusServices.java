package in.fssa.sundaratravels.service;

import java.sql.Date;
import java.util.List;

import in.fssa.sundaratravels.dao.BusDAO;
import in.fssa.sundaratravels.exception.*;
import in.fssa.sundaratravels.model.Bus;
import static in.fssa.sundaratravels.validator.BusValidator.validate;

public class BusServices {

    final BusDAO busDAO = new BusDAO();

    public void createBus(Bus bus) throws ServicesException {
        try {
            validate(bus);
            busDAO.createBus(bus);
        }catch(ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

	public Bus getBus(int id) throws ServicesException {
        try{
            validate(id,"Bus");
            return busDAO.getBus(id);
        }catch (ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public List<Bus> getAllBuses() throws ServicesException {
        try{
            return busDAO.getAllBuses();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public List<Bus> getBusesByRouteIdAndDate(int routeId, Date date) throws ServicesException {
        try {
            validate(routeId, "Route");
            return busDAO.getBusesByRouteIdAndDate(routeId, date);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public void updateBus(Bus bus) throws Exception {
        try{validate(bus);
        busDAO.updateBus(bus);
    }catch(ValidationException | PersistenceException e){
        e.printStackTrace();
        throw new ServicesException(e.getMessage());
    }
    }

    public void switchBusStatus(int id) throws ServicesException {
        try{
            validate(id,"Bus");
            busDAO.switchBusStatus(id);
        }catch(ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }
}
