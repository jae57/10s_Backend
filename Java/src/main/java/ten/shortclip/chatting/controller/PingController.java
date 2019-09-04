package ten.shortclip.chatting.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PingController {

    @GetMapping("")
    public String healthCheck(){
        return "ok";
    }
}
