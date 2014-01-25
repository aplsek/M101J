package com.tengen.hw3;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by mzucka on 1/18/14.
 */
public class ScoreAnalyzer {

    public static void main (String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students3");

        //DBObject query = QueryBuilder.start("type").is("homework").get();
        //DBCursor cursor = collection.find(query).sort(new BasicDBObject("student_id", 1).append("score",1));

        DBCursor cursor =  collection.find();

        try {
            while(cursor.hasNext()) {
                BasicDBObject cur = (BasicDBObject) cursor.next();
                BasicDBList scores = (BasicDBList) cur.get("scores");
                double min = Double.MAX_VALUE;
                BasicDBObject toDelete = null;
                for (Object sc : scores) {
                    BasicDBObject s = (BasicDBObject) sc;
                    String type = s.getString("type");
                    if (!type.equals("homework"))
                        continue;
                    double cc = s.getDouble("score");
                    if (cc < min) {
                        min = cc;
                        toDelete = s;
                    }
                }
                BasicDBObject update = new BasicDBObject("scores",toDelete);
                collection.update(cur,new BasicDBObject("$pull",update));
            }

        } finally {
            cursor.close();
        }

        BasicDBObject one = (BasicDBObject) collection.findOne();
        System.out.println("name :" + one.get("name") );
        BasicDBList scores = (BasicDBList) one.get("scores");
        for (Object sc : scores) {
            BasicDBObject s = (BasicDBObject) sc;
            System.out.println("\t score:" + s.get("score"));
        }

        long cnt = collection.count();
        System.out.println("grades:" + cnt);
    }
}
