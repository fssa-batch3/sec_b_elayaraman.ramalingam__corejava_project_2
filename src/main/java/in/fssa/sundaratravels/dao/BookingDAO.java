package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public int createBooking(Booking booking) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int bookingId = -1;

        try {
            String query = "INSERT INTO `bookings` (`bus_id`, `travel_date`, `booked_seats`) VALUES (?, ?, ?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getBusId());
            ps.setDate(2, booking.getTravelDate());
            ps.setInt(3, booking.getBookedSeats());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return bookingId;
    }

    public Booking getBookingById(int bookingId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Booking booking = null;

        try {
            String query = "SELECT * FROM `bookings` WHERE `booking_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);

            rs = ps.executeQuery();

            if (rs.next()) {
                booking = extractBookingFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return booking;
    }

    public List<Booking> getAllBookings() throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM `bookings` WHERE `is_active` = TRUE";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = extractBookingFromResultSet(rs);
                list.add(booking);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void updateBooking(int bookingId, int newBookedSeats) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE `bookings` SET `booked_seats` = ? WHERE `booking_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newBookedSeats);
            ps.setInt(2, bookingId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public Booking getBookingByBusAndDate(int busId, java.sql.Date travelDate) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Booking booking = null;

        try {
            String query = "SELECT * FROM `bookings` WHERE `bus_id` = ? AND `travel_date` = ? AND `is_active` = TRUE";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, busId);
            ps.setDate(2, travelDate);

            rs = ps.executeQuery();

            if (rs.next()) {
                booking = extractBookingFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return booking;
    }


    public void switchBookingStatus(int bookingId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE `bookings` SET `is_active` = NOT `is_active` WHERE `booking_id` = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("booking_id"));
        booking.setBusId(rs.getInt("bus_id"));
        booking.setTravelDate(rs.getDate("travel_date"));
        booking.setBookedSeats(rs.getInt("booked_seats"));
        booking.setActive(rs.getBoolean("is_active"));
        return booking;
    }
}
