package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void createBooking(Booking booking) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        try {
            String query = "INSERT INTO booking (bus_id, schedule_id, travel_date, booked_seats) VALUES (?, ?, ?, ?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getBusId());
            ps.setInt(2, booking.getScheduleId());
            ps.setDate(3, java.sql.Date.valueOf(String.valueOf(booking.getTravelDate())));
            ps.setInt(4, booking.getBookedSeats());

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                booking.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating booking failed, no ID obtained.");
            }
        } finally {
            ConnectionUtil.close(conn, ps, generatedKeys);
        }
    }

    public Booking getBookingById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Booking booking = null;

        try {
            String query = "SELECT * FROM booking WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                booking = extractBookingFromResultSet(rs);
            }
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }

        return booking;
    }

    public List<Booking> getAllBookings() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = extractBookingFromResultSet(rs);
                bookings.add(booking);
            }
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }

        return bookings;
    }

    public List<Booking> getBookingsByDate(String travelDate) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking WHERE travel_date = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(travelDate));
            rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = extractBookingFromResultSet(rs);
                bookings.add(booking);
            }
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }

        return bookings;
    }

    public List<Booking> getBookingsByBusId(int busId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking WHERE bus_id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, busId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = extractBookingFromResultSet(rs);
                bookings.add(booking);
            }
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }

        return bookings;
    }

    public void updateBooking(Booking booking) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE booking SET bus_id = ?, schedule_id = ?, travel_date = ?, booked_seats = ? WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, booking.getBusId());
            ps.setInt(2, booking.getScheduleId());
            ps.setDate(3, java.sql.Date.valueOf(String.valueOf(booking.getTravelDate())));
            ps.setInt(4, booking.getBookedSeats());
            ps.setInt(5, booking.getId());

            ps.executeUpdate();
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public void deleteBooking(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "DELETE FROM booking WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

    public Booking getBookingByBusNo(String busNo) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Booking booking = null;

        try {
            String query = "SELECT b.* FROM booking b JOIN bus_schedule bs ON b.schedule_id = bs.id JOIN bus bu ON bs.bus_id = bu.id WHERE bu.bus_no = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, busNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                booking = extractBookingFromResultSet(rs);
            }
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }

        return booking;
    }


    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setBusId(rs.getInt("bus_id"));
        booking.setScheduleId(rs.getInt("schedule_id"));
        booking.setTravelDate(rs.getDate("travel_date"));
        booking.setBookedSeats(rs.getInt("booked_seats"));
        return booking;
    }
}
