import java.awt.geom.Rectangle2D;

public class Ming extends Sprite{
	
	private int direction;
	private int directionFacing;
//	private int oldDirection;
	private int oldDirectionFacing;
	private int health;
	private int healthPack;
	private int ammo;
	private int battery;
	private int sandwiches;
	public boolean action;
	public boolean changed;
	public boolean gun;
	public boolean oldGun;
	public boolean isDead;
	public boolean run;
	private boolean attacked;
	protected boolean attack;
	protected Sprite hit;
	protected Sprite leftA;
	protected Sprite rightA;
	protected Sprite leftG;
	protected Sprite rightG;
	protected Sprite upA;
	protected Sprite downA;
	protected Sprite upG;
	protected Sprite downG;
	protected Sprite upLeftA;
	protected Sprite downLeftA;
	protected Sprite upRightA;
	protected Sprite downRightA;
	protected Sprite upLeftG;
	protected Sprite downLeftG;
	protected Sprite upRightG;
	protected Sprite downRightG;
	protected Sprite axeHit;
	protected Sprite axeMiss;
	protected Sprite fireUp;
	protected Sprite fireDown;
	protected Sprite fireLeft;
	protected Sprite fireRight;	
	protected Sprite fireUpRight;
	protected Sprite fireDownRight;
	protected Sprite fireDownLeft;
	protected Sprite fireUpLeft;
	protected Sprite leftStopA;
	protected Sprite rightStopA;
	protected Sprite leftStopG;
	protected Sprite rightStopG;
	protected Sprite upStopA;
	protected Sprite downStopA;
	protected Sprite upStopG;
	protected Sprite downStopG;
	protected Sprite upLeftStopA;
	protected Sprite downLeftStopA;
	protected Sprite upRightStopA;
	protected Sprite downRightStopA;
	protected Sprite upLeftStopG;
	protected Sprite downLeftStopG;
	protected Sprite upRightStopG;
	protected Sprite downRightStopG;
	public boolean moving;
	private boolean oldMoving;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public int stepTime;
	protected Ribbon rb;
	protected Sound pain;
	protected Sound death;
	protected Sound bite;
	protected Sound axeSwing;
	private long attackTime;
	private boolean attackChange;
	
	public Ming(Ribbon rib){
		super();
		this.stepTime = 200;
		this.attackTime = 0;
		this.health = 100;
		this.ammo = 50;
		this.sandwiches = 0;
		this.battery = 50;
		this.healthPack = 0;
		this.loadImages();
		action = false;
		changed = false;
		up = false;
		down = false;
		left = false;
		right = false;
		moving = false;
		run = false;
		attack = false;
		attacked = false;
		attackChange = false;
		rb = rib;
		pain = new Sound("sounds/reign-mingpain.wav");
		death = new Sound("sounds/reign-mingdeath.wav");
		bite = new Sound("sounds/reign-bite.wav");
		axeSwing = new Sound("sounds/reign-axe.wav");
		this.copy(downG);
		this.setX(0);
		this.setY(0);
		this.visible = true;
		this.direction = 4;
//		this.oldDirection = this.direction;
		this.gun = true;
		this.oldGun = this.gun;
		this.oldMoving = false;
		isDead = false;
	}

	
	public int getDirectionMoving() {
		return direction;
	}


	public void setDirectionMoving(int directionMoving) {
		this.direction = directionMoving;
	}


	public void setDirectionFacing(int directionFacing) {
		this.directionFacing = directionFacing;
	}


	public int getDirectionFacing(){
		return directionFacing;
	}
	
	@Override
	public void update(long timePassed){
		super.update(timePassed);
		if (attack){
			this.attackTime(timePassed);
		}
		this.move();
		if (directionChanged()){
			this.face();
		}
		rb.update();
		if (this.health <= 50){
			if (this.healthPack>=1){
				this.useHealth();
			}
			else if (this.getSandwiches()>= 1){
				this.eatSandwich();
			}
		}
		this.hitBox.setRect(this.getX()+30, this.getY(),this.getWidth()-60, this.getHeight()/2);
	}

