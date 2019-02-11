package rs.projecta.object;

public class Explosion
implements Is_Drawable, Has_Position
{
  public float cx, cy;
  public float r, r_delta, r_max;
  public rs.projecta.world.World w;
  public android.graphics.Paint p;
  // open gl
  public rs.projecta.ogl.Star star;
  
  public Explosion(rs.projecta.world.World w, float cx, float cy)
  {
    this.w=w;
    this.cx=cx;
    this.cy=cy;
    this.r_delta=3f;
    this.r_max=1500f;
    this.r=0;
    
    if (this.w.sounds!=null)
      this.w.sounds.play(this.w.soundid_whack, 1, 1, 0, 0, 1);
  
    if (this.w.hint==rs.projecta.world.World.HINT_ES2)
      Init_OpenGL(0xffff00ff);
    else
      Init_Canvas(0xffff00ff);
  }
  
  public void Init_Canvas(int col)
  {
    this.p=new android.graphics.Paint();
    this.p.setColor(col);
    this.p.setStyle(android.graphics.Paint.Style.STROKE);
    this.p.setAntiAlias(false);
    this.p.setPathEffect(new android.graphics.DiscretePathEffect(15, 90));
  }
  
  public void Init_OpenGL(int col)
  {
    this.star = new rs.projecta.ogl.Star(col);
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    this.r=this.r+this.r_delta*((float)this.w.lapsed_time/1000000f);
    if (this.r>r_max)
    {
      w.objs.Remove(this);
      w.Change_State(rs.projecta.world.World.STATE_LEVELFAIL);
    }
    else
    {
      if (this.w.hint==rs.projecta.world.World.HINT_ES2)
        this.Draw_OpenGL((rs.projecta.view.OpenGL_View)v);
      else
        this.Draw_Canvas(c);
    }
  }
  
  public void Draw_Canvas(android.graphics.Canvas c)
  {
    c.drawCircle(0, 0, this.r, p);
  }
  
  public void Draw_OpenGL(rs.projecta.view.OpenGL_View v)
  {
    this.star.Draw(v.ogl_ctx, this.r);
  }
  
  @Override
  public float Get_X()
  {
    return this.cx;
  }

  @Override
  public float Get_Y()
  {
    return this.cy;
  }

  @Override
  public void Set_X(float x)
  {
    this.cx=x;
  }

  @Override
  public void Set_Y(float y)
  {
    this.cy=y;
  }
  
  public static void Add(rs.projecta.world.World w, float cx, float cy)
  {
    if (w.objs.Get_Count(Explosion.class)<3)
      w.objs.Add(new Explosion(w, cx, cy));
  }
}
