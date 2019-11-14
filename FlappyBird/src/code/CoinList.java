package code;

public class CoinList 
{

	Coin coin0;
	Coin coin1;
	Coin coin2;
	Coin coin3;
	
	public CoinList(GameLoop gameLoop)
	{
		
	}
	
	public boolean checkCollisionSingle(GameLoop gameLoop, Coin coin)
	{
		Bird bird = gameLoop.getBirdObject();
		if (	bird.getYPosition() + bird.getSprite(gameLoop).getHeight() > coin.yPos() && bird.getYPosition() < coin.getSprite().getHeight() + coin.yPos
			&& 	bird.getXPosition() + bird.getSprite(gameLoop).getWidth() > coin.xPos() && bird.getXPosition() < coin.getSprite().getWidth() + coin.xPos)
		{
			return true;
		}
		else 
			return false;
	}
	
	public boolean checkCollisionAll(GameLoop gameLoop)
	{
		if (coin0 != null && checkCollisionSingle(gameLoop, coin0))
		{
			killCoinObject0();
			return true;
		}
		else if (coin1 != null && checkCollisionSingle(gameLoop, coin1))
		{
			killCoinObject1();
			return true;
		}
		else if (coin2 != null && checkCollisionSingle(gameLoop, coin2))
		{
			killCoinObject2();
			return true;
		}
		else if (coin3 != null && checkCollisionSingle(gameLoop, coin3))
		{
			killCoinObject3();
			return true;
		}
		else
			return false;
	}
	
	public void updateCoinPosition(int speed)
	{
		if (coin0 != null)
			coin0.updateXPos(speed);
		if (coin1 != null)
			coin1.updateXPos(speed);
		if (coin2 != null)
			coin2.updateXPos(speed);
		if (coin3 != null)
			coin3.updateXPos(speed);
	}
	
	public void checkCoinList(GameLoop gameLoop)
	{
		int rand = (int) (400 * Math.random());
		
		if (coin0 != null && coin0.xPos() - gameLoop.getCoinReferance().getSprite().getWidth() < 0)
			killCoinObject0();
		if (coin1 != null && coin1.xPos() - gameLoop.getCoinReferance().getSprite().getWidth() < 0)
			killCoinObject1();
		if (coin2 != null && coin2.xPos() - gameLoop.getCoinReferance().getSprite().getWidth() < 0)
			killCoinObject2();
		if (coin3 != null && coin3.xPos() - gameLoop.getCoinReferance().getSprite().getWidth() < 0)
			killCoinObject3();
				
		if (coin0 == null && rand == 10)
			{
			createCoin0(gameLoop);
			System.out.println(rand);
			}
		if (coin1 == null && rand == 11)
			{
			createCoin1(gameLoop);
			System.out.println(rand);
			}
		if (coin2 == null && rand == 12)
			{
			createCoin2(gameLoop);
			System.out.println(rand);
			}
		if (coin3 == null && rand == 13)
			{
			createCoin3(gameLoop);
			System.out.println(rand);
			}
	}
	
	public void killCoinObject0()
	{
		coin0 = null;
	}

	public void killCoinObject1()
	{
		coin1 = null;
	}
	
	public void killCoinObject2()
	{
		coin2 = null;
	}
	
	public void killCoinObject3()
	{
		coin3 = null;
	}
	
	public Coin getCoin0Object()
	{
		return coin0;
	}
	
	public Coin getCoin1Object()
	{
		return coin1;
	}
	
	public Coin getCoin2Object()
	{
		return coin2;
	}
	
	public Coin getCoin3Object()
	{
		return coin3;
	}
	
	private void createCoin0(GameLoop gameLoop)
	{
		coin0 = new Coin(gameLoop);
	}

	private void createCoin1(GameLoop gameLoop)
	{
		coin1 = new Coin(gameLoop);
	}

	private void createCoin2(GameLoop gameLoop)
	{
		coin2 = new Coin(gameLoop);
	}

	private void createCoin3(GameLoop gameLoop)
	{
		coin3 = new Coin(gameLoop);
	}
}
