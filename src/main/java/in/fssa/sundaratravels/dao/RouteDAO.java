package in.fssa.sundaratravels.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.util.ConnectionUtil;

import static in.fssa.sundaratravels.util.ConnectionUtil.getConnection;

public class RouteDAO {

    public void createRoute(Route route) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            String query = "INSERT INTO routes (from_location, to_location, base_price) VALUES (?, ?, ?)";
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, route.getFromLocation());
            ps.setString(2, route.getToLocation());
            ps.setBigDecimal(3, route.getBasePrice());

            rs = ps.executeUpdate();

            if (rs > 0) {
                System.out.println("route created");
            } else {
                System.out.println("Route is not created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public void updateRoutePrice(int id, BigDecimal price) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE routes SET base_price = ? WHERE route_id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, price);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Route price updated successfully");
            } else {
                System.out.println("No route found with the specified ID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }


    public List<Route> getAllRoutes() throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT route_id,from_location,to_location,base_price,is_active FROM routes WHERE is_active = 1";

            conn = getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public Route getRouteById(int id) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Route route = null;

        try {
            String query = "SELECT route_id,from_location,to_location,base_price,is_active FROM routes WHERE route_id = ?";

            conn = getConnection();

            ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                route = extractRouteFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return route;
    }

    public List<Route> getByFromLocation(String from) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT route_id,from_location,to_location,base_price,is_active FROM routes WHERE from_location = ? AND is_active = 1";

            conn = getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }

            if (list.isEmpty()) throw new SQLException("No routes with this location as from location");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public List<Route> getByToLocation(String to) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT route_id,from_location,to_location,base_price,is_active FROM routes WHERE to_location = ? AND is_active = 1";

            conn = getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, to);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }
            if (list.isEmpty()) throw new SQLException("No routes with this location as to location");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void deleteRoute(int id) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE routes SET is_active = 0 WHERE route_id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Route deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public Route getByFromLocationAndToLocation(String from, String to) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Route route = null;

        try {
            String query = "SELECT route_id,from_location,to_location,base_price,is_active FROM routes WHERE (from_location = ? AND to_location = ?)";

            conn = getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);
            ps.setString(2, to);

            rs = ps.executeQuery();

            while (rs.next()) {
                route = new Route();
                route = extractRouteFromResultSet(rs);
            }

            if (route == null) throw new SQLException("No routes with this locations");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return route;
    }

    private Route extractRouteFromResultSet(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setRouteId(rs.getInt("route_id"));
        route.setFromLocation(rs.getString("from_location"));
        route.setToLocation(rs.getString("to_location"));
        route.setBasePrice(rs.getBigDecimal("base_price"));
        route.isActive(rs.getBoolean("is_active"));
        return route;
    }
}
