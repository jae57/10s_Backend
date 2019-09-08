package ten.shortclip.chatting.service;

import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;
import ten.shortclip.chatting.model.*;
import ten.shortclip.chatting.repository.*;

import java.io.*;
import java.net.*;
import java.util.*;

@Service
public class MessageService {
    private JwtService jwtService;
    private MessageRepository messageRepository;
    private AWSS3FileClient awss3FileClient;

    public MessageService(JwtService jwtService, MessageRepository messageRepository, AWSS3FileClient awss3FileClient) {
        this.jwtService = jwtService;
        this.messageRepository = messageRepository;
        this.awss3FileClient = awss3FileClient;
    }

    public List<Message> getAllFindByRoomId(Long roomId) {
        return messageRepository.findAllByRoomId(roomId);
    }

    public void insertMessageByRoomId(Long roomId, MultipartFile file) throws IOException {
        URL path = awss3FileClient.upload(String.valueOf(roomId), "10s-voice", file.getResource().getFile());
        Long index = messageRepository.countByRoomId(roomId);
        Message message = new Message(path.getPath(), index, jwtService.getUserId(), roomId);
        messageRepository.insert(message);
    }
}
