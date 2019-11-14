package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird 
{

	int xPosition = 200;
	int yPosition = 200;
	
	BufferedImage sprite0;
	BufferedImage sprite1;
	BufferedImage sprite2;
	BufferedImage spriteIdle;
	
	double velocity = -0.0;
	double xVelocity = -0.0;
	double accleration = 0.3;
	
	double jumpHeight = -6;
	
	double spriteIndex = 0;
	double animationSpeed = 0.03;
	
	public Bird()
	{
		setSprite();
	}
	
	public int getXPosition()
	{
		return xPosition;
	}
	
	public int getYPosition()
	{
		return yPosition;
	}
	
	public void updateXPosition()
	{
		if (yPosition <= 0)
			velocity = 2;
		yPosition += getVelocity();
	}
	
	public void updateYPosition(GameLoop gameLoop)
	{
		if (gameLoop.getMouseXPos() > xPosition)
		{
			xPosition += updateXVelocity(true);
		}
		else if (gameLoop.getMouseXPos() < xPosition)
		{
			xPosition += updateXVelocity(false);
		}
	}
	
	public int updateXVelocity(boolean greaterThan)
	{
		if (greaterThan && xVelocity < 2)
		{
			xVelocity += 1;			
			return (int) xVelocity;
		}
		else if (!greaterThan && xVelocity > -3)
		{
			xVelocity -= 1;
			return (int) xVelocity;
		}
		else 
			return (int) xVelocity;
			
	}
	
	public int getVelocity()
	{
		int velocity2 = (int) velocity;
		velocity += accleration;
		
		return velocity2;
	}
	
	public double returnVelocity()
	{
		
		return velocity;
		
	}
	
	public void updateVelocity()
	{
		spriteIndex = 0;
		velocity = jumpHeight;
	}
	
	public BufferedImage getSprite(GameLoop gameLoop)
	{
		if (!gameLoop.getGameUpdateObject().getGameRunning())
		{
			return sprite1;
		}
		else if (spriteIndex < 1)
		{
			spriteIndex += animationSpeed;
			return sprite0;
		}
		else if (spriteIndex < 2)
		{
			spriteIndex += animationSpeed;
			return sprite1;
		}
		else if (spriteIndex < 3)
		{
			spriteIndex += animationSpeed;
			return sprite2;
		}
		else
			return spriteIdle;
	}
	
	public void setSprite()
	{
		try 
		{
			sprite0 = ImageIO.read(new File("src/resources/bird0.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			sprite1 = ImageIO.read(new File("src/resources/bird1.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			sprite2 = ImageIO.read(new File("src/resources/bird2.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		try 
		{
			spriteIdle = ImageIO.read(new File("src/resources/birdIdle.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
