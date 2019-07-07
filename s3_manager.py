import boto3
from pytz import timezone

from datetime import datetime
from hashlib import md5


def upload_file(file_stream, chatroom_id, bucket, file_name):
    s3_client = boto3.client('s3')
    now = datetime.now(timezone('Asia/Seoul'))
    datetime_hash = md5(str(now).encode('utf-8')).hexdigest()
    file_path = '/'.join([chatroom_id, datetime_hash, file_name])
    s3_client.upload_fileobj(file_stream, bucket, file_path, ExtraArgs={'ACL':'public-read'})
    response = s3_client.generate_presigned_url('get_object', Params={'Bucket':bucket, 'Key':file_path})
    return response.split('?')[0]





