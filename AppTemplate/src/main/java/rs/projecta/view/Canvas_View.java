package rs.projecta.view;

public class Canvas_View
extends android.view.SurfaceView
implements 
  android.view.SurfaceHolder.Callback,
  java.lang.Runnable,
  rs.projecta.world.World_Step_Listener,
  rs.projecta.view.Game_View
{
  public android.view.SurfaceHolder surface;
  public rs.projecta.world.World world;
  public Thread game_loop;
  public android.graphics.Paint p;
  public float scale;
  public rs.projecta.Debug_Renderer debug_renderer;
  public Object camera;

  public Canvas_View(android.content.Context ctx, rs.projecta.world.World world)
  {
    super(ctx);

    world.Set_Listener(this);
    this.scale=0;
  
    this.p = new android.graphics.Paint();
    this.p.setColor(0xffffffff);
    this.p.setTextSize(40f);

    this.Init(world);
  }
  
  public void Init(rs.projecta.world.World w)
  {
    this.world = w;
    //this.camera = w.objs.Get_Player();
    
    if (this.world.debug)
    {
      this.debug_renderer = new rs.projecta.Debug_Renderer(this.world.phys_scale);
      this.world.phys_world.setDebugDraw(this.debug_renderer);
    }
  }
  
  @Override
  public void surfaceCreated(android.view.SurfaceHolder s)
  {
    //android.util.Log.d("surfaceCreated()", "Entered");
    this.surface = s;
  }
  
  @Override
  public void surfaceChanged(android.view.SurfaceHolder s, int format, int w, int h)
  {
  }
  
  @Override
  public void surfaceDestroyed(android.view.SurfaceHolder s)
  {
    //android.util.Log.d("surfaceDestroyed()", "Entered");
    this.surface = null;
  }
  
  public void onDrawFrame(android.graphics.Canvas c)
  {
    //android.util.Log.d("onDraw()", "Entry");
    
    if (this.world.debug)
    {
      this.world.debug_msg[0]="";
      this.world.debug_msg[0]+="c.getWidth(): "+c.getWidth()+"\n";
      this.world.debug_msg[0]+="c.getHeight(): "+c.getHeight()+"\n";
    }
    
    if (this.scale==0)
    {
      this.scale=0.0003f*(c.getWidth()+c.getHeight())+0.0928f;
    }
    
    c.save();
    
    c.translate((float)c.getWidth() / 2f, (float)c.getHeight() / 2f);
    c.scale(this.scale, this.scale);
    if (this.camera instanceof rs.projecta.object.Has_Direction)
      c.rotate(-((rs.projecta.object.Has_Direction)this.camera).Get_Angle_Degrees());
    if (this.camera instanceof rs.projecta.object.Has_Position)
      c.translate(
        -((rs.projecta.object.Has_Position)this.camera).Get_X(),
        -((rs.projecta.object.Has_Position)this.camera).Get_Y());
    
    c.drawColor(0xff000022);
    
    this.world.objs.Draw(this, c);
    
    if (this.debug_renderer!=null)
    {
      this.debug_renderer.canvas=c;
      this.world.phys_world.drawDebugData();
    }
    
    c.restore();
    
    if (this.world.debug)
      this.Draw_Console(c);
  }
  
  public void onResume()
  {
    //android.util.Log.d("onResume()", "Entered");
    this.world.Init_Sound();

    if (this.game_loop == null || !this.game_loop.isAlive())
    {
      this.game_loop = new Thread(this);
      this.game_loop.start();
    }
  }
  
  public void onPause()
  {
    //android.util.Log.d("onPause()", "Entered");
    this.world.sounds.release();
    this.world.sounds = null;

    if (this.game_loop != null && this.game_loop.isAlive())
    {
      this.world.do_processing = false;
      try
      {this.game_loop.join();}
      catch (java.lang.Exception e)
      {}
      this.game_loop=null;
    }
  }

  public Object Get_Camera()
  {
    return camera;
  }

  // ===================================================================================
  
  @Override
  public void onDraw(android.graphics.Canvas c)
  {
    this.onDrawFrame(c);
  }
  
  public void On_World_State_Change(rs.projecta.world.World w, int state)
  {
    android.graphics.Canvas c;

    if (state==rs.projecta.world.World.STATE_INIT)
    {
      this.camera = w.objs.Get_Player();
    }
    else if (state==rs.projecta.world.World.STATE_STEP)
    {
      this.surface = this.getHolder();
      if (this.surface != null)
      {
        try
        {
          c = this.surface.lockCanvas();
        } catch (java.lang.Exception e)
        {
          c = null;
        }
        if (c != null)
        {
          this.onDrawFrame(c);
          this.getHolder().unlockCanvasAndPost(c);
        }
      }
    }
	}
  
  public void run()
  {
    //android.util.Log.d("Game_View", "run()");
    this.world.Do_Processing();
    /*this.game_loop = null;
    if (this.world.state==rs.projecta.world.World.STATE_LEVELFAIL)
    {
      this.world.Init_Level();
      this.Start_Loop();
    }*/
  }
  
  public float Get_Camera_X()
  {
    float res=0;
    
    if (this.camera!=null && this.camera instanceof rs.projecta.object.Has_Position)
    {
      res=((rs.projecta.object.Has_Position)this.camera).Get_X();
    }
    return res;
  }
  
  public float Get_Camera_Y()
  {
    float res=0;
    
    if (this.camera!=null && this.camera instanceof rs.projecta.object.Has_Position)
    {
      res=((rs.projecta.object.Has_Position)this.camera).Get_Y();
    }
    return res;
  }
  
  public void Draw_Console(android.graphics.Canvas c)
  {
    String[] lines;
    int l;
    
    if (rs.android.Util.NotEmpty(this.world.debug_msg))
    {
      l=0;
      for (String msg: this.world.debug_msg)
      {
        if (msg!=null)
        {
          lines = msg.split("\n");
          for (String line: lines)
          {
            c.drawText(line, 5, l * p.getTextSize() + 25, p);
            l++;
          }
        }
      }
    }
  }
}
