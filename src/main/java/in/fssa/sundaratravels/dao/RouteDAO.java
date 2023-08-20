package in.fssa.sundaratravels.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.util.ConnectionUtil;
import in.fssa.sundaratravels.exception.ValidationException;

public class RouteDAO {

    public void createRoute(Route route) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        try {
            String query = "INSERT INTO route (from_location, to_location, base_price) VALUES (?, ?, ?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, route.getFrom_location());
            ps.setString(2, route.getTo_location());
            ps.setBigDecimal(3, route.getBasePrice());

            if (doesReverseCombinationExist(conn, route.getTo_location(), route.getFrom_location())) {
                throw new RuntimeException("Reverse route combination already exists");
            }

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int routeId = generatedKeys.getInt(1);
                route.setRouteId(routeId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, generatedKeys);
        }
    }

    private boolean doesReverseCombinationExist(Connection conn, String fromLocation, String toLocation) throws SQLException {
        String query = "SELECT id FROM route WHERE from_location = ? AND to_location = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, fromLocation);
            ps.setString(2, toLocation);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Route> getAllRoutes() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM route WHERE is_active = 1";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }
        } catch (SQLException e) {
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
        Route route = null;

        try {
            String query = "SELECT * FROM route WHERE id = ?";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                route = extractRouteFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return route;
    }

    public List<Route> getByFromLocation(String from) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM route WHERE from_location = ? AND is_active = 1";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }
        } catch (SQLException e) {
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
        List<Route> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM route WHERE to_location = ? AND is_active = 1";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, to);

            rs = ps.executeQuery();

            while (rs.next()) {
                Route route = extractRouteFromResultSet(rs);
                list.add(route);
            }
        } catch (SQLException e) {
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
            String query = "UPDATE route SET is_active = 0 WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Route deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public Route getByFromLocationAndToLocation(String from, String to) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Route route = null;

        try {
            String query = "SELECT * FROM route WHERE (from_location = ? AND to_location = ?) OR (from_location = ? AND to_location = ?)";

            conn = ConnectionUtil.getConnection();

            ps = conn.prepareStatement(query);

            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, to);
            ps.setString(4, from);

            rs = ps.executeQuery();

            while (rs.next()) {
                route = new Route();
                route = extractRouteFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return route;
    }



    private Route extractRouteFromResultSet(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setRouteId(rs.getInt("id"));
        route.setFrom_location(rs.getString("from_location"));
        route.setTo_location(rs.getString("to_location"));
        route.setBasePrice(rs.getBigDecimal("base_price"));
        return route;
    }
}
