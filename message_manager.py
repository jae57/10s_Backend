from datetime import datetime
from pymongo import MongoClient


def make_temp():
	chatroom = dict()
	chatroom[1] = [{
		"order":1, 
        "sender":1,
		"receiver":1,
		"content":"https://10s-voice.s3.amazonaws.com/chat-room/175d40241f5d1d6612f970fcdfe47c36/test.mp3",
		"date": datetime.now()
	}, {
		"order":2,
		"sender":1,
		"receiver":3,
		"content":"https://10s-voice.s3.amazonaws.com/chat-room/175d40241f5d1d6612f970fcdfe47c36/test.mp3",
		"date":datetime.now()
	}, {
		"order":3,
		"sender":3,
		"receiver":1,
		"content":"https://10s-voice.s3.amazonaws.com/chat-room/175d40241f5d1d6612f970fcdfe47c36/test.mp3",
		"date":datetime.now()
	}, {
		"order":4,
		"sender":1,
		"receiver":1,
		"content":"https://10s-voice.s3.amazonaws.com/chat-room/175d40241f5d1d6612f970fcdfe47c36/test.mp3",
		"date":datetime.now()
	}, {
		"order":5,
		"sender":3,
		"receiver":1,
		"content":"https://10s-voice.s3.amazonaws.com/chat-room/175d40241f5d1d6612f970fcdfe47c36/test.mp3",
		"date":datetime.now()
	}]
	return chatroom


class MessageManager:
	def __init__(self, mode=1):
		self.conn = MongoClient('localhost', 27017)
	
	def getMessage(self, chat_id, start=1):
		db = self.conn[chat_id]
		return list(db.messages.find())

	def pushMessage(self, chat_id, message):
		db = self.conn[chat_id]
		messages = db.messages
		messages.insert_one(message)

	def countMessage(self, chat_id):
		db = self.conn[chat_id]
		return db.messages.count_documents({})

if __name__ == "__main__":
	from pprint import pprint
	manager = MessageManager()
	## 몽고 db 키는 스트링 이어야함
	print(manager.countMessage(str(5)))

	pprint(manager.getMessage(str(5)))
	







