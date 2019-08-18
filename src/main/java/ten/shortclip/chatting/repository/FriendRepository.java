package ten.shortclip.chatting.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ten.shortclip.chatting.domain.Chatroom;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.dto.UserProfileDto;

import java.util.List;

@Repository
public class FriendRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper;

    public FriendRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    }

    public List<User> getFriends(Long userId){
        String query = "select * from user u inner join friend f on u.id = f.friend_id where user_id=?";
        try{
            return jdbcTemplate.query(query,rowMapper,userId);

        }catch(EmptyResultDataAccessException e){
            throw e;
        }

    }

    public int addFriend(Long userId, Long friendId){
        String query = "INSERT INTO friend(user_id, friend_id) VALUES(?,?)";
        return jdbcTemplate.update(query, userId, friendId);
    }
}
