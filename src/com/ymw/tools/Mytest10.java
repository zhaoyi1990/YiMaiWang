package com.ymw.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
 
public class Mytest10 {
    public static Random random = new Random();
	
	public static int r(int min,int max){
        int num=0;
        num=random.nextInt(max-min)+min;
        return num;
    }
    
	public static void main(String[] args) throws IOException {
        //���ڴ��д���һ��ͼƬ
        int w=120;
        int h=50;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //��ͼƬ�ϻ�һ�����ε�����
        Graphics g = img.getGraphics();
        g.setColor(new Color(r(50,250),r(50,250),r(50,250)));
        g.fillRect(0, 0, w, h);
         
        String str = "aqzxswedcfrvgtbhyujklp23456789";
        for(int i=0;i<4;i++){
            g.setColor(new Color(r(50,180),r(50,180),r(50,180)));
            g.setFont(new Font("����",Font.PLAIN,40));
            char c = str.charAt(r(0,str.length()));
            g.drawString(String.valueOf(c), 10+i*30, r(h-30,h));
        }
         
        //�������
        for(int i=0;i<25;i++){
            g.setColor(new Color(r(50,180),r(50,180),r(50,180)));
            g.drawLine(r(0,w), r(0,h),r(0,w), r(0,h));
        }
        //���ڴ��д�����ͼ��������ļ���
        File file =new File("vcode.png");
        ImageIO.write(img, "png", file);
        System.out.println("ͼƬ������");
         
    }
}
