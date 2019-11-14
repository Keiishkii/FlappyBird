package code;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Display 
{

	int x;
	int xOffset;
	
	BufferedImage background;
	BufferedImage floor;
	BufferedImage title;
	//
	BufferedImage zero;
	BufferedImage one;
	BufferedImage two;
	BufferedImage three;
	BufferedImage four;
	BufferedImage five;
	BufferedImage six;
	BufferedImage seven;
	BufferedImage eight;
	BufferedImage nine;
	Graphics g;
	double rotation = 0;

	public Display()
	{
		title = setSprite("title");
		background = setSprite("background");
		floor = setSprite("floor");
		
		zero = setSprite("zero");
		one = setSprite("one");
		two = setSprite("two");
		three = setSprite("three");
		four = setSprite("four");
		five = setSprite("five");
		six = setSprite("six");
		seven = setSprite("seven");
		eight = setSprite("eight");
		nine = setSprite("nine");
	}
	
	public void draw(GameLoop gameLoop)
	{
		
		int width = gameLoop.getWidth();
		int height = gameLoop.getHeight();
		
		BufferStrategy bf = gameLoop.getBufferStrategy();
		g = bf.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		g.drawImage(inGameDisplay(gameLoop) , 0, 0, null);
			
		g.dispose();
		bf.show();
		
	}
	
	private BufferedImage inGameDisplay(GameLoop gameLoop)
	{		
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();
		///
		
		g.drawImage(background(gameLoop), 0, 0, null);
		g.drawImage(pipeImage(gameLoop), 0, 0, null);
		//g.drawImage(floor, 0, 560, null);
		g.drawImage(birdImage(gameLoop), gameLoop.getBirdObject().getXPosition() - 6, gameLoop.getBirdObject().getYPosition() - 16, null);
		g.drawImage(coinImage(gameLoop), 0, 0, null);
		
		g.drawImage(drawScore(gameLoop), 0, 0, null);
		//g.drawImage(title, ((gameLoop.getWidth() / 2) - 192), 100, null);
		///
		g2.dispose();			
		return display;	
	}

	
	private BufferedImage background(GameLoop gameLoop)
	{	
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();
		
		if (xOffset <= (background.getWidth() * -1))
			xOffset = 0;
		else if (gameLoop.getGameUpdateObject().getGameRunning())
			xOffset -= (gameLoop.getGameUpdateObject().getPipeSpeed() / 2);
		
		{
			g.drawImage(background, xOffset, 0, null);
			g.drawImage(background, xOffset + background.getWidth(), 0, null);
			g.drawImage(background, xOffset + background.getWidth() * 2, 0, null);
			
		}
		
		g2.dispose();			
		return display;		
	}
	
	private BufferedImage coinImage(GameLoop gameLoop)
	{
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();
		
		CoinList coinList = gameLoop.getCoinListObject();
		
		if (coinList.getCoin0Object() != null)
			g.drawImage(gameLoop.getCoinReferance().getSprite(), coinList.getCoin0Object().xPos(), coinList.getCoin0Object().yPos, null);
		if (coinList.getCoin1Object() != null)
			g.drawImage(gameLoop.getCoinReferance().getSprite(), coinList.getCoin1Object().xPos(), coinList.getCoin1Object().yPos, null);
		if (coinList.getCoin2Object() != null)
			g.drawImage(gameLoop.getCoinReferance().getSprite(), coinList.getCoin2Object().xPos(), coinList.getCoin2Object().yPos, null);
		if (coinList.getCoin3Object() != null)
			g.drawImage(gameLoop.getCoinReferance().getSprite(), coinList.getCoin3Object().xPos(), coinList.getCoin3Object().yPos, null);
		
		g2.dispose();			
		return display;	
		
	}
	
	private BufferedImage pipeImage(GameLoop gameLoop)
	{
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();
		
		try
		{
		g.drawImage(gameLoop.getPipeReferance().getTopSprite(), gameLoop.getPipe0Object().getXPosition(), gameLoop.getPipe0Object().getTopYPosition(), null);
		g.drawImage(gameLoop.getPipeReferance().getBottomSprite(), gameLoop.getPipe0Object().getXPosition(), gameLoop.getPipe0Object().getBottomYPosition(), null);
		}
		catch(Exception e)
		{
			
		}	
		
		try
		{
		g.drawImage(gameLoop.getPipeReferance().getTopSprite(), gameLoop.getPipe1Object().getXPosition(), gameLoop.getPipe1Object().getTopYPosition(), null);
		g.drawImage(gameLoop.getPipeReferance().getBottomSprite(), gameLoop.getPipe1Object().getXPosition(), gameLoop.getPipe1Object().getBottomYPosition(), null);
		}
		catch(Exception e)
		{
			
		}
		
		g2.dispose();			
		return display;	
	}
		
	public BufferedImage drawScore(GameLoop gameLoop)
	{
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();

		int score = gameLoop.getGameUpdateObject().getScore();
		
		if (score < 10)
			g.drawImage(getScore(score), (gameLoop.getWidth() / 2) - (getScore(1).getWidth() / 2), 40, null);
		else if (score >= 10 && score < 100)
		{
			g.drawImage(getScore((score / 10)), (gameLoop.getWidth() / 2) - (((getScore(1).getWidth() / 2) * 3 ) - 4), 40, null);
			g.drawImage(getScore((score % 10)), (gameLoop.getWidth() / 2) - (((getScore(1).getWidth() / 2) )), 40, null);			
		}
		else
		{
			g.drawImage(getScore((score / 100)), (gameLoop.getWidth() / 2) - (((getScore(1).getWidth() / 2) * 5 )), 40, null);
			g.drawImage(getScore(((score % 100) / 10)), (gameLoop.getWidth() / 2) - (((getScore(1).getWidth() / 2) * 3 )), 40, null);	
			g.drawImage(getScore((score % 10)), (gameLoop.getWidth() / 2) - (((getScore(1).getWidth() / 2) )), 40, null);				
		}
		
		g2.dispose();			
		return display;	
	}
	
	public BufferedImage getScore(int score)
	{		
		if (score < 10)
		{
			switch (score)
			{
			case 0 : return zero;
			case 1 : return one;
			case 2 : return two;
			case 3 : return three;
			case 4 : return four;
			case 5 : return five;
			case 6 : return six;
			case 7 : return seven;
			case 8 : return eight;
			case 9 : return nine;
			}
		}
		
		return null;
	}
	
	public BufferedImage setSprite(String name)
	{
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read(new File("src/resources/" + name +  ".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage birdImage(GameLoop gameLoop)
	{
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();


		double locationX = gameLoop.getBirdObject().getSprite(gameLoop).getWidth() / 2;
		double locationY = gameLoop.getBirdObject().getSprite(gameLoop).getHeight() / 2;
		double velocity = gameLoop.getBirdObject().returnVelocity();
		
		if ((((velocity) / 60) * Math.PI) < Math.PI * -0.2)
			rotation = Math.PI * 0.2;
		if ((((velocity) / 60) * Math.PI) < Math.PI * 0.5)
		{
			
			rotation = ((velocity / 60) * Math.PI);
		}
		else 
			rotation = Math.PI * 0.5;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotation, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(gameLoop.getBirdObject().getSprite(gameLoop), null), gameLoop.getBirdObject().getXPosition(), gameLoop.getBirdObject().getYPosition(), null);

		g2.dispose();			
		return display;	
	}	
}
