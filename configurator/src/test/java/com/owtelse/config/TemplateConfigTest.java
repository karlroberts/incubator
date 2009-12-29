package com.owtelse.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TemplateConfigTest {
    @Before public void setUp() {
        // Add your code here
    }

    @After public void tearDown() {
        // Add your code here
    }

    /**
     * Make sure the constructor with sum template filenames works
     */
    @Test public void testContructor1works() {
        List<String> templates = new ArrayList();
        templates.add("dummyTemplate1");
        List<String> substitutionFiles = new ArrayList<String>();
        Properties substitutions = new Properties();
        List< File > outputFiles = new ArrayList<File>();
        outputFiles.add(new File("/tmp/dummyOutputFile1"));

        TemplateConfig tc =new TemplateConfig(templates, substitutionFiles, substitutions, outputFiles);

    }

    @Test(expected = IllegalArgumentException.class) public void testContructor1fails1() {
       List<String> templates = new ArrayList();
       // No templates templates.add("dummyTemplate1");
        List<String> substitutionFiles = new ArrayList<String>();
        Properties substitutions = new Properties();
        List< File > outputFiles = new ArrayList<File>();
        outputFiles.add(new File("/tmp/dummyOutputFile1"));
        TemplateConfig tc =new TemplateConfig(templates, substitutionFiles, substitutions, outputFiles);
    }

    @Test(expected = IllegalArgumentException.class) public void testContructor1fails2() {
       List<String> templates = new ArrayList();
        templates.add("dummyTemplate1");
        List<String> substitutionFiles = new ArrayList<String>();
        Properties substitutions = new Properties();
        List< File > outputFiles = new ArrayList<File>();
        // no output files // outputFiles.add(new File("/tmp/dummyOutputFile1"));
        TemplateConfig tc =new TemplateConfig(templates, substitutionFiles, substitutions, outputFiles);
    }
}
