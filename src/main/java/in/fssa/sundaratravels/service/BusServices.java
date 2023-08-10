package in.fssa.sundaratravels.service;

import java.util.List;

import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.dao.BusDAO;
import in.fssa.sundaratravels.validator.BusValidator;

public class BusServices {

    BusDAO busDAO = new BusDAO();

    public void createBus(Bus bus) throws Exception {
        BusValidator.validate(bus);
        busDAO.createBus(bus);
    }

    public Bus getBus(int id) throws Exception {
        return busDAO.getBus(id);
    }

    public List<Bus> getAllBuses() throws Exception {
        return busDAO.getAllBuses();
    }

    public void updateBus(Bus bus) throws Exception {
        BusValidator.validate(bus);
        busDAO.updateBus(bus);
    }

    public void switchBusStatus(int id) throws Exception {
        busDAO.switchBusStatus(id);
    }
}
