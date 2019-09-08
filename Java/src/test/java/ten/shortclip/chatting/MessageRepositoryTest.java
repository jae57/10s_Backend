package ten.shortclip.chatting;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import ten.shortclip.chatting.model.*;
import ten.shortclip.chatting.repository.*;

import java.util.*;

//성공만 테스트 한다.
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest {
    @Autowired
    MessageRepository messageRepository;

    @Test
    public void 메세지_넣기_성공() {
        Message message = new Message("hi", 2L, 2L, 3L);
        messageRepository.insert(message);
    }

    @Test
    public void 메세지_가져오기_성공() {
        Message message = new Message("hi", 2L, 2L, 3L);
        messageRepository.insert(message);
        List<Message> messageList= messageRepository.findAll();
        Assert.assertTrue(messageList.size() > 0);
    }
}