	@Override
	public synchronized void loadImages(){
		leftA = new Sprite();
		leftG = new Sprite();
		rightA = new Sprite();
		rightG = new Sprite();
		upA = new Sprite();
		upG = new Sprite();
		downA = new Sprite();
		downG = new Sprite();
		axeHit = new Sprite();
		axeMiss = new Sprite();
		fireLeft = new Sprite();
		fireRight = new Sprite();
		fireUp = new Sprite();
		fireDown = new Sprite();
		fireUpRight = new Sprite();
		fireDownRight = new Sprite();
		fireDownLeft = new Sprite();
		fireUpLeft = new Sprite();
		upLeftA = new Sprite();
		downLeftA = new Sprite();
		upRightA = new Sprite();
		downRightA = new Sprite();
		upLeftG = new Sprite();
		downLeftG = new Sprite();
		upRightG = new Sprite();
		downRightG = new Sprite();
		leftStopA = new Sprite();
		leftStopG = new Sprite();
		rightStopA = new Sprite();
		rightStopG = new Sprite();
		upStopA = new Sprite();
		upStopG = new Sprite();
		downStopA = new Sprite();
		downStopG = new Sprite();
		upLeftStopA = new Sprite();
		downLeftStopA = new Sprite();
		upRightStopA = new Sprite();
		downRightStopA = new Sprite();
		upLeftStopG = new Sprite();
		downLeftStopG = new Sprite();
		upRightStopG = new Sprite();
		downRightStopG = new Sprite();
		hit = new Sprite();
		
		try {
			hit.addScene("sprites/ming/Hit1.png", stepTime);
			hit.addScene("sprites/ming/Hit2.png", stepTime);
			
			axeHit.addScene("sprites/ming/AxeAttack2.png", stepTime/2);
			
			axeMiss.addScene("sprites/ming/AxeAttack1.png", stepTime);
			
			upLeftA.addScene("sprites/ming/upLeftA1.png", stepTime);
			upLeftA.addScene("sprites/ming/upLeftA2.png", stepTime);
			upLeftA.addScene("sprites/ming/upLeftA3.png", stepTime);
			upLeftA.addScene("sprites/ming/upLeftA4.png", stepTime);
			
			downLeftA.addScene("sprites/ming/downLeftA1.png", stepTime);
			downLeftA.addScene("sprites/ming/downLeftA2.png", stepTime);
			downLeftA.addScene("sprites/ming/downLeftA3.png", stepTime);
			downLeftA.addScene("sprites/ming/downLeftA4.png", stepTime);
			
			upRightA.addScene("sprites/ming/upRightA1.png", stepTime);
			upRightA.addScene("sprites/ming/upRightA2.png", stepTime);
			upRightA.addScene("sprites/ming/upRightA3.png", stepTime);
			upRightA.addScene("sprites/ming/upRightA4.png", stepTime);
			
			downRightA.addScene("sprites/ming/downRightA1.png", stepTime);
			downRightA.addScene("sprites/ming/downRightA2.png", stepTime);
			downRightA.addScene("sprites/ming/downRightA3.png", stepTime);
			downRightA.addScene("sprites/ming/downRightA4.png", stepTime);
			
			upLeftStopA.addScene("sprites/ming/upLeftA1.png", stepTime);
			
			downLeftStopA.addScene("sprites/ming/downLeftA1.png", stepTime);
			
			upRightStopA.addScene("sprites/ming/upRightA1.png", stepTime);
			
			downRightStopA.addScene("sprites/ming/downRightA1.png", stepTime);
			
			upLeftG.addScene("sprites/ming/upLeftG1.png", stepTime);
			upLeftG.addScene("sprites/ming/upLeftG2.png", stepTime);
			upLeftG.addScene("sprites/ming/upLeftG3.png", stepTime);
			upLeftG.addScene("sprites/ming/upLeftG4.png", stepTime);
			
			downLeftG.addScene("sprites/ming/downLeftG1.png", stepTime);
			downLeftG.addScene("sprites/ming/downLeftG2.png", stepTime);
			downLeftG.addScene("sprites/ming/downLeftG3.png", stepTime);
			downLeftG.addScene("sprites/ming/downLeftG4.png", stepTime);
			
			upRightG.addScene("sprites/ming/upRightG1.png", stepTime);
			upRightG.addScene("sprites/ming/upRightG2.png", stepTime);
			upRightG.addScene("sprites/ming/upRightG3.png", stepTime);
			upRightG.addScene("sprites/ming/upRightG4.png", stepTime);
			
			downRightG.addScene("sprites/ming/downRightG1.png", stepTime);
			downRightG.addScene("sprites/ming/downRightG2.png", stepTime);
			downRightG.addScene("sprites/ming/downRightG3.png", stepTime);
			downRightG.addScene("sprites/ming/downRightG4.png", stepTime);
			
			upLeftStopG.addScene("sprites/ming/upLeftG1.png", stepTime);
			
			downLeftStopG.addScene("sprites/ming/downLeftG1.png", stepTime);
			
			upRightStopG.addScene("sprites/ming/upRightG1.png", stepTime);
			
			downRightStopG.addScene("sprites/ming/downRightG1.png", stepTime);
			
			leftStopA.addScene("sprites/ming/LeftAxe1.png", stepTime);
			
			leftA.addScene("sprites/ming/LeftAxe1.png", stepTime);
			leftA.addScene("sprites/ming/LeftAxe2.png", stepTime);
			leftA.addScene("sprites/ming/LeftAxe3.png", stepTime);
			leftA.addScene("sprites/ming/LeftAxe4.png", stepTime);
	
			rightStopA.addScene("sprites/ming/RightAxe1.png", stepTime);
			
			rightA.addScene("sprites/ming/RightAxe1.png", stepTime);
			rightA.addScene("sprites/ming/RightAxe2.png", stepTime);
			rightA.addScene("sprites/ming/RightAxe3.png", stepTime);
			rightA.addScene("sprites/ming/RightAxe4.png", stepTime);
			
			leftStopG.addScene("sprites/ming/LeftGun1.png", stepTime);
			
			leftG.addScene("sprites/ming/LeftGun1.png", stepTime);
			leftG.addScene("sprites/ming/LeftGun2.png", stepTime);
			leftG.addScene("sprites/ming/LeftGun3.png", stepTime);
			leftG.addScene("sprites/ming/LeftGun4.png", stepTime);
	
			rightStopG.addScene("sprites/ming/RightGun1.png", stepTime);
			
			rightG.addScene("sprites/ming/RightGun1.png", stepTime);
			rightG.addScene("sprites/ming/RightGun2.png", stepTime);
			rightG.addScene("sprites/ming/RightGun3.png", stepTime);
			rightG.addScene("sprites/ming/RightGun4.png", stepTime);
			
			upStopA.addScene("sprites/ming/BackAxe1.png", stepTime);
	
			upA.addScene("sprites/ming/BackAxe1.png", stepTime);
			upA.addScene("sprites/ming/BackAxe2.png", stepTime);
			upA.addScene("sprites/ming/BackAxe3.png", stepTime);
			upA.addScene("sprites/ming/BackAxe4.png", stepTime);
			
			upStopG.addScene("sprites/ming/BackGun1.png", stepTime);
	
			upG.addScene("sprites/ming/BackGun1.png", stepTime);
			upG.addScene("sprites/ming/BackGun2.png", stepTime);
			upG.addScene("sprites/ming/BackGun3.png", stepTime);
			upG.addScene("sprites/ming/BackGun4.png", stepTime);
			
			downStopG.addScene("sprites/ming/FrontGun1.png", stepTime);
	
			downG.addScene("sprites/ming/FrontGun1.png", stepTime);
			downG.addScene("sprites/ming/FrontGun2.png", stepTime);
			downG.addScene("sprites/ming/FrontGun3.png", stepTime);
			downG.addScene("sprites/ming/FrontGun4.png", stepTime);
			
			downStopA.addScene("sprites/ming/FrontAxe1.png", stepTime);
	
			downA.addScene("sprites/ming/FrontAxe1.png", stepTime);
			downA.addScene("sprites/ming/FrontAxe2.png", stepTime);
			downA.addScene("sprites/ming/FrontAxe3.png", stepTime);
			downA.addScene("sprites/ming/FrontAxe4.png", stepTime);
			
			fireUp.addScene("sprites/ming/firing/BackFire.png", stepTime);
			fireUpRight.addScene("sprites/ming/firing/upRightFire.png", stepTime);
			fireRight.addScene("sprites/ming/firing/RightFire.png", stepTime);
			fireDownRight.addScene("sprites/ming/firing/downRightFire.png", stepTime);
			fireDown.addScene("sprites/ming/firing/FrontFire.png", stepTime);
			fireDownLeft.addScene("sprites/ming/firing/downLeftFire.png", stepTime);
			fireDownLeft.addScene("sprites/ming/firing/downLeftFire.png", stepTime);
			fireLeft.addScene("sprites/ming/firing/LeftFire.png", stepTime);
			fireLeft.addScene("sprites/ming/firing/LeftFire.png", stepTime);
			fireUpLeft.addScene("sprites/ming/firing/upLeftFire.png", stepTime);
			
		} catch (Exception ex){}
		this.hitBox = new Rectangle2D.Double(this.getX()+20, this.getY(), this.getWidth()-40, this.getHeight()/2);
	}
	
