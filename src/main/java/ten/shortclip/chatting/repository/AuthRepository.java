package ten.shortclip.chatting.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.dto.RequestUserDto;

@Repository
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper;

    public AuthRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    }

    public User getUserById(Long id){
        String query = "SELECT * FROM user WHERE id = ?";
        try{

            return jdbcTemplate.queryForObject(query,rowMapper,id);

        }catch(EmptyResultDataAccessException e){
            System.out.println("Exception!!"+id);
            throw e;
        }
    }

    public User getUserByEmail(String email){
        String query = "SELECT * FROM user WHERE email = ?";
        try{

            return jdbcTemplate.queryForObject(query,rowMapper,email);

        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public User save(RequestUserDto requestUserDto){
        String query = "INSERT INTO user( email, password, nickname, profile_image ) values (?,?, ?, ?)";
        String email = requestUserDto.getEmail();
        String password = requestUserDto.getPassword();
        String nickname = requestUserDto.getNickname();
        String profileImage = requestUserDto.getProfileImage();
        jdbcTemplate.update(query,email,password, nickname, profileImage);

        return getUserByEmail(email);
    }
}
