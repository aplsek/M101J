package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by mzucka on 1/7/14.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            public Object handle(final Request request, final Response response) {
                return "Hello World From Spark";
            }
        });
    }
}
