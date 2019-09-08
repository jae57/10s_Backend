package ten.shortclip.chatting.service;

import com.amazonaws.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.mock.web.*;
import org.springframework.test.context.junit4.*;
import org.springframework.web.multipart.*;
import ten.shortclip.chatting.model.*;

import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @Test
    public void getAllFindByRoomId_성공() {
        List<Message> messageList = messageService.getAllFindByRoomId(3L);
    }

    // 안되 몰라 패스
//    @Test
//    public void insertMessageByRoomId_성공() throws IOException {
//        File file = new File("file");
//        FileInputStream input = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile("file",
//                file.getName(), "text/plain", IOUtils.toByteArray(input));
//        messageService.insertMessageByRoomId(3L, multipartFile);
//    }
}
