from datetime import datetime

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
	def __init__(self, mode=0):
		if mode == 0:
			self.chatroom = make_temp()
		else:	
			self.chatroom = dict()
	
	def getMessage(self, chat_id, start=1):
		return self.chatroom[chat_id][start-1:]

	def pushMessage(self, chat_id, message):
	    self.chatroom[chat_id].append(message)


if __name__ == "__main__":
	manager = MessageManager(1)
	print(manager.getMessage(1))
	manager2 = MessageManager()
	print(manager.getMessage(1))







