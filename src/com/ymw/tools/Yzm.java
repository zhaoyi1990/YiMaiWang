package com.ymw.tools;

import java.util.Random;

public class Yzm {
	private String code; //��֤��

	public String getCode() {
		return code;
	}
	
	public void createCode(int count){
		/* ***************************
		 * ��֤���ַ���ȥ�����׻������ַ���
		 * ����1,Сд��ĸl,��д��ĸI,
		 * ����2,Сд��ĸz,��д��ĸZ,
		 * ����9,Сд��ĸg,Сд��ĸq,
		 * ����0,Сд��ĸo,��д��ĸO,��д��ĸQ
		 * ***************************/
		char[] cs = "345678abcdefhijkmnprstuvwxyABCDEFGHJKLMNPRSTUVWXY".toCharArray();
		Random random = new Random(System.currentTimeMillis());
		for(int i=0;i<count;i++){
		}
	}
}
