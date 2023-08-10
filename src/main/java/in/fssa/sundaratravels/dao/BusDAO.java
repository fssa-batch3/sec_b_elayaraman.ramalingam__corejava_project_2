package in.fssa.sundaratravels.dao;

import java.sql.*;
import java.util.*;

import in.fssa.sundaratravels.model.*;
import in.fssa.sundaratravels.util.ConnectionUtil;
import in.fssa.sundaratravels.service.*;
public class BusDAO {
	
	public void createBus(Bus bus) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		RouteServices dao = new RouteServices();
		
		Route route = dao.getRouteById(bus.getRoute_id());
		
		System.out.println(route);
		
		if(route == null)throw new RuntimeException("Invalid route id");
		
		try {
			String query = "INSERT INTO bus (bus_no, departure_time, arrival_time, schedule_id, is_ac, route_id)"
					+ " VALUES(?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, bus.getBusNo());
	        ps.setTime(2, bus.getDeparture_time());
	        ps.setTime(3, bus.getArrival_time());
	        ps.setInt(4, bus.getSchedule_id());
	        ps.setBoolean(5, bus.isIs_ac());
	        ps.setInt(6, bus.getRoute_id());
	        
	        ps.executeUpdate();
		}catch (SQLException e) {
			if (e.getMessage().contains("Dupicate")) {
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
			String query = "SELECT * FROM bus WHERE id = ?";
			
			conn = ConnectionUtil.getConnection();
			
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bus = new Bus();
				bus.setId(rs.getInt("id"));
				bus.setBusNo(rs.getString("bus_no"));
				bus.setDeparture_time(rs.getTime("departure_time"));
				bus.setRoute_id(rs.getInt("route_id"));
				bus.setSchedule_id(rs.getInt("schedule_id"));
				bus.setArrival_time(rs.getTime("arrival_time"));
				bus.setIs_ac(rs.getBoolean("is_ac"));
				bus.setIs_active(rs.getBoolean("is_active"));
			}
		}catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
		return bus;
	}
	
	public List<Bus> getAllBuses() throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bus> list = null;
        
        try {
        	String query = "SELECT * FROM bus WHERE is_active = 1";
        	conn = ConnectionUtil.getConnection();
        	ps = conn.prepareStatement(query);
        	
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		list = new ArrayList<>();
        		while(rs.next()) {
        			Bus bus = new Bus();
    				bus.setId(rs.getInt("id"));
    				bus.setBusNo(rs.getString("bus_no"));
    				bus.setDeparture_time(rs.getTime("departure_time"));
    				bus.setRoute_id(rs.getInt("route_id"));
    				bus.setSchedule_id(rs.getInt("schedule_id"));
    				bus.setArrival_time(rs.getTime("arrival_time"));
    				bus.setIs_ac(rs.getBoolean("is_ac"));
    				bus.setIs_active(rs.getBoolean("is_active"));
        			
        		}
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
		
	}
	public void updateBus(Bus bus) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		
		Bus bustest = getBus(bus.getId());
		if(bustest == null) {
			throw new RuntimeException("Invalid Bus Id");
		}
		
		try {
			String query = "UPDATE bus SET departure_time = ?, arrival_time = ? , route_id = ? WHERE bus_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setTime(1, bus.getDeparture_time());
			ps.setTime(2, bus.getArrival_time());
			ps.setInt(3, bus.getRoute_id());
			
			ps.executeUpdate();
			System.out.println("Bus Updated");
			
		}catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
		
	}
	public void switchBusStatus(int id) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		
		Bus bustest = getBus(id);
		if(bustest == null) {
			throw new RuntimeException("Invalid Bus Id");
		}
		
		try {
			String query = "UPDATE bus SET  is_active = NOT is_active WHERE bus_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,id );
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
		
	}

}
