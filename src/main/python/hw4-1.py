import pymongo
import sys

connection = pymongo.Connection("mongodb://localhost", safe=True)

db = connection.products
foo = db.students

doc = foo.find_one({'student_id':1})
print "first :" , doc['student_id'], " is ", doc['scores']



