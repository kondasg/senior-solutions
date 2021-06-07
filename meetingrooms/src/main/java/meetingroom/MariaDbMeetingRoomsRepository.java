package meetingroom;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository {

    private final JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3307/csxxdg_t360?useUnicode=true");
            dataSource.setUser("csxxdg_t360");
            dataSource.setPassword("sWRAiZGCTAGY");

            jdbcTemplate = new JdbcTemplate(dataSource);

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.clean();
            flyway.migrate();
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can't create datasource", sqle);
        }
    }

    @Override
    public void save(String name, int width, int length) {
        String sql = "INSERT INTO `meetingrooms` (`name`, `width`, `length`) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, width, length);
    }

    @Override
    public List<MeetingRoom> list() {
        String sql = "SELECT `name`, `width`, `length` FROM `meetingrooms` ORDER BY `name`";
        return jdbcTemplate.query(sql, new MeetingRoomMapper());
    }

    @Override
    public List<MeetingRoom> listReverse() {
        String sql = "SELECT `name`, `width`, `length` FROM `meetingrooms` ORDER BY `name` DESC";
        return jdbcTemplate.query(sql, new MeetingRoomMapper());
    }

    @Override
    public List<MeetingRoom> listEverySecond() {
        String sql = "SELECT `name`, `width`, `length` FROM " +
                "(SELECT `name`, `width`, `length`, ROW_NUMBER() OVER (ORDER BY `id`) AS `rownum` FROM `meetingrooms`) " +
                "AS `row`" +
                "WHERE `row`.`rownum` % 2 = 0 ORDER BY `name`";
        return jdbcTemplate.query(sql, new MeetingRoomMapper());
    }

    @Override
    public List<MeetingRoom> getAreas() {
        String sql = "SELECT `name`, `width`, `length`, (`width` * `length`) AS area FROM `meetingrooms` ORDER BY area DESC";
        return jdbcTemplate.query(sql, new MeetingRoomMapper());
    }

    @Override
    public MeetingRoom findByName(String name) {
        MeetingRoom meetingRoom;
        String sql = "SELECT `name`, `width`, `length` FROM `meetingrooms` WHERE `name` LIKE ? ORDER BY `name`";
        try {
            meetingRoom = jdbcTemplate.queryForObject(sql, new Object[]{name}, new MeetingRoomMapper());
        } catch (EmptyResultDataAccessException e) {
            meetingRoom = null;
        }
        return meetingRoom;
    }

    @Override
    public List<MeetingRoom> findByPattern(String pattern) {
        String p = "%" + pattern + "%";
        String sql = "SELECT `name`, `width`, `length` FROM `meetingrooms` WHERE `name` LIKE ? ORDER BY `name`";
        return jdbcTemplate.query(sql, new Object[]{p.toLowerCase()}, new MeetingRoomMapper());
    }

    @Override
    public List<MeetingRoom> findByArea(int area) {
        String sql = "SELECT `name`, `width`, `length` FROM `meetingrooms` WHERE (`width` * `length`) > ? ORDER BY `name`";
        return jdbcTemplate.query(sql, new Object[]{area}, new MeetingRoomMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE `meetingrooms`");
    }
}
