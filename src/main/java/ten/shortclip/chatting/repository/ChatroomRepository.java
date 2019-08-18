package ten.shortclip.chatting.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ten.shortclip.chatting.domain.Chatroom;

@Repository
public class ChatroomRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Chatroom> rowMapper;

    public ChatroomRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = BeanPropertyRowMapper.newInstance(Chatroom.class);
    }

    public Chatroom getChatroomById(Long roomId){
        String query = "SELECT * FROM chatroom WHERE id = ?";
        try{

            return jdbcTemplate.queryForObject(query,rowMapper,roomId);

        }catch(EmptyResultDataAccessException e){
            throw e;
        }

    }
}
