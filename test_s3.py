import s3_manager

with open("./test_files/test.mp3", "rb") as f:
    s3_manager.upload_file(f, "10s-voice", "test.mp3")