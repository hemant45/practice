package com.example.practice;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegularExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final String regex = "(ftp://172.16.0.254/files/2018/10/29/)\\w+(20181029)\\w(.)+";
		final String string = "ftp://172.16.0.254/files/2018/10/29/EKAplus_20181029112130.xlsx";
		
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(string);
		System.out.println(matcher.matches());
		

	}

}
