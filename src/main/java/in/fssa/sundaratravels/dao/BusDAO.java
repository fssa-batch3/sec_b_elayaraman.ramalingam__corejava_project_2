package in.fssa.sundaratravels.dao;

import java.sql.*;
import java.util.*;

import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.service.RouteServices;
import in.fssa.sundaratravels.util.ConnectionUtil;

public class BusDAO {

	public void createBus(Bus bus) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		RouteServices dao = new RouteServices();

		Route route = dao.getRouteById(bus.getRoute_id());

		if (route == null) {
			throw new RuntimeException("Invalid route id");
		}
		

		try {
			String query = "INSERT INTO buses (bus_no, departure_time, capacity, arrival_time, is_ac, route_id)"
					+ " VALUES(?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, bus.getBusNo());
			ps.setTime(2, bus.getDeparture_time());
			ps.setInt(3, bus.getCapacity());
			ps.setTime(4, bus.getArrival_time());
			ps.setBoolean(5, bus.isIs_ac());
			ps.setInt(6, bus.getRoute_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate")) {
				throw new Exception("Duplicate Entry");
			} else {
				System.out.println(e.getMessage());
				throw new Exception(e);
			}
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	public Bus getBus(int id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Bus bus = null;
		try {
			String query = "SELECT * FROM buses WHERE bus_id = ?";

			conn = ConnectionUtil.getConnection();

			ps = conn.prepareStatement(query);

			ps.setInt(1,id);

			rs = ps.executeQuery();

			if (rs.next()) {
				bus = new Bus();
				bus.setId(rs.getInt("id"));
				bus.setBusNo(rs.getString("bus_no"));
				bus.setDeparture_time(rs.getTime("departure_time"));
				bus.setRoute_id(rs.getInt("route_id"));
				bus.setSchedule_id(rs.getInt("schedule_id"));
				bus.setArrival_time(rs.getTime("arrival_time"));
				bus.setIs_ac(rs.getBoolean("is_ac"));
				bus.setIs_active(rs.getBoolean("is_active"));
				bus.setCapacity(rs.getInt("capacity"));
			}
		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return bus;
	}

	public List<Bus> getAllBuses() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Bus> list = null;

		try {
			String query = "SELECT * FROM buses WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			if (rs.next()) {
				list = new ArrayList<>();
				while (rs.next()) {
					Bus bus = extractBusFromResultSet(rs);
					list.add(bus);
				}
			}
		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return list;

	}

	public void updateBus(Bus bus) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		Bus isBusExist = getBus(bus.getId());
		if (isBusExist == null) {
			throw new RuntimeException("Invalid Bus Id");
		}

		try {
			String query = "UPDATE buses SET departure_time = ?, arrival_time = ? , route_id = ? WHERE bus_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setTime(1, bus.getDeparture_time());
			ps.setTime(2, bus.getArrival_time());
			ps.setInt(3, bus.getRoute_id());

			ps.executeUpdate();
			System.out.println("Bus Updated");

		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	public void switchBusStatus(int id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		Bus isBusExist = getBus(id);
		if (isBusExist == null) {
			throw new RuntimeException("Invalid Bus Id");
		}

		try {
			String query = "UPDATE buses SET  is_active = NOT is_active WHERE bus_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	private Bus extractBusFromResultSet(ResultSet rs) throws SQLException {
		Bus bus = new Bus();
		bus.setId(rs.getInt("id"));
		bus.setBusNo(rs.getString("bus_no"));
		bus.setDeparture_time(rs.getTime("departure_time"));
		bus.setArrival_time(rs.getTime("arrival_time"));
		bus.setIs_ac(rs.getBoolean("is_ac"));
		bus.setIs_active(rs.getBoolean("is_active"));
		bus.setRoute_id(rs.getInt("route_id"));
		bus.setCapacity(rs.getInt("capacity"));
		return bus;
	}
}
