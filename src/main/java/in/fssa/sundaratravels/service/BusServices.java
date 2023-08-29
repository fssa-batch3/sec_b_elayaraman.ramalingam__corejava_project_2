package in.fssa.sundaratravels.service;

import java.util.List;

import com.google.protobuf.ServiceException;
import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.dao.BusDAO;
import static in.fssa.sundaratravels.validator.BusValidator.validate;

public class BusServices {

    BusDAO busDAO = new BusDAO();

    public void createBus(Bus bus) throws ServiceException {
        try {
            validate(bus);
            busDAO.createBus(bus);
        }catch(ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Bus getBus(int id) throws ServiceException {
        try{
            validate(id,"Bus");
            return busDAO.getBus(id);
        }catch (ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Bus> getAllBuses() throws ServiceException {
        try{
            return busDAO.getAllBuses();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateBus(Bus bus) throws Exception {
        try{validate(bus);
        busDAO.updateBus(bus);
    }catch(ValidationException | PersistenceException e){
        e.printStackTrace();
        throw new ServiceException(e.getMessage());
    }
    }

    public void switchBusStatus(int id) throws ServiceException {
        try{
            validate(id,"Bus");
            busDAO.switchBusStatus(id);
        }catch(ValidationException | PersistenceException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