	public boolean directionChanged(){
		if (attack){
			return false;
		}
		else if (directionFacing != oldDirectionFacing){
			oldDirectionFacing = directionFacing;
			return true;
		}
		else if ((moving == oldMoving) && (gun == oldGun) && (!attacked) && !changed && !attackChange){
			return false;
		}
		attacked = false;
		attackChange = false;
		changed = false;
		//oldDirectionFacing = directionFacing;
		oldMoving = moving;
		//oldDirectionFacing = directionFacing;
		oldGun = gun;
		//oldDirection = direction;
		return true;
		
	}

	
	//Ming is moving which direction?
	public void move(){
		//determineDirection();
		double hypotenuse,scale_x,scale_y,speed,goX,goY;
		if (run){
			speed = 0.1;
		}
		else {
			speed = 0.04;
		}
		goX = 0;
		goY = 0;
		switch (direction){
		//up
		case 0:
			if (moving){
				goX = 0;
				goY = -20;
			}
			else {
				rb.stayStill();
			}	
			break;
		//up-right	
		case 1:
			if (moving){
				goX = 10;
				goY = -10;
			}
			else {
				rb.stayStill();
			}
			break;
		//right
		case 2:	
			if (moving){
				goX = 20;
				goY = 0;
			}
			else {
				rb.stayStill();
			}
			break;
		//down-right
		case 3:
			if (moving){
				goX = 10;
				goY = 10;
			}
			else {
				rb.stayStill();
			}
			break;
		//down	
		case 4:
			if (moving){
				goX = 0;
				goY = 20;
			}
			else {
				rb.stayStill();
			}
			break;
		//down-left
		case 5:
			if (moving){
				goX = -10;
				goY = 10;
			}
			else {
				rb.stayStill();
			}
			break;
		//left
		case 6:
			if (moving){
				goX = -20;
				goY = 0;
			}
			else {
				rb.stayStill();
			}
			break;
		//up-left	
		case 7:
			if (moving){
				goX = -10;
				goY = -10;
			}
			else {
				rb.stayStill();
			}
			break;
		}
		if (moving){
			hypotenuse = Math.hypot((double)(goX), (double)(goY));
			scale_x = (goX)/hypotenuse;
			scale_y = (goY)/hypotenuse;
			if (run){
				speed = 0.1;
			}
			else {
				speed = 0.04;
			}
			this.setVx((float)(scale_x*speed));
			this.setVy((float)(scale_y*speed));
		}
		else {
			rb.stayStill();
			this.setVx(0);
			this.setVy(0);
		}	

	}
	
