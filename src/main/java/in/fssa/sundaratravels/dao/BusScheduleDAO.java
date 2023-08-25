package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.util.ConnectionUtil;
import in.fssa.sundaratravels.model.BusSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusScheduleDAO {

    public void createBusSchedule(BusSchedule busSchedule) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO bus_schedules (bus_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, busSchedule.getBusId());
            ps.setBoolean(2, busSchedule.isMonday());
            ps.setBoolean(3, busSchedule.isTuesday());
            ps.setBoolean(4, busSchedule.isWednesday());
            ps.setBoolean(5, busSchedule.isThursday());
            ps.setBoolean(6, busSchedule.isFriday());
            ps.setBoolean(7, busSchedule.isSaturday());
            ps.setBoolean(8, busSchedule.isSunday());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public BusSchedule getBusSchedule(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BusSchedule busSchedule = null;

        try {
            String query = "SELECT * FROM bus_schedules WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                busSchedule = extractBusScheduleFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return busSchedule;
    }

    public List<BusSchedule> getAllBusSchedules() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BusSchedule> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM bus_schedules";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                BusSchedule busSchedule = extractBusScheduleFromResultSet(rs);
                list.add(busSchedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void updateBusSchedule(BusSchedule busSchedule) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE bus_schedules SET bus_id=?, monday=?, tuesday=?, wednesday=?, thursday=?, friday=?, saturday=?, sunday=? " +
                    "WHERE id=?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, busSchedule.getBusId());
            ps.setBoolean(2, busSchedule.isMonday());
            ps.setBoolean(3, busSchedule.isTuesday());
            ps.setBoolean(4, busSchedule.isWednesday());
            ps.setBoolean(5, busSchedule.isThursday());
            ps.setBoolean(6, busSchedule.isFriday());
            ps.setBoolean(7, busSchedule.isSaturday());
            ps.setBoolean(8, busSchedule.isSunday());
            ps.setInt(9, busSchedule.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public void deleteBusSchedule(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "DELETE FROM bus_schedules WHERE id=?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    private BusSchedule extractBusScheduleFromResultSet(ResultSet rs) throws SQLException {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setId(rs.getInt("id"));
        busSchedule.setBusId(rs.getInt("bus_id"));
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
