package in.fssa.sundaratravels.service;

import in.fssa.sundaratravels.dao.BusScheduleDAO;
import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.exception.ServiceException;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.BusSchedule;

import static in.fssa.sundaratravels.validator.BusScheduleValidator.validate;

import java.util.List;

public class BusScheduleServices {

    private final BusScheduleDAO busScheduleDAO = new BusScheduleDAO();

    public void createBusSchedule(BusSchedule busSchedule) throws ServiceException {
        try {
            validate(busSchedule);
            busScheduleDAO.createBusSchedule(busSchedule);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public BusSchedule getBusSchedule(int id) throws ServiceException {
        try {
            validate(id);
            return busScheduleDAO.getBusSchedule(id);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<BusSchedule> getAllBusSchedules() throws ServiceException {
        try {
            return busScheduleDAO.getAllBusSchedules();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateBusSchedule(BusSchedule busSchedule) throws ServiceException {
        try {
            validate(busSchedule);
            busScheduleDAO.updateBusSchedule(busSchedule);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteBusSchedule(int id) throws ServiceException {
        try {
            validate(id);
            busScheduleDAO.deleteBusSchedule(id);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
