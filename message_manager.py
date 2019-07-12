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
chatroom[2] = ["", ""]
chatroom[3] = ["", ""]
chatroom[4] = ["", ""]


def getMessage(chat_id, start=1):
    return chatroom[chat_id][start:]

def pushMessage(chat_id, message):
    chatroom[chat_id].append(message)







