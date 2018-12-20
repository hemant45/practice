package com.example.practiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryAnalyserNonTokenizer {
	
	
	public static void main(String [] args) {
		//String query3 = "SELECT select_c1_select,(c2 * 5) as from_c22,c3, (select * from (select * from select * from t5) where c4) as c4, c6,c7 from table1";
		
		String query3 = "SELECT (t1.c1+t1.c1 * (100))/2, t2.* as r FROM table1 t1, table2 t2"
				+ "where c1 in (select c4 from abc)";
		String queryyy = "SELECT t1.c1, (select asd.a from asd "
				+ "where (select (xyz*10), rty.* from xyz,rty) as asd) FROM table1 t1, table2 t2";
		
		
		System.out.println(queryyy.replaceAll("\\(.+?\\)", "").toLowerCase());
		System.out.println(queryyy.replaceAll("\\([^()]*\\)", "").toLowerCase());
		System.out.println(queryyy.replaceAll("\\s*\\([^\\)]*\\)\\s*", "").toLowerCase());
		
		String str = query3.replaceAll("\\(.*\\)", "").toLowerCase();
		
		Pattern pr = Pattern.compile("\\([^()]*\\)");
		Matcher match = pr.matcher(queryyy);
		
		while(match.find()) {
			queryyy = match.replaceAll("");
			match = pr.matcher(queryyy);
		}
		
		System.out.println(queryyy);
		
		str = str.substring(0, str.indexOf(" from "));
		
		
		System.out.println(str);

		
		String query1 = "SELECT  state_id * 6 AS X, t2.* FROM cities t2;";
		String query2 =  "select index_imsid, SUM(weighted_value) sumWeightedValue,"
				+ "  (select top 1 percentof from [v_PlanPerProvider1] where [PLAN_RANK]=1) percentof"
				+ "  (select top 1 [Plan_Name_OR_Payment_Type] from [v_PlanPerProvider1] where [PLAN_RANK]=1) Plan_Name_OR_Payment_Type"
				+ "from [v_PlanPerProvider1]"
				+ "where plan_rank between 1 and 10"
				+ "group by index_imsid"
				+ "order by 1";
		
		
		String query4 = "SELECT table1.col1, table2.* from table1, table2 ";
		String query5 = "SELECT table1.col1, table2.col3*10 from table1, table2 ";
		
		String query6= "select * from a";
		
		/*System.out.println("Valid query : "+ validate(query1.trim().replaceAll("()\\s{2,}"," ")));
		System.out.println("Valid query : "+ validate(query2.trim().replaceAll("()\\s{2,}"," ")));
		System.out.println("Valid query : "+ validate(query3.trim().replaceAll("()\\s{2,}"," ")));
		System.out.println("Valid query : "+ validate(query4.trim().replaceAll("()\\s{2,}"," ")));
		System.out.println("Valid query : "+ validate(query5.trim().replaceAll("()\\s{2,}"," ")));*/
		
		System.out.println(query1 + "\nValid : "+ parseAndValidate(query1.trim().replaceAll("\\s{2,}"," ")));
		System.out.println(query2 + "\nValid : "+ parseAndValidate(query2.trim().replaceAll("\\s{2,}"," ")));
		System.out.println(query3 + "\nValid : "+ parseAndValidate(query3.trim().replaceAll("\\s{2,}"," ")));
		System.out.println(query4 + "\nValid : "+ parseAndValidate(query4.trim().replaceAll("\\s{2,}"," ")));
		System.out.println(query5 + "\nValid : "+ parseAndValidate(query5.trim().replaceAll("\\s{2,}"," ")));
		System.out.println(query6 + "\nValid : "+ parseAndValidate(query6.trim().replaceAll("\\s{2,}"," ")));
		
	}
	
	static boolean parse(String query) {
		char[] chars = query.toCharArray();
		
		int count = 0;
		for(int i=0;i<chars.length;i++) {
			if(chars[i] == '(') {
				++count;
			} else if(chars[i] == ')') {
				--count;
			}
			if(count == 0) {
				if(chars[i] == '*') {
					if(checkFrom(chars, i)) {
						return true;
					}
					if(!val(chars, i)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static boolean checkFrom(char[] chars, int ind) {
		String fromStr = "";
		for(int i=ind+1;i<chars.length ;i++) {
			if(chars[i] != ' ') {
				if(fromStr.length() == 4) {
					if("FROM".equalsIgnoreCase(fromStr)) {
						return true;
					}
					break;
				}
				fromStr += chars[i];
			}
		}
		return false;
	}
	
	static boolean val(char[] chars, int ind) {
		if(chars[ind-1] == '.') {
			return false;
		}
		return true;
	}
	
	
	
	private static boolean parseAndValidate(String query) {
		Integer counter =0;
		Integer subquerymarker=0;
		Integer beginIndex=0;
		Integer endIndex =0;
		
		List<String> stringsToValidate = new ArrayList<String>();
		
		for(int i=0; i < query.length()-7; i++) {
			
			if(query.charAt(i)=='(') {
				if(subquerymarker==0) {
					endIndex=i;
					stringsToValidate.add(query.substring(beginIndex, endIndex));
				}
				subquerymarker++;
			} else if(query.charAt(i)==')') {
				subquerymarker--;
				if(subquerymarker==0) {
					beginIndex=i+1;
				}
				
			}
			
			if(query.substring(i,i+7).equalsIgnoreCase("select ")){
				counter++;
			} else if(query.substring(i,i+6).equalsIgnoreCase(" from ")) {
				counter--;
				if(counter==0) {
					endIndex=i;
					stringsToValidate.add(query.substring(beginIndex, endIndex));
					break;
				}
			}
		}
		
		for(String str : stringsToValidate) {
			if(str.toLowerCase().contains("select *") || str.contains(".*")) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
public static boolean validate (String query) {
	
	Integer selectIndex = Integer.MAX_VALUE;
	Integer fromIndex = 0;
	Integer counter =0;
	
	Integer selectMarker=0; //to make sure select index is set only once
	String stringToValidate = null;
	
	for(int i=0; i < query.length()-6; i++) {
		if(query.substring(i,i+6).equalsIgnoreCase("select")){
			counter++;
			if(counter==2 && selectMarker==0) {
				selectMarker=1;
				selectIndex=i;
			}
		} else if(query.substring(i,i+4).equalsIgnoreCase("from")) {
			counter--;
			if(counter==0) {
				fromIndex=i;
				break;
			}
		}
	}
	
	stringToValidate=query.substring(0, selectIndex < fromIndex ? selectIndex : fromIndex);
	
	if(stringToValidate.trim().startsWith("Select *")
			||stringToValidate.trim().startsWith("SELECT *")
			||stringToValidate.contains(".*")) {
		return false;
	}
		return true;
	}
}
