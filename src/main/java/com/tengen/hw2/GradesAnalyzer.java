package com.tengen.hw2;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by mzucka on 1/18/14.
 */
public class GradesAnalyzer {

    public static void main (String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("students");
        DBCollection collection = database.getCollection("grades");

        DBObject query = QueryBuilder.start("type").is("homework").get();
        DBCursor cursor = collection.find(query).sort(new BasicDBObject("student_id", 1).append("score",1));


        try {
            long last_id = -1;
            long grade = Long.MAX_VALUE;
            BasicDBObject cur_grade = null;
            while(cursor.hasNext()) {
                BasicDBObject cur = (BasicDBObject) cursor.next();   /// ????

                System.out.println("cur: " + cur.getInt("student_id") +
                        ", grade:" + cur.getInt("score") +
                        ", type = " + cur.getString("type"));

                long cur_id = cur.getInt("student_id");
                if (last_id == -1) {
                    last_id = cur_id;
                    long g = cur.getInt("score");
                    grade = g;
                    cur_grade = cur;
                } else {
                    if (last_id == cur_id) {
                        long g = cur.getInt("score");
                        if (g < grade) {
                            grade = g;
                            cur_grade = cur;
                        }
                    } else {
                        // remove the lowest from collection
                        //tODO:
                        collection.remove(cur_grade);
                        last_id = cur_id;
                        grade = cur.getInt("score");
                        cur_grade = cur;
                    }
                }
            }

            collection.remove(cur_grade);
        } finally {
            cursor.close();
        }

        long cnt = collection.count();
        System.out.println("grades:" + cnt);
    }
}
