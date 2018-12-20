package com.example.antlr;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snt.inmemantlr.GenericParser;
import org.snt.inmemantlr.exceptions.CompilationException;
import org.snt.inmemantlr.exceptions.IllegalWorkflowException;
import org.snt.inmemantlr.exceptions.ParsingException;
import org.snt.inmemantlr.listener.DefaultTreeListener;
import org.snt.inmemantlr.stream.CasedStreamProvider;

public class TestClass {
	private static File [] ok = new File("../grammar/examples").listFiles(pathname -> pathname.isFile());

	private static String query= "SELECT * from abc where asd=10";

    private static File [] gfiles = new File [] {
            new File("D:/Own/paractice/practice/src/main/resources/grammar/MySQLLexer.g4"),
            new File("D:/Own/paractice/practice/src/main/resources/grammar/MySQLParser.g4")
    };
    
    public static void main(String [] args) {


        GenericParser gp = null;
        try {
            gp = new GenericParser(gfiles);
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }

        DefaultTreeListener dt = new DefaultTreeListener();

        gp.setListener(dt);

    
        try {
            gp.compile();
        } catch (CompilationException e) {
        	e.printStackTrace();
        }


        gp.setStreamProvider(new CasedStreamProvider(GenericParser
                .CaseSensitiveType.UPPER));

        try {
        	System.out.println(gp.parse(query, "root", GenericParser
			        .CaseSensitiveType.UPPER));
			gp.parse(query, "root", GenericParser
			        .CaseSensitiveType.UPPER);
		} catch (IllegalWorkflowException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParsingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
    }
} 
