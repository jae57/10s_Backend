package ten.shortclip.chatting.service;

import com.amazonaws.*;
import com.amazonaws.regions.*;
import org.joda.time.*;
import org.springframework.stereotype.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import ten.shortclip.chatting.exception.*;
import ten.shortclip.chatting.util.*;

import java.io.File;
import java.net.*;
import java.security.*;
import java.util.*;

//이걸 빈으로 넣어야 하는지는 좀 의문
@Service
public class AWSS3FileClient {
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();

    public URL upload(String chatroomId, String bucketName, File file) {
        try {
            String key = getKey(chatroomId, file);
            s3.putObject(bucketName, key, file);
            return s3.generatePresignedUrl(bucketName, key, new Date(), HttpMethod.GET);
        // AWS 예외는 SDK 가져다 쓰는거니까 따로 표시
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            throw new AWSS3Exception();
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new AWSS3Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Runtime 에서 505면 안해도 될 듯
        throw new UploadFailException();
    }

    private String getKey(String chatroomId, File file) throws NoSuchAlgorithmException {
        String dateHash = HashGenerator.generate("MD5", DateTime.now()
                                                                        .toString()
                                                                        .getBytes());
        return String.join("/", new String[]{chatroomId, dateHash, file.getName()});
    }
}
