import pymongo
import sys

connection = pymongo.Connection("mongodb://localhost", safe=True)

db = connection.products
foo = db.students


