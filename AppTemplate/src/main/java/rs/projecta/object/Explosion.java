package rs.projecta.object;

public class Explosion
implements rs.projecta.object.features.Is_Drawable, rs.projecta.object.features.Has_Position
{
  public float cx, cy;
  public float r, r_delta, r_max;
  public rs.projecta.world.World w;
  public rs.projecta.ogl.shapes.Star star;
  public rs.projecta.ogl.Color color;
  public boolean restart_on_hit;
  
  public Explosion(rs.projecta.world.World w, float cx, float cy)
  {
    this.w=w;
    this.cx=cx;
    this.cy=cy;
    this.r_delta=3f;
    this.r_max=1500f;
    this.r=0;
    this.restart_on_hit=false;
    
    if (this.w.sounds!=null)
      this.w.sounds.play(this.w.soundid_whack, 1, 1, 0, 0, 1);
  
    this.star = new rs.projecta.ogl.shapes.Star();
    this.color = new rs.projecta.ogl.Color(0xffff00ff);
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    this.r=this.r+this.r_delta*((float)this.w.lapsed_time/1000000f);
    if (this.r>r_max)
    {
      w.objs.Remove(this);
      if (this.restart_on_hit)
        w.Change_State(rs.projecta.world.World.STATE_LEVELFAIL);
    }
    else
    {
      ctx.Draw(this.r, this.r,0, 0, 0, this.color, this.star, 0);
    }
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
    Add(w, cx, cy, true);
  }
  
  public static void Add(rs.projecta.world.World w, float cx, float cy, boolean restart_on_hit)
  {
    Explosion explosion;
    
    if (w.objs.Get_Count(Explosion.class)<3)
    {
      explosion = new Explosion(w, cx, cy);
      explosion.restart_on_hit = restart_on_hit;
      w.objs.Add_Later(explosion);
    }
  }
}
