package com.autumn.system.tools;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * 名   称：SecCodeUtil.java
 * 描   述：生成验证码
 * 作   者：李波
 * 时   间：Dec 13, 2016 10:57:56 AM
 * --------------------------------------------------
 * 修改历史
 * 序号    日期    修改人     修改原因 
 * 1                                                        
 * **************************************************
 */
public class SecCodeUtil {

	public static String drawImg(ByteArrayOutputStream output) {
		String code = "";
		for (int i = 0; i < 4; i++)
		{
			code += randomChar();
		}
		/*int width = 70;
		int height = 25;
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		Graphics2D g = bi.createGraphics();
		g.setFont(font);
		Color color = new Color(66, 2, 82);
		g.setColor(color);
		g.setBackground(new Color(226, 226, 240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(code, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = bounds.getY();
		double baseY = y - ascent;
		g.drawString(code, (int) x, (int) baseY);
		g.dispose();
		try
		{
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e)
		{
			e.printStackTrace();
		}*/
		return code;

	}
	
	public static char randomChar()
	{
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}


}
