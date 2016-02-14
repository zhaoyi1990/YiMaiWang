package com.ymw.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VCode {
	private String code; 		//��֤��
	private BufferedImage img;	//��֤��ͼƬ

	public String getCode() {
		return code.toLowerCase();
	}
	public BufferedImage getImg() {
		return img;
	}
	
	public VCode() {
		createCode(4);
		createImg(120, 50);
	}
	
	public VCode(int count){
		createCode(count);
		createImg(30*count, 50);
	}
	
	//������֤��
	private void createCode(int count){
		/* ***************************
		 * ��֤���ַ���ȥ�����׻������ַ���
		 * ����1,Сд��ĸl,Сд��ĸi,��д��ĸI,
		 * ����2,Сд��ĸz,��д��ĸZ,
		 * ����9,Сд��ĸq,
		 * ����0,Сд��ĸo,��д��ĸO,��д��ĸQ
		 * ***************************/
		char[] cs = "345678abcdefghjkmnprstuvwxyABCDEFGHJKLMNPRSTUVWXY".toCharArray();
		char[] codeArray = new char[count];
		for(int i=0;i<count;i++){
			codeArray[i] = cs[r(0, cs.length)];
		}
		code = new String(codeArray);
	}
	
	private void createImg(int w,int h){
		img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //��ͼƬ�ϻ�һ�����ε�����
        Graphics g = img.getGraphics();
        g.setColor(new Color(r(150,255),r(150,255),r(150,255)));
        g.fillRect(0, 0, w, h);
        
        for(int i = 0;i<code.length();i++){
        	//�ֵ���ɫ�ͱ�����ɫ������ͬ
        	g.setColor(new Color(r(0,149), r(0,149), r(0,149)));
            g.setFont(new Font("����",Font.PLAIN,40));
            g.drawString(String.valueOf(code.charAt(i)), 10+i*w/code.length(),r(30,h-10));
        }
        
        //�������
        for(int i =0 ;i<25;i++){
        	g.setColor(new Color(r(0,255), r(0,255), r(0,255)));
        	g.drawLine(r(0,w), r(0,h),r(0,w), r(0,h));
        }
        
        
        //���ڴ��д�����ͼ��������ļ���
//        File file =new File("vcode.jpg");
//        try {
//			ImageIO.write(img, "jpg", file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        System.out.println("ͼƬ������");
	}
	
	//�����
	private int r(int min,int max){
		if(min>max){
			int temp = min;
			min = max;
			max = temp;
		}
		Random random = new Random();
		return random.nextInt(max-min)+min;
	}
}
