package in.fssa.sundaratravels.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.util.ConnectionUtil;

import in.fssa.sundaratravels.exception.ValidationException;

public class RouteDAO {

    public void createRoute(Route route) throws ValidationException, Exception {

		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (route.getFrom_location().equals(route.getTo_location()))
            throw new ValidationException("Both cannot be same");
        
        try{
            String query = "SELECT * FROM route WHERE from_location = ? AND to_location =?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, route.getFrom_location());
            ps.setString(2, route.getTo_location());
            rs = ps.executeQuery();
            if(rs.next()){
                throw new RuntimeException("Route is already exists");
            }
        }catch (SQLException e) {
			if (e.getMessage().contains("Dupicate")) {
				throw new Exception("Duplicate Entry");
			} else {
				System.out.println(e.getMessage());
				throw new Exception(e);
			}
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
                try{
            String query = "SELECT * FROM route WHERE from_location = ? AND to_location =?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(2, route.getFrom_location());
            ps.setString(1, route.getTo_location());
            rs = ps.executeQuery();
            if(rs.next()){
                throw new RuntimeException("Route is already exists");
            }
        }catch (SQLException e) {
			if (e.getMessage().contains("Dupicate")) {
				throw new Exception("Duplicate Entry");
			} else {
				System.out.println(e.getMessage());
				throw new Exception(e);
			}
		} finally {
			ConnectionUtil.close(conn, ps,rs);
		}

		try {
			String query = "INSERT INTO route (from_location,to_location) values (?,?)";

			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, route.getFrom_location());
			ps.setString(2, route.getTo_location());
			ps.executeUpdate();
			System.out.println("Route added");
		} catch (SQLException e) {
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

    public List<Route> getAllRoutes() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = null;
        try {
            String query = "SELECT * FROM route WHERE is_active = 1";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            if (rs.next()) {
                list = new ArrayList<>();
                while (rs.next()) {
                    Route r = new Route();
                    r.setRouteId(rs.getInt("id"));
                    r.setFrom_location(rs.getString("from_location"));
                    r.setTo_location(rs.getString("to_location"));
                    list.add(r);
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

    public List<Route> getByFromLocation(String from) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = null;
        try {
            String query = "SELECT * FROM route WHERE from_location = ?";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            list = new ArrayList<>();
            do {
                Route r = new Route();
                r.setRouteId(rs.getInt("id"));
                r.setFrom_location(rs.getString("from_location"));
                r.setTo_location(rs.getString("to_location"));
                list.add(r);
            } while (rs.next());
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

    public List<Route> getByToLocation(String to) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = null;
        try {
            String query = "SELECT * FROM route WHERE to_location = ?";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, to);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            list = new ArrayList<>();
            do {
                Route r = new Route();
                r.setRouteId(rs.getInt("id"));
                r.setFrom_location(rs.getString("from_location"));
                r.setTo_location(rs.getString("to_location"));
                list.add(r);
            } while (rs.next());
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
    
    public Route getRouteById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Route list = null;
        try {
            String query = "SELECT * FROM route WHERE id = ?";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            do {
                list = new Route();
                list.setRouteId(rs.getInt("id"));
                list.setFrom_location(rs.getString("from_location"));
                list.setTo_location(rs.getString("to_location"));
            } while (rs.next());
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

    public List<Route> getByFromLocationAndTolocation(String from, String to) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = null;
        try {
            String query = "SELECT * FROM route WHERE from_location = ? AND to_location = ?";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);
            ps.setString(2, to);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            list = new ArrayList<>();
            do {
                Route r = new Route();
                r.setRouteId(rs.getInt("id"));
                r.setFrom_location(rs.getString("from_location"));
                r.setTo_location(rs.getString("to_location"));
                list.add(r);
            } while (rs.next());
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

    public void deleteRoute(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE Route SET is_active = 0 WHERE id = ? ";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Route deleted");
        } catch (SQLException e) {
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
