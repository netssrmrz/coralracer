package rs.projecta.activity;

public class Play_Activity 
extends android.app.Activity
implements 
  rs.projecta.world.World_Step_Listener,
  android.view.View.OnTouchListener,
  android.view.GestureDetector.OnGestureListener
{
  public android.view.SurfaceView gfx_view;
  public rs.projecta.Tilt_Manager tilt_man;
  public rs.projecta.world.World world;
  public rs.projecta.level.Level curr_level;
  public android.widget.LinearLayout main_view;
  public android.support.v4.view.GestureDetectorCompat gesture_man;
  public boolean has_tilt_sensors;

  @Override
  public void onCreate(android.os.Bundle saved_state)
  {
    boolean supports_es2=false;
    
    super.onCreate(saved_state);

    //android.util.Log.d("Test_Activity.onCreate()", "Entered");
    this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
    this.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    this.getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.getWindow().getDecorView().setBackgroundColor(0xff000000);
    
    this.curr_level=rs.projecta.level.Level.Get(this);
  
    supports_es2 = this.Supports_ES2();
    if (supports_es2)
      this.world=new rs.projecta.world.World(this, this.curr_level, rs.projecta.world.World.HINT_ES2);
    else
      this.world=new rs.projecta.world.World(this, this.curr_level, rs.projecta.world.World.HINT_NONE);
    this.world.Set_Listener(this);
    if (supports_es2)
      this.gfx_view = new rs.projecta.view.OpenGL_View(this, this.world);
    else
      this.gfx_view = new rs.projecta.view.Canvas_View(this, this.world);
    
    com.google.android.gms.ads.AdView mAdView =
      new com.google.android.gms.ads.AdView(this);
    mAdView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
    mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111"); // sample banner ad for dev and testing
    //mAdView.setAdUnitId("ca-app-pub-7012708134579766/4040982339"); // prod
    com.google.android.gms.ads.AdRequest adRequest = 
      new com.google.android.gms.ads.AdRequest.Builder().build();
    mAdView.loadAd(adRequest);
    
    this.main_view=new android.widget.LinearLayout(this);
    this.main_view.setOrientation(android.widget.LinearLayout.VERTICAL);
    this.main_view.addView(mAdView,
      new android.widget.LinearLayout.LayoutParams(
        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
        0, 10f));
    this.main_view.addView(this.gfx_view,
      new android.widget.LinearLayout.LayoutParams(
        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
        0, 90f));
    
    this.setContentView(main_view, 
      new android.widget.LinearLayout.LayoutParams(
        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));

    this.has_tilt_sensors = rs.projecta.Tilt_Manager.Has_Tilt_Sensors(this);
    if (this.has_tilt_sensors)
    {
      this.tilt_man=new rs.projecta.Tilt_Manager(this, this.world);
      this.gesture_man = new android.support.v4.view.GestureDetectorCompat(this, this);
    }
    else
    {
      this.gfx_view.setOnTouchListener(this);
    }
  }
  
  public boolean Supports_ES2()
  {
    return ((android.app.ActivityManager)this
     .getSystemService(android.content.Context.ACTIVITY_SERVICE))
     .getDeviceConfigurationInfo().reqGlEsVersion>=0x20000;
  }
  
  @Override
  public void onResume()
  {
    super.onResume();
    
    //android.util.Log.d("onResume()", "Entered");
    ((rs.projecta.view.Game_View)this.gfx_view).onResume();
    
    if (this.tilt_man != null)
      this.tilt_man.Register();
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    //android.util.Log.d("onPause()", "Entered");
    if (this.tilt_man != null)
      this.tilt_man.Unregister();
    ((rs.projecta.view.Game_View)this.gfx_view).onPause();
  }
  
  public void On_World_State_Change(rs.projecta.world.World w, int state)
  {
    //android.util.Log.d("Play_Activity", "On_World_State_Change(): state="+state);
    android.content.Intent i;

    if (state==rs.projecta.world.World.STATE_LEVELCOMPLETE)
    {
      w.Init_Next_Level();
      //this.setResult(state);
      //this.finish();
      //android.util.Log.d("On_World_State_Change", "state==rs.projecta.world.World.STATE_LEVELCOMPLETE");
      //i=new android.content.Intent(this, rs.projecta.activity.Finish_Activity.class);
      //i.setFlags(android.content.Intent.FLAG_ACTIVITY_NO_HISTORY);
      //i.putExtra("level_class", this.curr_level.getClass().getName());
      //this.startActivity(i);
    }
  }
  
  @Override
  public boolean onTouchEvent(android.view.MotionEvent event)
  {
    boolean res = false;
  
    if (this.has_tilt_sensors)
    {
      res = this.gesture_man.onTouchEvent(event);
    }
    else
    {
      res = super.onTouchEvent(event);
    }
    
    return res;
  }
  
  public boolean onTouch(android.view.View v, android.view.MotionEvent event)
  {
    int w = v.getWidth();
    //int h = v.getHeight();
    
    float x = event.getX();
    //float y = event.getY();
    
    x = x - w/2;
    //y = y - h/2;
    
    x = x / 2000f;
  
    this.world.objs.Get_Player().Turn(x);
 
    return true;
  }
  
  @Override
  public boolean onDown(android.view.MotionEvent e)
  {
    //android.util.Log.d("onDown()", "entered");
    return false;
  }
  
  @Override
  public void onShowPress(android.view.MotionEvent e)
  {
    //android.util.Log.d("onShowPress()", "entered");
  }
  
  @Override
  public boolean onSingleTapUp(android.view.MotionEvent e)
  {
    //android.util.Log.d("onSingleTapUp()", "entered");
    return false;
  }
  
  @Override
  public boolean onScroll(android.view.MotionEvent e1, android.view.MotionEvent e2, float distanceX, float distanceY)
  {
    //android.util.Log.d("onScroll()", "entered");
    return false;
  }
  
  @Override
  public void onLongPress(android.view.MotionEvent e)
  {
    //android.util.Log.d("onLongPress()", "entered");
  }
  
  @Override
  public boolean onFling(android.view.MotionEvent e1, android.view.MotionEvent e2, float pixel_vx, float pixel_vy)
  {
    int w = this.gfx_view.getWidth();
    int h = this.gfx_view.getHeight();
    float vx = pixel_vx/w;
    float vy = pixel_vy/h;
    float factor = -500f;

    this.world.objs.Get_Player().Apply_Force(vx*factor, vy*factor);
  
    return true;
  }
}
