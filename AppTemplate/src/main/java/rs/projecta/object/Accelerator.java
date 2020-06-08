package rs.projecta.object;

public class Accelerator
implements rs.projecta.object.features.Has_Position, rs.projecta.object.features.Is_Drawable, rs.projecta.object.features.Can_Collide, rs.projecta.object.features.Has_Direction
{
  public rs.projecta.world.World world;
  public org.jbox2d.dynamics.Body body;
  public float x, y, a_degrees;
  public rs.projecta.ogl.shapes.Arrow arrow;
  public static final float size=50;
  public rs.projecta.ogl.Color color;

  public Accelerator(rs.projecta.world.World world, float x, float y, float a_degrees)
  {
    org.jbox2d.collision.shapes.CircleShape shape;
    
    this.world=world;
    this.x = x;
    this.y = y;
    this.a_degrees = a_degrees;

    shape=new org.jbox2d.collision.shapes.CircleShape();
    shape.setRadius(this.world.To_Phys_Dim(size));
    //this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 1, true, this);
  
    this.arrow = new rs.projecta.ogl.shapes.Arrow();
    this.color = new rs.projecta.ogl.Color(0xff00ff00);
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(50f, 50f,0, 0, 0, this.color, this.arrow, 0);
  }

  public float Get_X()
  {
    return this.x;
  }

  public float Get_Y()
  {
    return this.y;
  }
  
  public void Set_X(float x)
  {
  }

  public void Set_Y(float y)
  {
  }
  
  public void Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    Player player;
    org.jbox2d.common.Vec2 imp;
    float a_radians;
    android.util.Log.d("Accelerator", "Contact()");
  
    player = (rs.projecta.object.Player)rs.projecta.world.World.Get_Contact_Object_If_Type(c, rs.projecta.object.Player.class);
    if (player != null)
    {
      player.suspend_secs = 0.1f;
      // set player direction
      player.Set_Angle_Degrees(this.a_degrees);
      // add impulse
      a_radians = a_degrees * org.jbox2d.common.MathUtils.DEG2RAD;
      imp = new org.jbox2d.common.Vec2(org.jbox2d.common.MathUtils.cos(a_radians), org.jbox2d.common.MathUtils.sin(a_radians));
      imp.mulLocal(10);
      player.body.applyLinearImpulse(imp, player.body.getWorldCenter());
    }
  }
  
  public float Get_Angle_Degrees()
  {
    return this.a_degrees;
  }
  
  public void Set_Angle_Degrees(float a)
  {
  
  }
}
