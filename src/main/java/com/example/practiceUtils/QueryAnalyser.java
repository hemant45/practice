package com.example.practiceUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringTokenizer;

public class QueryAnalyser {

	private Integer selectIndex = 0;
	private Integer fromindex = 0;
	private static List stack = new ArrayList<String>();
	
	public static void main(String [] args) {
		String query1 = "SELECT field1 from (SELECT count(field2) from table2 UNION SELECT count(fileld3) from table3)";
		String query2 =  "select index_imsid, SUM(weighted_value) sumWeightedValue,"
				+ "  (select top 1 percentof from [v_PlanPerProvider1] where [PLAN_RANK]=1) percentof"
				+ "  (select top 1 [Plan_Name_OR_Payment_Type] from [v_PlanPerProvider1] where [PLAN_RANK]=1) Plan_Name_OR_Payment_Type"
				+ "from [v_PlanPerProvider1]"
				+ "where plan_rank between 1 and 10"
				+ "group by index_imsid"
				+ "order by 1";
		validate(query2.trim());
		
	}
	
	public static boolean validate (String query) {
		
		query = query.replace("(", " ");
		query = query.replace(")", " ");
		StringTokenizer token =new StringTokenizer(query," ");
		String tempStr =null;
		
		while (token.hasNext()) {
			tempStr = token.nextToken();
			System.out.println(tempStr);
			
			if(tempStr.equalsIgnoreCase("select")) {
				push("select");
				System.out.println("token index : " + token.previousIndex());
				//System.out.println(token.nextIndex());
			}
			if(tempStr.equalsIgnoreCase("from")) {
				pop();
			}
			if(stack.size()==0) {
				break; 
			}
			
		}
		return false;
	}
	
	public static void pop() {
		stack.remove("select");
	}
	
	public static void push(String str) {
		stack.add(str);
	}
	
}
