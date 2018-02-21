


public class GameView extends SurfaceView implements Runnable{

	HUD hud;
	
	private boolean playing = false;
	private boolean paused = false;

	private long fps;
	
	private SurfaceHolder ourHolder;
 
	private Canvas canvas;
    private Paint paint;
	
	private Thread gameThread = null;
 

	
	/*
	// A handy Random instance
    Random random = new Random();
 
 
 
    // How big is the world?
    // Change these for lots of fun...
    // And a slow game
    int worldWidth = 0;
    int targetWorldWidth = 500;
    int targetWorldHeight = 150;
    int groundLevel = 145;
 
    // For the returned rectangle from the viewport
    // These we will use in the main game loop, multiple times
    // Saves creating new instances each frame
    RectF convertedRect = new RectF();
    PointF convertedPointA = new PointF();
    PointF convertedPointB = new PointF();
    PointF convertedPointC = new PointF();
    PointF tempPointF = new PointF();
 
    // This is our thread

 
    // The city is built from bricks
    private Brick[] bricks = new Brick[20000];
    private int numBricks;
 
    // Twinkling stars in the sky above the city
    private Star[] stars = new Star[5000];
    private int numStars;
 
    // The player's ship
    Ship player;
 
    // The player's bullets
    private Bullet[] playerBullets = new Bullet[10];
    private int nextPlayerBullet;
    private int maxPlayerBullets = 10;
 
    // Our neat viewport/camera/clipping machine
    Viewport vp;
 
  
	*/
	
	public GameView(Context c, int screenX, int screenY){
		
	}
	
	
	private void createLevel(){
		
	}

	public void run(){
		while(playing){
			long start = System.currentTimeMillis();
			
			if(!paused){
				update();
			}
			draw();
		
			long end = System.currentTimeMillis;
			if(start-end >=1){
				//TODO: something to do with fps
				fps = 1000 / (start-end);
			}	
		}
	}
	
	private void update(){
		
	}
	
	private void draw(){
		
		if(ourHolder.getSurface().isValid()){
			
			canvas = ourHolder.lockCanvas();
			canvas.drawColor(Color.argb(255,0,0,0));

			
			
			ourHolder.unlockCanvasAndPost(canvas);
		}
	}
	
	
	public void pause(){
		playing = false;
		
		try{
			gameThread.join();
		}catch(InterruptedException e){
			Log.e("error joing thread");
		}
	}
	
	public void resume(){
		playing = true;
		gameThread = nre Thread(this);
		gameThread.start();		
	}
	
	public boolean onTouchEvent(MotionEvent me){
		hud.handleInput(me);
		return true;
	}
	
	
	
	class HUD{
		
		Rect left, right, thrust, shoot, pause;
		
		public ArrayList<Rect> buttons = new ArrayList<>();
		
		
		public HUD(int width, int height){
			
			int buttonWidth = width/8;
			int buttonHeight = height/7;
			int padding = width/80;

			left = new Rect(padding, height- buttonHeight-padding, buttonWidth, height-padding);
			right = new Rect(buttonWidth + padding, height- buttonHeight-padding, buttonWidth+padding+buttonWidth, height-padding);
			thrust = new Rect(width -buttonWidth-padding, height - buttonHeight*2 - padding*2, width-padding, height- padding*2-buttonHeight);
			shoot = new Rect(width - buttonWidth-padding, height-buttonHeight-padding, width-padding, height-padding);
			pause = new Rect(width - padding - buttonWidth, padding, width-padding, padding+buttonHeight);
			
			buttons.add(left);
			buttons.add(right);
			buttons.add(thrust);
			buttons.add(shoot);
			buttons.add(pause);
					
		}
		
		public void handleInput(MotionEvent me){
			
			int x = (int) me.getX(0);
			int y = (int) me.getY(0);
			
			
			switch(me.getAction() & MotionEvent.ACTION_MASK){//may be able to just use getActionMasked()
				case MotionEvent.ACTION_DOWN:
					if(left.contains(x,y)){
						//go left
					}
					else if(right.contains(x,y)){
						//go right 
					}
					else if(thrust.contains(x,y)){
						//move forward
					}
					else if(shoot.contains(x,y)){
						//shoot
					}	
					else if(pause.contains(x,y)){
						//pause
					}
				
				case MotionEvent.ACTION_UP:
					//stop
					break;
					
			}
		}
	}
	

}