package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by mzucka on 1/7/14.
 */
public class HelloWorldMongoDBStyle {
       public static void main (String[] args) throws UnknownHostException {
           MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

           DB database = client.getDB("course");
           DBCollection collection = database.getCollection("hello");
           DBObject document = collection.findOne();
           System.out.println(document);
       }
}