	//Ming is facing which direction?
	public void face(){
		switch (directionFacing){
		//up
		case 0:
			if (moving){
					if (gun){
						this.copy(upG);
						//System.out.println("upG");
					
					}
					else {
						this.copy(upA);
						//System.out.println("upA");
					}
			}
			else {
					if (gun){
						this.copy(upStopG);
						//System.out.println("upStopG");
					}
					else {
						this.copy(upStopA);
						//System.out.println("upStopA");
					}	
			}	
			break;
		//up-right	
		case 1:
			if (moving){
					if (gun){
						this.copy(upRightG);
						//System.out.println("upRightG");
					}
					else {
						this.copy(upRightA);	
						//System.out.println("upRightA");
					}
			}
			else {
					if (gun){
						this.copy(upRightStopG);
						//System.out.println("upRightStopG");
					}
					else {
						this.copy(upRightStopA);
						//System.out.println("upRightStopA");
					}	
			}
			break;
		//right
		case 2:	
			if (moving){
					if (gun){
						this.copy(rightG);
						//System.out.println("rightG");		
						}
					else {
						this.copy(rightA);
						//System.out.println("rightA");
					}
			}
			else {
					if (gun){
						this.copy(rightStopG);
						//System.out.println("rightStopG");
					}
					else {
						this.copy(rightStopA);
						//System.out.println("rightStopA");
					}
			}
			break;
		//down-right
		case 3:
			if (moving){
					if (gun){
						this.copy(downRightG);
						//System.out.println("downRightG");
					}
					else {
						this.copy(downRightA);
						//System.out.println("downRightA");
					}
			}
			else {
					if (gun){
						this.copy(downRightStopG);
						//System.out.println("downRightStopG");
					}
					else {
						this.copy(downRightStopA);
						//System.out.println("downRightStopA");
					}	
			}
			break;
		//down	
		case 4:
			if (moving){
					if (gun){
						this.copy(downG);
						//System.out.println("downG");
					}
					else {
						this.copy(downA);
						//System.out.println("downA");
					}
			}
			else {
					if (gun){
						this.copy(downStopG);
						//System.out.println("downStopG");
					}
					else {
						this.copy(downStopA);
						//System.out.println("downStopA");
					}	
			}
			break;
		//down-left
		case 5:
			if (moving){
					if (gun){
						this.copy(downLeftG);
						//System.out.println("downLeftG");
					}
					else {
						this.copy(downLeftA);
						//System.out.println("downLeftA");
					}
			}
			else {
					if (gun){
						this.copy(downLeftStopG);
						//System.out.println("downLeftStopG");
					}
					else {
						this.copy(downLeftStopA);
						//System.out.println("downLeftStopA");
					}	
			}
			break;
		//left
		case 6:
			if (moving){
					if (gun){
						this.copy(leftG);
						//System.out.println("leftG");
					}
					else {
						this.copy(leftA);
						//System.out.println("leftA");
					}
			}
			else {
					if (gun){
						this.copy(leftStopG);
						//System.out.println("leftStopG");
					}
					else {
						this.copy(leftStopA);
						//System.out.println("leftStopA");
					}
			}
			break;
		//up-left	
		case 7:
			if (moving){
					if (gun){
						this.copy(upLeftG);
						//System.out.println("upLeftG");
					}
					else {
						this.copy(upLeftA);
						//System.out.println("upLeftA");
					}	
			}
			else {
					if (gun){
						this.copy(upLeftStopG);
						//System.out.println("upLeftStopG");
					}
					else {
						this.copy(upLeftStopA);
						//System.out.println("upLeftStopA");
					}
			}
			break;
		}
	}
	
	
	public void attackTime(long timePassed){
		attackTime += timePassed;
		//System.out.println("Still attacking ...");
		if (attackTime >= this.stepTime/2){
			//System.out.println("attack reset");
			attackTime = 0;
			attackChange = true;
			attack = false;
		}
	}
	
