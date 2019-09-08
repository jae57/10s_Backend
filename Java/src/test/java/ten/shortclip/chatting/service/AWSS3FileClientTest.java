package ten.shortclip.chatting.service;

import org.junit.*;

import java.io.*;

//일단 클래스 동작 성공만 테스트
public class AWSS3FileClientTest {
    @Test
    public void 파일_업로드_성공() throws IOException {
        String roomId = "1";
        String bucket = "10s-voice";
        AWSS3FileClient fileClient = new AWSS3FileClient();
        File file = File.createTempFile("file", "file");
        fileClient.upload(roomId, bucket, file);
    }
}
