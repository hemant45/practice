package com.example.practiceUtils;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.example.antlr.MySqlLexer;
import com.example.antlr.MySqlParser;

public class QueryAnalyserAntlr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "SELECT * from abc where asd=10";
		CharStream cs = CharStreams.fromString(input.toUpperCase());
		MySqlLexer lexer = new MySqlLexer(cs);
		CommonTokenStream cts = new CommonTokenStream(lexer);
		MySqlParser parser = new MySqlParser(cts);
		System.out.println(parser.root().getChildCount());
		System.out.println(parser.root().children);
	}

}
