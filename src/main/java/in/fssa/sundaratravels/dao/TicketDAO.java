package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Ticket;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public int createTicket(Ticket ticket) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ticketId = -1;
        try {
            String query = "INSERT INTO `tickets` (`booking_id`, `travel_date`, `booked_seats`, `passenger_name`, `phone_number`, `total_price`) VALUES (?, ?, ?, ?, ?, ?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ticket.getBookingId());
            ps.setDate(2, ticket.getTravelDate());
            ps.setInt(3, ticket.getBookedSeats());
            ps.setString(4, ticket.getPassengerName());
            ps.setLong(5, ticket.getPhoneNumber());
            ps.setBigDecimal(6, ticket.getTotalPrice());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticketId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return ticketId;
    }

    public Ticket getTicketById(int ticketId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket ticket = null;

        try {
            String query = "SELECT * FROM `tickets` WHERE `ticket_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ticketId);

            rs = ps.executeQuery();

            if (rs.next()) {
                ticket = extractTicketFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return ticket;
    }

    public List<Ticket> getAllTickets() throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM `tickets` WHERE `is_active` = TRUE";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ticket ticket = extractTicketFromResultSet(rs);
                list.add(ticket);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void cancelTicket(int ticketId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE `tickets` SET `is_active` = 0 WHERE `ticket_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ticketId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public void switchTicketStatus(int ticketId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE `tickets` SET `is_active` = NOT `is_active` WHERE `ticket_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ticketId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketId(rs.getInt("ticket_id"));
        ticket.setBookingId(rs.getInt("booking_id"));
        ticket.setBookedSeats(rs.getInt("booked_seats"));
        ticket.setTravelDate(rs.getDate("travel_date"));
        ticket.setPassengerName(rs.getString("passenger_name"));
        ticket.setPhoneNumber(rs.getLong("phone_number"));
        ticket.setTotalPrice(rs.getBigDecimal("total_price"));
        ticket.setActive(rs.getBoolean("is_active"));
        return ticket;
    }
}
