package ten.shortclip.chatting.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ten.shortclip.chatting.model.Chatroom;
import ten.shortclip.chatting.model.User;

public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper;

    public AuthRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    }

    public User getUserById(Long userId){
        String query = "SELECT * FROM user WHERE id = ?";
        try{

            return jdbcTemplate.queryForObject(query,rowMapper,userId);

        }catch(EmptyResultDataAccessException e){
            throw e;
        }
    }
}
