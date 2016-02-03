package com.ymw.tools;

import java.util.Random;

public class Yzm {
	private String code; //验证码

	public String getCode() {
		return code;
	}
	
	public void createCode(int count){
		/* ***************************
		 * 验证码字符集去除容易混淆的字符：
		 * 数字1,小写字母l,大写字母I,
		 * 数字2,小写字母z,大写字母Z,
		 * 数字9,小写字母g,小写字母q,
		 * 数字0,小写字母o,大写字母O,大写字母Q
		 * ***************************/
		char[] cs = "345678abcdefhijkmnprstuvwxyABCDEFGHJKLMNPRSTUVWXY".toCharArray();
		Random random = new Random(System.currentTimeMillis());
		for(int i=0;i<count;i++){
		}
	}
}
