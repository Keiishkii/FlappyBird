package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameLoop implements MouseListener, Runnable, MouseMotionListener
{

	static GameLoop gameLoop = new GameLoop();
	
	JFrame frame;
	Display display = new Display();
	GameUpdate gameUpdate = new GameUpdate();
	Bird bird = new Bird();
	
	Pipe pipeReferance = new Pipe(0,0,0);
	Coin coinReferance;
	CoinList coinList;
	
	Pipe pipe0;
	Pipe pipe1;
	
	int pipeSpeed;
	int mouseXPos;
	int mouseYPos;
	
	public static void main(String args[])
	{
		
		Thread loop = new Thread(gameLoop);
		loop.start();
		
	}

	public GameLoop()
	{
		frame = new JFrame();
		
		frame.setTitle("Flappy Bird");
		
		frame.setIconImage(new ImageIcon("src/resources/icon.png").getImage());
		
		frame.setSize(1200, 600);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.createBufferStrategy(2);

		createPipe0Object();
		
	}

	public void run() 
	{
		double time1;
		double time2;
		double drawTime;
		int frameBuffer;
		
		coinList = new CoinList(gameLoop);
		coinReferance = new Coin(gameLoop);
		
		while(true)
		{

			time1 = System.currentTimeMillis();
			
			///
			
			
			display.draw(gameLoop);
			gameUpdate.nextGameState(gameLoop);
			
			
			///
			
			time2 = System.currentTimeMillis();
			drawTime = time2 - time1;
	
			frameBuffer = (int) ((1000/60) - drawTime);
			//System.out.println(drawTime + " = DrawTime");
			//System.out.println(frameBuffer + " = FrameBuffer");
			
			if (frameBuffer > 0)
			{
				try  
				{
				Thread.sleep((frameBuffer));
				} 
				catch (InterruptedException e) 
				{
				}
			}

			time2 = System.currentTimeMillis();
			//System.out.println("Time = " + (time2 - time1) + " ms" + "Fps = " + (1000 / (time2 - time1)));
			
		}
		
	}

	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	public void mouseExited(MouseEvent arg0)
	{
		
	}

	public void mousePressed(MouseEvent arg0) 
	{
		if(gameUpdate.getGameRunning())
			bird.updateVelocity();
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		
	}
	
	public BufferStrategy getBufferStrategy() 
	{
		return frame.getBufferStrategy();
	}
	
	public int getWidth()
	{
		return frame.getWidth();
	}
	
	public int getHeight()
	{
		return frame.getHeight();		
	}
	
	public Bird getBirdObject()
	{
		return bird;
	}
	
	public void createPipe0Object()
	{
		pipe0 = new Pipe(frame.getWidth(), frame.getHeight(), gameUpdate.getPipeSpeed());
	}
	
	public void createPipe1Object()
	{
		pipe1 = new Pipe(frame.getWidth(), frame.getHeight(), gameUpdate.getPipeSpeed());
	}
	
	public void killPipe0Object()
	{
		pipe0 = null;
	}
	
	public void killPipe1Object()
	{
		pipe1 = null;
	}
	
	public Pipe getPipeReferance()
	{
		return pipeReferance;
	}
	
	public Coin getCoinReferance()
	{
		return coinReferance;
	}
	
	public CoinList getCoinListObject()
	{
		return coinList;
	}
	
	public Pipe getPipe0Object()
	{
		return pipe0;
	}
	
	public Pipe getPipe1Object()
	{
		return pipe1;
	}
	
	public GameUpdate getGameUpdateObject()
	{
		return gameUpdate;
	}

	public void mouseDragged(MouseEvent mouse) 
	{
		
	}

	public void mouseMoved(MouseEvent mouse) 
	{
		mouseXPos = mouse.getX();
		mouseYPos = mouse.getY();
	}
	
	public int getMouseXPos()
	{
		return mouseXPos;
	}
	
	public int getMouseYPos()
	{
		return mouseYPos;
	}
	
}
