﻿package hello.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
	public static String getDateCN(){
		Date dt = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
	public static String getDate(){
		Date dt = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
		return sdf.format(dt);
	}
}