	public boolean detectSurvivor(Survivor s) {
		//if (this.getX() <= (s.getX()+s.getWidth()) && this.getX() >= s.getX() && this.getY() >= s.getY() && this.getY() <= (s.getY()+s.getHeight())){
		if (this.hitBox.intersects(s.hitBox)){
			return true;
		}
		return false;
	}
	
	public void detectAttack(Zombie z) {
		if (!z.isHit && z.visible && this.hitBox.intersects(z.hitBox)){
//			&& this.getX() <= (z.getX()+z.getWidth()) && (this.getX()+this.getWidth()) >= z.getX() && (this.getY()+this.getHeight()) >= z.getY() && this.getY() <= (z.getY()+z.getHeight()) && z.visible){
			attacked = true;
			hit();
		}
	}
	
	public boolean detectDoor(Area.Door d) {
		Rectangle2D grabBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (action && grabBox.intersects(d.getDoor())){
			action = false;
			return true;
		}
		return false;
	}
	
	public boolean detectItems(Item i) {
		Rectangle2D grabBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (i.visible && grabBox.intersects(i.hitBox) && action){
			action = false;
			switch (i.getItemType()){
			case 0:
				//Ammunition
				this.foundAmmo(i.getValue());
				return true;
			case 1:
				//Health
				this.foundHealth(i.getValue());
				return true;
			case 2:
				//Sandwich
				this.foundSandwich(i.getValue());
				return true;
			case 3:
				//Battery
				this.foundBattery(i.getValue());
				return true;
			}
		}
		return false;
	}
	
