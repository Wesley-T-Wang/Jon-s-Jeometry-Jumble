
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Boss3 extends Boss1 {

	private Engine engine;
	private Color color;
	public Boss3(int x, int y, int h, ID id, Handler handler, Engine engine) {
		
		super( x, y, h, id, handler, engine);
		this.handler = handler;
		this.engine = engine;
		health = h;
		timer = 1500;
		timer2 = 0;
		// TODO Auto-generated constructor stub
	}

	public void tick() 
	{
		x = restrict(x, 5, Engine.WIDTH - 100);
		y = restrict(y, 5, Engine.HEIGHT - 75);
		shoot();
		timer++;
		timer2++;
		move() ;
		if (this.getHealth() <= 0){
			this.setHealth(0);
			handler.removeObject(this);
			Engine.victory = true;
		}		
		
		x += speedX;
		y += speedY;

	}


	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x,y , 100, 100);

		g.setColor(Color.BLACK);
		g.fillRect( x + 17, y + 20, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 65, y + 20, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 15, y + 60, 60, 10);

		color = new Color( 255 - this.colorValue(), this.colorValue(), 0);
		g.setColor(color);
		g.fillRect(0 , 0 ,(this.getHealth()) / 5, 10);

		g.setColor(Color.GRAY);
		g.drawRect(0 , 0 , 1000, 10);

		g.setColor(Color.RED);
		g.drawRect(x , y , 100, 100);

	}
	public void shoot()
	{
		if (timer == 1500){
			for(int i = 0; i< 5; i++)
			{
				Enemy f = new Enemy( (int)(Math.random() * 900), (int) (Math.random() * 250) + 30, 50, ID.Enemy, handler);
				handler.addObject(f);

				Kamikaze w = new Kamikaze( (int)(Math.random() * 900), (int) (Math.random() * 300) + 30, 50, ID.Enemy, handler, 1);
				handler.addObject(w);

			}
			Turret k = new Turret( (int)(Math.random() * 900), 730, 50, ID.Enemy, handler);
			handler.addObject(k);
			timer = 0;
		}
	}

	//There was a bug when using SSMinnowJohnson shooting the big bullet at the boss
	public int colorValue() {

		if ((this.getHealth()/25 + 55) >= 0) {
			return (this.getHealth()/25 + 55);
		}
		else 
			return 55;

	}

}

