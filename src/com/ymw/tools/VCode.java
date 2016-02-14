package com.ymw.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VCode {
	private String code; 		//验证码
	private BufferedImage img;	//验证码图片

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
	
	//生成验证码
	private void createCode(int count){
		/* ***************************
		 * 验证码字符集去除容易混淆的字符：
		 * 数字1,小写字母l,小写字母i,大写字母I,
		 * 数字2,小写字母z,大写字母Z,
		 * 数字9,小写字母q,
		 * 数字0,小写字母o,大写字母O,大写字母Q
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
        //在图片上画一个矩形当背景
        Graphics g = img.getGraphics();
        g.setColor(new Color(r(150,255),r(150,255),r(150,255)));
        g.fillRect(0, 0, w, h);
        
        for(int i = 0;i<code.length();i++){
        	//字的颜色和背景颜色不能相同
        	g.setColor(new Color(r(0,149), r(0,149), r(0,149)));
            g.setFont(new Font("黑体",Font.PLAIN,40));
            g.drawString(String.valueOf(code.charAt(i)), 10+i*w/code.length(),r(30,h-10));
        }
        
        //画随机线
        for(int i =0 ;i<25;i++){
        	g.setColor(new Color(r(0,255), r(0,255), r(0,255)));
        	g.drawLine(r(0,w), r(0,h),r(0,w), r(0,h));
        }
        
        
        //把内存中创建的图像输出到文件中
//        File file =new File("vcode.jpg");
//        try {
//			ImageIO.write(img, "jpg", file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        System.out.println("图片输出完成");
	}
	
	//随机数
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