	public void attack(int toX,int toY){
		//System.out.println("Ming.attack()");
		attack = true;
		if (gun && ammo <= 0) {
			switchWeapon();
		}
		if (gun && ammo > 0) {	
			ammo -= 1;
			
			int angle1;
			angle1  = (int)(Math.atan2(-toY, toX)*(180/Math.PI)) % 360;
			//angle1 -= 90;
			if (angle1 < 0 ){
				angle1 += 360;
			}
			//System.out.println("Angle = " + angle1);
			if (angle1 >= 0 && angle1 <= 22){
				direction = 2;
				//System.out.println("ming is facing right");
			}
			else if (angle1 > 22 && angle1 <= 67){
				direction =1;
				//System.out.println("ming is facing up-right");
			}
			else if (angle1 > 67 && angle1 <= 112){
				direction = 0;
				//System.out.println("ming is facing up");
			}
			else if (angle1 > 112 && angle1 <= 157){
				direction = 7;
				//System.out.println("ming is facing up-left");
			}
			else if (angle1 > 157 && angle1 <= 202){
				direction = 6;
				//System.out.println("ming is facing left");
			}
			else if (angle1 > 202 && angle1 <= 247){
				direction = 5;
				//System.out.println("ming is facing down-left");
			}
			else if (angle1 > 247 && angle1 <= 292){
				direction = 4;
				//System.out.println("ming is direction down");
			}
			else if (angle1 > 292 && angle1 <= 337){
				direction = 3;
				//System.out.println("ming is facing down-right");
			}
			else if (angle1 > 337 && angle1 <= 360){
				direction = 2;
				//System.out.println("ming is facing right");
			}
			
			switch(direction){
			case 0:
				this.copy(fireUp);
				break;
			case 1:
				this.copy(fireUpRight);
				break;
			case 2:
				this.copy(fireRight);
				break;
			case 3:
				this.copy(fireDownRight);	
				break;
			case 4:
				this.copy(fireDown);
				break;
			case 5:
				//System.out.println("fireDownLeft");
				this.copy(fireDownLeft);
				break;
			case 6:
				//System.out.println("fireLeft");
				this.copy(fireLeft);
				break;
			case 7:
				//System.out.println("fireUpLeft");
				this.copy(fireUpLeft);	
				break;
			}
		}	
	}

	public boolean axing(Zombie z){
		if (attack){
			moving = false;
			//attack = false;
			return axeHit(z);
		}
		return false;
	}
	
