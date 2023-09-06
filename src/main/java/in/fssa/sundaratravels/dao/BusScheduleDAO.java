package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.util.ConnectionUtil;
import in.fssa.sundaratravels.model.BusSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusScheduleDAO {

    public void createBusSchedule(BusSchedule busSchedule) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO bus_schedules (monday, tuesday, wednesday, thursday, friday, saturday, sunday) " +
                    "VALUES(?,?,?,?,?,?,?)"; // Remove the bus_id column
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, busSchedule.isMonday());
            ps.setBoolean(2, busSchedule.isTuesday());
            ps.setBoolean(3, busSchedule.isWednesday());
            ps.setBoolean(4, busSchedule.isThursday());
            ps.setBoolean(5, busSchedule.isFriday());
            ps.setBoolean(6, busSchedule.isSaturday());
            ps.setBoolean(7, busSchedule.isSunday());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }


    public BusSchedule getBusSchedule(int id) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BusSchedule busSchedule = null;

        try {
            String query = "SELECT * FROM bus_schedules WHERE schedule_id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                busSchedule = extractBusScheduleFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return busSchedule;
    }

    public List<BusSchedule> getAllBusSchedules() throws PersistenceException  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BusSchedule> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM bus_schedules WHERE is_active = 1";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                BusSchedule busSchedule = extractBusScheduleFromResultSet(rs);
                list.add(busSchedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException (e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void updateBusSchedule(BusSchedule busSchedule) throws PersistenceException  {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE bus_schedules SET monday=?, tuesday=?, wednesday=?, thursday=?, friday=?, saturday=?, sunday=? " +
                    "WHERE schedule_id=?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, busSchedule.isMonday());
            ps.setBoolean(2, busSchedule.isTuesday());
            ps.setBoolean(3, busSchedule.isWednesday());
            ps.setBoolean(4, busSchedule.isThursday());
            ps.setBoolean(5, busSchedule.isFriday());
            ps.setBoolean(6, busSchedule.isSaturday());
            ps.setBoolean(7, busSchedule.isSunday());
            ps.setInt(8, busSchedule.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException (e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public void deleteBusSchedule(int id) throws PersistenceException  {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE bus_schedules SET is_active = 0 WHERE schedule_id=?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException (e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    private BusSchedule extractBusScheduleFromResultSet(ResultSet rs) throws SQLException {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setId(rs.getInt("schedule_id"));
        busSchedule.setMonday(rs.getBoolean("monday"));
        busSchedule.setTuesday(rs.getBoolean("tuesday"));
        busSchedule.setWednesday(rs.getBoolean("wednesday"));
        busSchedule.setThursday(rs.getBoolean("thursday"));
        busSchedule.setFriday(rs.getBoolean("friday"));
        busSchedule.setSaturday(rs.getBoolean("saturday"));
        busSchedule.setSunday(rs.getBoolean("sunday"));
        return busSchedule;
    }
}
