package meetingroom;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingRoomMapper implements RowMapper<MeetingRoom> {

    @Override
    public MeetingRoom mapRow(ResultSet rs, int i) throws SQLException {
        return new MeetingRoom(
                rs.getString("name"),
                rs.getInt("width"),
                rs.getInt("length"));
    }
}
