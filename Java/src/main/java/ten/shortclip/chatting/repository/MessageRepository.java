package ten.shortclip.chatting.repository;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;
import ten.shortclip.chatting.model.*;

import java.util.*;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByRoomId(Long roomId);
    Long countByRoomId(Long roomId);
}
