package com.sanqing.util;

public class StrUtil {
	//将字符串数组转化为数数组
	public static int[] contract(String[] strArr,int num){
		int len=strArr==null?1:strArr.length;
		int[] numArr = new int[len];
		for(int i=0;strArr!=null&&i<strArr.length;i++){
			numArr[i]=Integer.parseInt(strArr[i]);
		}
		numArr[numArr.length-1]=num;
		return numArr;
	}
}
