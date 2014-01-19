package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mzucka on 1/7/14.
 */
public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(
                HelloWorldFreeMarkerStyle.class, "/");
        try {
            Template helloTemplate = conf.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Freemarker");

            helloTemplate.process(helloMap,writer);

            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
