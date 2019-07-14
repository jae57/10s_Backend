#-*- coding:utf-8 -*-
from datetime import datetime
from pymongo import MongoClient


class MessageManager:
	def __init__(self, mode=1):
		self.conn = MongoClient('localhost', 27017)
	
	def getMessage(self, chat_id, start=0):
		db = self.conn[str(chat_id)]
		return list(db.messages.find({},{'_id':0}).skip(start).sort([('order',1)]))

	def pushMessage(self, chat_id, message):
		db = self.conn[str(chat_id)]
		messages = db.messages
		messages.insert_one(message)

	def countMessage(self, chat_id):
		db = self.conn[str(chat_id)]
		return db.messages.count_documents({})

	def getNextOrder(self, chat_id):
		return self.countMessage(chat_id) + 1


if __name__ == "__main__":
	from pprint import pprint
	manager = MessageManager()
	## 몽고 db 키는 스트링 이어야함
	pprint(manager.countMessage(1))
	pprint(manager.getNextOrder(1))
	







