package com.thinkgem.jeesite.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2014-1-34"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
