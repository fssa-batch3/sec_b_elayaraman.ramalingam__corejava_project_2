package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Ticket;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public void createTicket(Ticket ticket) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            int bookingId = ticket.getBookingId();
            int bookedSeats = ticket.getBookedSeats();
            BigDecimal totalPrice = calculateTotalPrice(ticket.getTravelDate(), bookedSeats);

            if (bookingId == 0) { // New booking required for a new ticket
                ps = conn.prepareStatement(
                        "INSERT INTO bookings (bus_id, travel_date, booked_seats) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setInt(1, ticket.getBookingId()); // Replace with the actual bus ID
                ps.setDate(2, new java.sql.Date(ticket.getTravelDate().getTime()));
                ps.setInt(3, bookedSeats);
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    bookingId = rs.getInt(1);
                }
            } else { // Update existing booking's seat count
                ps = conn.prepareStatement(
                        "UPDATE bookings SET booked_seats = booked_seats + ? WHERE booking_id = ?"
                );
                ps.setInt(1, bookedSeats);
                ps.setInt(2, bookingId);
                ps.executeUpdate();
            }

            ps = conn.prepareStatement(
                    "INSERT INTO tickets (booking_id, travel_date, booked_seats, total_price) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, bookingId);
            ps.setDate(2, new java.sql.Date(ticket.getTravelDate().getTime()));
            ps.setInt(3, bookedSeats);
            ps.setBigDecimal(4, totalPrice);
            ps.executeUpdate();

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
    }

    public List<Ticket> getAllTickets() throws PersistenceException {
        List<Ticket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM tickets");
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractTicketFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public List<Ticket> getTicketsByDate(Date travelDate) throws PersistenceException {
        List<Ticket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM tickets WHERE travel_date = ?");
            ps.setDate(1, travelDate);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractTicketFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public Ticket getTicketByNumber(int ticketNumber) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM tickets WHERE ticket_id = ?");
            ps.setInt(1, ticketNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractTicketFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return null;
    }

    public List<Ticket> getTicketsByBusNo(String busNo) throws PersistenceException {
        List<Ticket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(
                    "SELECT t.* FROM tickets t JOIN bookings b ON t.booking_id = b.booking_id JOIN buses bu ON b.bus_id = bu.bus_id WHERE bu.bus_no = ?"
            );
            ps.setString(1, busNo);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractTicketFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    private BigDecimal calculateTotalPrice(Date travelDate, int bookedSeats) {
        // Logic to calculate total price based on travel date, booked seats, and bus type (AC/Non-AC)
        // Replace with your actual pricing logic
        return BigDecimal.ZERO; // Replace with the calculated total price
    }

    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("ticket_id"));
        ticket.setBookingId(rs.getInt("booking_id"));
        ticket.setTravelDate(rs.getDate("travel_date"));
        ticket.setBookedSeats(rs.getInt("booked_seats"));
        ticket.setTotalPrice(rs.getBigDecimal("total_price"));
        return ticket;
    }
}
