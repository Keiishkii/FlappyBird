package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pipe
{

	int upperPipeHeight;
	int pipeDisplacement;
	
	boolean canGiveScore = true;
	
	int xPosition;
	
	double topYPosition;
	double bottomYPosition;
	
	int 	pipeSpeed;
	double 	closeSpeed;
	
	int pipeDisplacementBase = 280;
	int pipeDisplacementRandom = 200;
	
	BufferedImage topSprite;
	BufferedImage bottomSprite;
	
	public Pipe(int width, int height, int speed)
	{
		setSprite();
		setPipeSpeed(speed);
		bottomYPosition = (int) ((height) - ((Math.random()*height) / 2));
		
		pipeDisplacement = pipeDisplacementBase + (int) (Math.random()* pipeDisplacementRandom);
		closeSpeed = 0.0 + (Math.random() * 0.3);
		
		topYPosition = bottomYPosition - pipeDisplacement - topSprite.getHeight();
		xPosition = width;
	}
	
	

	public int getXPosition()
	{
		return xPosition;
	}
	
	public void setPipeSpeed(int speed)
	{
		pipeSpeed = speed;
	}
	
	public int getTopYPosition()
	{
		int y = (int) topYPosition;
		if ((topYPosition + topSprite.getHeight()) < bottomYPosition)
			topYPosition += closeSpeed;
		return y;
	}

	public int getBottomYPosition()
	{
		int y = (int) bottomYPosition;
		if ((topYPosition + topSprite.getHeight()) < bottomYPosition)
			bottomYPosition -= closeSpeed;
		return y;
	}
	
	public void updateXPosition(GameLoop gameLoop)
	{
		if (xPosition < gameLoop.getBirdObject().getXPosition() && canGiveScore)
		{
			canGiveScore = false;
			gameLoop.getGameUpdateObject().updateScore();
		}
		xPosition -= pipeSpeed;
	}
	
	public BufferedImage getBottomSprite()
	{
		return bottomSprite;	
	}

	public BufferedImage getTopSprite()
	{
		return topSprite;	
	}
	
	public void setSprite()
	{
		try 
		{
			bottomSprite = ImageIO.read(new File("src/resources/bottomPipe.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			topSprite = ImageIO.read(new File("src/resources/topPipe.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
