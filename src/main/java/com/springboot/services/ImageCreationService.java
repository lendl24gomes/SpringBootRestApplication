package com.springboot.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ImageCreationService {
	
	@Autowired
	private Environment envt;
	
	protected static final Map<RenderingHints.Key, Object> RenderingProperties = new HashMap <RenderingHints.Key, Object> ();

   static{
	    RenderingProperties.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    RenderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    RenderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	}

	public String textToImage(String txt) throws IOException{
		int max=0;
		int index=0;
		String [] lines = txt.split("\n");
		for (int i=0; i<lines.length; i++)
		{
			if (lines[i].length()> max)
			{
				max=lines[i].length();
				index=i;
			}
		}
		Font f = new Font("Times New Roman", Font.PLAIN, 35);
	    FontRenderContext frc = new FontRenderContext(null, true, true);
	    LineMetrics lm = f.getLineMetrics(txt, frc);
	    Rectangle2D r2d = f.getStringBounds(lines[index], frc);
	    BufferedImage img = new BufferedImage((int)Math.ceil(r2d.getWidth()), ((int)Math.ceil(r2d.getHeight())*lines.length), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = img.createGraphics();
	    g2d.setRenderingHints(RenderingProperties);
	    g2d.setBackground(Color.WHITE);
	    g2d.setColor(Color.BLACK);
	    g2d.clearRect(0, 0, img.getWidth(), img.getHeight());
	    g2d.setFont(f);
	    float y= lm.getAscent();
	    	for (String line : lines)
	    	{	
	    		g2d.drawString(line,0,y);
	    		y+=lm.getAscent();
	    	}
	    g2d.dispose();
	    File outputfile = new File(envt.getProperty("fileLocation"));
	    new File(outputfile.getParent()).mkdirs();
        ImageIO.write(img, "png", outputfile);
        
        return envt.getProperty("imageLink");
	}
	
}




