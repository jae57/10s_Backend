package ten.shortclip.chatting.model;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "message")
public class Message {
    @Id
    private String id;

    private String content;
    private Long index;
    private Long sender;
    private Long receiver;

    public Message(String content, Long index, Long sender, Long receiver) {
        this.content = content;
        this.index = index;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getIndex() {
        return index;
    }

    public Long getSender() {
        return sender;
    }

    public Long getReceiver() {
        return receiver;
    }
}
