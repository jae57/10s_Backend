import boto3
from pytz import timezone

from datetime import datetime
from hashlib import md5


def upload_file(file_stream, bucket, file_name):
    s3 = boto3.client('s3')
    now = datetime.now(timezone('Asia/Seoul'))
    datetime_hash = md5(str(now).encode('utf-8')).hexdigest()
    file_path = '/'.join([datetime_hash, file_name])
    s3.upload_fileobj(file_stream, bucket, file_path)
    return file_path



