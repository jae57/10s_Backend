package ten.shortclip.chatting.repository;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;
import ten.shortclip.chatting.model.*;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
