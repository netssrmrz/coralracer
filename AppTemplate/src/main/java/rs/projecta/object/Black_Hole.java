package rs.projecta.object;

public class Black_Hole
implements
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Has_Auto_Movement
{
  public float x, y;
  public rs.projecta.ogl.shapes.Swirl swirl;
  public rs.projecta.object.Player player;
  public final float effect_dist = 1000;
  public final float max_force = 140;
  public rs.projecta.ogl.Color color;
  
  public Black_Hole(rs.projecta.world.World w, float x, float y, float dx, float dy, float a_degrees)
  {
    this.x = x;
    this.y = y;
    this.swirl = new rs.projecta.ogl.shapes.Swirl();
    this.player = w.objs.Get_Player();
    this.color = new rs.projecta.ogl.Color(0xffff0000);
  }
  
  @Override
  public float Get_X()
  {
    return this.x;
  }
  
  @Override
  public float Get_Y()
  {
    return this.y;
  }
  
  @Override
  public void Set_X(float x)
  {
    this.x = x;
  }
  
  @Override
  public void Set_Y(float y)
  {
    this.y = y;
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(50f, -50f,0, 0, 0, this.color, this.swirl, 0);
  }
  
  @Override
  public void Update(float dt)
  {
    org.jbox2d.common.Vec2 f;
    float distance, f_strength;
    
    f = new org.jbox2d.common.Vec2();
    f.x = this.x - this.player.Get_X();
    f.y = this.y - this.player.Get_Y();
    distance = f.normalize();
    if (distance<effect_dist)
    {
      f_strength = -(max_force/effect_dist)*(distance-effect_dist);
      f.mulLocal(f_strength);
      //this.player.body.applyLinearImpulse(f, this.player.body.getWorldCenter());
      this.player.body.applyForceToCenter(f);
    }
  }
}
