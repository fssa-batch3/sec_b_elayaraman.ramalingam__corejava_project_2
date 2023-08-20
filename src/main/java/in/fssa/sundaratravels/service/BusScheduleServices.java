package in.fssa.sundaratravels.service;

import in.fssa.sundaratravels.dao.BusScheduleDAO;
import in.fssa.sundaratravels.model.BusSchedule;

import java.util.List;

public class BusScheduleServices {

    private final BusScheduleDAO busScheduleDAO = new BusScheduleDAO();

    public void createBusSchedule(BusSchedule busSchedule) throws Exception {
        busScheduleDAO.createBusSchedule(busSchedule);
    }

    public BusSchedule getBusSchedule(int id) throws Exception {
        return busScheduleDAO.getBusSchedule(id);
    }

    public List<BusSchedule> getAllBusSchedules() throws Exception {
        return busScheduleDAO.getAllBusSchedules();
    }

    public void updateBusSchedule(BusSchedule busSchedule) throws Exception {
        busScheduleDAO.updateBusSchedule(busSchedule);
    }

    public void deleteBusSchedule(int id) throws Exception {
        busScheduleDAO.deleteBusSchedule(id);
    }
}
