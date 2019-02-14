package rs.projecta.activity;

public class Play_Activity 
extends android.app.Activity
implements 
  rs.projecta.world.World_Step_Listener
{
  public android.view.SurfaceView gfx_view;
  public rs.projecta.Tilt_Manager tilt_man;
  public rs.projecta.world.World world;
  public rs.projecta.level.Level curr_level;
  public android.widget.LinearLayout main_view;

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
    this.tilt_man=new rs.projecta.Tilt_Manager(this, this.world);
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
    this.tilt_man.Register();
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    //android.util.Log.d("onPause()", "Entered");
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
}
