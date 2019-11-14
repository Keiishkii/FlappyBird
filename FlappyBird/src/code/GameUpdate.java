package code;

public class GameUpdate 
{

	int pipeBuffer;
	int score = 0;
	int pipeSpeed = 3;
	boolean gameContinue = true;
	boolean secondPipeActive;
	
	String hazardMode = "Pillar";
	
	int hazardCycleCounter;
	
	int pipeTimeCycle;
		
	public void nextGameState(GameLoop gameLoop)
	{
		if (pipeTimeCycle == 0)
			pipeTimeCycle = (gameLoop.getPipe0Object().getBottomSprite().getWidth() + gameLoop.getWidth()) / pipeSpeed;
		if (gameContinue)
		{						
			gameLoop.getBirdObject().updateXPosition();
			gameLoop.getBirdObject().updateYPosition(gameLoop);
			
			gameLoop.getCoinListObject().checkCoinList(gameLoop);
			gameLoop.getCoinListObject().updateCoinPosition(pipeSpeed);
			
			if (gameLoop.getCoinListObject().checkCollisionAll(gameLoop))
			{
				score += 5;
			}
			
			if (hazardMode == "Pillar")
				pillarHazard(gameLoop);
		
			
			//if (hazardCycleCounter > )
			
		}
		else
			endGame(gameLoop);
	}
	
	public void pillarHazard(GameLoop gameLoop)
	{
		pipeBuffer += 1;
		if (score > 4)
		{
			secondPipeActive = true;
		}
		
		if (gameLoop.getPipe0Object() != null)
		{
			if (collision0Detection(gameLoop) || gameLoop.getBirdObject().yPosition > 520)
			{
				gameContinue = false;
				endGame(gameLoop);
			}
			gameLoop.getPipe0Object().updateXPosition(gameLoop);
		}
		else
			gameLoop.createPipe0Object();	

		if (gameLoop.getPipe0Object() != null && pipeBuffer == pipeTimeCycle)
		{
			gameLoop.killPipe0Object();
			pipeBuffer = 0;
		}
		//////
		
		//////
		if (gameLoop.getPipe1Object() != null)
		{
			if (collision1Detection(gameLoop) || gameLoop.getBirdObject().yPosition > 520)
			{
				gameContinue = false;
				endGame(gameLoop);
			}
			gameLoop.getPipe1Object().updateXPosition(gameLoop);
		}
		else if ((secondPipeActive && (pipeBuffer - (pipeTimeCycle / 2)) == 1))
			gameLoop.createPipe1Object();	

		if ((gameLoop.getPipe1Object() != null && (pipeBuffer - (pipeTimeCycle / 2)) == 0))
		{
			gameLoop.killPipe1Object();
		}
		
	}
	
	public boolean getGameRunning()
	{
		return gameContinue;
	}
	
	public void updateScore()
	{
		score += 1;
		System.out.println("Score = " + score);
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void endGame(GameLoop gameLoop)
	{
		if (!(gameLoop.getBirdObject().yPosition > gameLoop.getHeight() + gameLoop.getBirdObject().getSprite(gameLoop).getHeight()))
		gameLoop.getBirdObject().updateXPosition();		
	}
	
	public boolean collision0Detection(GameLoop gameLoop)
	{
		Pipe pipe = gameLoop.getPipe0Object();
		Bird bird = gameLoop.getBirdObject();
		
		if (
				((bird.getYPosition() + 12 < pipe.getTopYPosition() + pipe.getTopSprite().getHeight() || bird.getYPosition() + bird.getSprite(gameLoop).getHeight() - 16 > pipe.getBottomYPosition())
				&& bird.getXPosition() + 68 > pipe.getXPosition() && bird.getXPosition() - 104< pipe.getXPosition()
				)
			)
			return true;
		else
			return false;
	}
	
	public int getPipeSpeed()
	{
		return pipeSpeed;
	}
	
	public boolean collision1Detection(GameLoop gameLoop)
	{
		if (
				((gameLoop.getBirdObject().getYPosition() < gameLoop.getPipe1Object().getTopYPosition() + 540|| gameLoop.getBirdObject().getYPosition() + (48) > gameLoop.getPipe1Object().getBottomYPosition()) && 
				gameLoop.getBirdObject().getXPosition() + 68 > gameLoop.getPipe1Object().getXPosition() && 
				gameLoop.getBirdObject().getXPosition() - 104< gameLoop.getPipe1Object().getXPosition()
				)
			)
			return true;
		else
			return false;
	}
	
}
