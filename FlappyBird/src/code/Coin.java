package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin 
{
	BufferedImage coinSprite;
	double animationTime;
	int xPos;
	int yPos;
	
	public Coin(GameLoop gameLoop)
	{
		setSprite();
		xPos = gameLoop.getWidth();
		yPos = (int) ((gameLoop.getHeight() / 4) + (Math.random() * (gameLoop.getHeight() / 2)));
	}
	
	public BufferedImage getSprite()
	{
		return coinSprite;
	}
	
	public int xPos()
	{
		return xPos;
	}

	public void updateXPos(int speed)
	{
		xPos -= speed;
	}
	
	public int yPos()
	{
		return yPos;
	}
	
	private void setSprite()
	{
		try 
		{
			coinSprite = ImageIO.read(new File("src/resources/coin.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