	public boolean axeHit(Zombie z) {
		//attack = true;
		if (!axeSwing.running()){
			//System.out.println("axeSwing");
			axeSwing.start();
		}
		try {
			Rectangle2D attackBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());

			//if (this.getX()-10 <= (z.getX()+z.getWidth()) && (this.getX()+this.getWidth()+10) >= z.getX() && (this.getY()+this.getHeight()+10) >= z.getY() && this.getY()-10 <= (z.getY()+30) && z.visible){
			if (z.visible && !z.isHit && attackBox.intersects(z.hitBox)){
				direction = 4;
				this.copy(axeHit);
				return true;
			}
			else { 
				direction = 4;
				this.copy(axeMiss);
				return false;
			}
		} catch (Exception ex){
			System.out.println(ex);
			return false;
		}		
	}
	
	public void switchWeapon(){
		if (gun){
			gun=false;
			switch(direction){
				case 0:
					this.copy(upA);
					break;
				case 1:
					this.copy(upRightA);
					break;
				case 2:
					this.copy(rightA);
					break;
				case 3:
					this.copy(downRightA);	
					break;
				case 4:
					this.copy(downA);
					break;
				case 5:
					this.copy(downLeftA);
					break;
				case 6:
					this.copy(leftA);
					break;
				case 7:
					this.copy(upLeftA);	
					break;
			}
		}
		else {
			gun=true;
			switch(direction){
			case 0:
				this.copy(upG);
				break;
			case 1:
				this.copy(upRightG);
				break;
			case 2:
				this.copy(rightG);
				break;
			case 3:
				this.copy(downRightG);	
				break;
			case 4:
				this.copy(downG);
				break;
			case 5:
				this.copy(downLeftG);
				break;
			case 6:
				this.copy(leftG);
				break;
			case 7:
				this.copy(upLeftG);	
				break;
			}
		}
	}
	
	// everything over 100 counts as supplies for survivors at the end of the level
	public void foundHealth(int x) {
		if (health < 100){
			this.health += x;
			if (health > 100){
				health = 100;
			}
		}
		else {
			this.healthPack++;
		}
		//System.out.println("Found health: " + this.getHealth());
	}
	
	public void hit() {
		direction = 4;
		if (health%2 ==0){
			if (!bite.running()){
				//System.out.println("bite");
				bite.start();
			}
		}
		else{
			if (!pain.running()){
				//System.out.println("pain");
				pain.start();
			}
		}
		this.copy(hit);
		//this.health -= 25;
		this.health--;
		if (health <= 0){
			health = 0;
			isDead = true;
		}
		//System.out.println("Hit - health: " + this.getHealth());
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getHealthPack() {
		return healthPack;
	}
	
	public void foundAmmo(int ammo) {
		this.ammo += ammo;
		//System.out.println("Found ammo: " + this.getAmmo());
	}

	public int getAmmo() {
		return ammo;
	}
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	// everything over 100 counts as supplies for survivors at the end of the level
	public void foundBattery(int x) {
		this.battery += x;
		//System.out.println("Found battery: " + this.getBattery());
	}
	
	public void batteryDecrement(){
		this.battery -= 2;
	}

	public int getBattery() {
		return battery;
	}

	// all sandwiches that are in inventory can be  
	public void foundSandwich(int x) {
		if (health < 50) {
			health += (x*10);
		}
		else {
			this.sandwiches += x;
		}
		//System.out.println("Found sandwich: " + this.getSandwiches());
	}
	
	public void eatSandwich(){
		if (sandwiches > 0){
			sandwiches--;
			health += 10;
			if (health > 100){
				health = 100;
			}
		}
	}

	public void useHealth(){
		this.healthPack--;
		this.health += 25;
		if (health > 100){
			health = 100;
		}
	}
	
	public int getSandwiches() {
		return sandwiches;
	}
	
	public boolean getGun()
	{
		return gun;
	}
	
	public boolean getMoving()
	{
		return moving;
	}
}
