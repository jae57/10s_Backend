import s3_manager

with open("./test_files/test.mp3", "rb") as f:
    print(s3_manager.upload_file(f, "chat-room", "10s-voice", "test.mp3"))