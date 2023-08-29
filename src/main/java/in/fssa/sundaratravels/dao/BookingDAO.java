package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void createBooking(Booking booking) throws PersistenceException {
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO bookings (bus_id, travel_date, booked_seats) VALUES (?, ?, ?)"
             )
        ) {
            ps.setInt(1, booking.getBusId());
            ps.setDate(2, new java.sql.Date(booking.getTravelDate().getTime()));
            ps.setInt(3, booking.getBookedSeats());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookings() throws PersistenceException {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM bookings"
             )
        ) {
            while (rs.next()) {
                list.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return list;
    }

    public List<Booking> getBookingsByDate(Date travelDate) throws PersistenceException {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE travel_date = ?"
             )
        ) {
            ps.setDate(1, travelDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractBookingFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return list;
    }

    public List<Booking> getBookingsByBusId(int busId) throws PersistenceException {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE bus_id = ?"
             )
        ) {
            ps.setInt(1, busId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractBookingFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return list;
    }

    public List<Booking> getBookingsByDateAndBusId(Date travelDate, int busId) throws PersistenceException {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE travel_date = ? AND bus_id = ?"
             )
        ) {
            ps.setDate(1, travelDate);
            ps.setInt(2, busId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractBookingFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return list;
    }

    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("booking_id"));
        booking.setBusId(rs.getInt("bus_id"));
        booking.setTravelDate(rs.getDate("travel_date"));
        booking.setBookedSeats(rs.getInt("booked_seats"));
        return booking;
    }
}
