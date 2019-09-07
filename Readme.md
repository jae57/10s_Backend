## 배포하기
로컬에서 gradlew 빌드 후 배포

### Mac, Linux
```
gradlew build
scp -i ~/.ssh/10s.pem ./build/libs/chatting-0.0.1-SNAPSHOT.jar ubuntu@52.78.119.14:/home/ubuntu/deploy/$(date +"%Y%m%d%H%M%S").jar
```

## 실행하기
배포 서버에서 백그라운드 실행
```
java -jar ~/deploy/최신버전.jar &
```