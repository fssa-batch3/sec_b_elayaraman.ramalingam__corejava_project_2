package in.fssa.sundaratravels.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.service.BusScheduleServices;
import in.fssa.sundaratravels.util.ConnectionUtil;

public class BusDAO {

    BusScheduleServices busScheduleServices = new BusScheduleServices();

	public void createBus(Bus bus) throws PersistenceException {
		try (Connection conn = ConnectionUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(
					 "INSERT INTO buses (bus_no, departure_time, capacity, arrival_time, is_ac, route_id, schedule_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
			 )
		) {
			ps.setString(1, bus.getBusNo());
			ps.setTime(2, bus.getDepartureTime());
			ps.setInt(3, bus.getCapacity());
			ps.setTime(4, bus.getArrivalTime());
			ps.setBoolean(5, bus.isAc());
			ps.setInt(6, bus.getRouteId());
			ps.setInt(7, bus.getScheduleId());
			ps.executeUpdate();
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate")) {
				throw new PersistenceException("Duplicate Entry");
			} else {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public Bus getBus(int id) throws PersistenceException {
		try (Connection conn = ConnectionUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(
					 "SELECT * FROM buses WHERE bus_id = ?"
			 )
		) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return extractBusFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return null;
	}

	public List<Bus> getAllBuses() throws PersistenceException {
		List<Bus> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(
					 "SELECT * FROM buses WHERE is_active = 1"
			 )
		) {
			while (rs.next()) {
				list.add(extractBusFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return list;
	}

	public void updateBus(Bus bus) throws PersistenceException {
		Bus existingBus = getBus(bus.getId());
		if (existingBus == null) {
			throw new RuntimeException("Invalid Bus Id");
		}
		try (Connection conn = ConnectionUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(
					 "UPDATE buses SET departure_time = ?, arrival_time = ?, route_id = ? schedule_id = ? WHERE bus_id = ?"
			 )
		) {
			ps.setTime(1, bus.getDepartureTime());
			ps.setTime(2, bus.getArrivalTime());
			ps.setInt(3, bus.getRouteId());
			ps.setInt(4, bus.getScheduleId());
			ps.setInt(5, bus.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void switchBusStatus(int id) throws PersistenceException {
		Bus existingBus = getBus(id);
		if (existingBus == null) {
			throw new RuntimeException("Invalid Bus Id");
		}
		try (Connection conn = ConnectionUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(
					 "UPDATE buses SET is_active = NOT is_active WHERE bus_id = ?"
			 )
		) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	private Bus extractBusFromResultSet(ResultSet rs) throws SQLException {
		Bus bus = new Bus();
		bus.setId(rs.getInt("bus_id"));
		bus.setBusNo(rs.getString("bus_no"));
		bus.setDepartureTime(rs.getTime("departure_time"));
		bus.setArrivalTime(rs.getTime("arrival_time"));
		bus.setAc(rs.getBoolean("is_ac"));
		bus.setIsActive(rs.getBoolean("is_active"));
		bus.setRouteId(rs.getInt("route_id"));
		bus.setCapacity(rs.getInt("capacity"));
		bus.setScheduleId(rs.getInt("schedule_id"));
		return bus;
	}

	public List<Bus> getAllBusesByRouteId(int routeId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Bus> list = new ArrayList<>();

		try {
			String query = "SELECT * FROM buses WHERE route_id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, routeId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Bus bus = extractBusFromResultSet(rs);
				list.add(bus);
			}
		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return list;
	}

	public Bus getBusByBusNo(String busNo) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Bus bus = null;

		try {
			String query = "SELECT * FROM buses WHERE bus_no = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, busNo);

			rs = ps.executeQuery();
			if (rs.next()) {
				bus = extractBusFromResultSet(rs);
			}
		} catch (SQLException | RuntimeException e) {
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return bus;
	}
}
