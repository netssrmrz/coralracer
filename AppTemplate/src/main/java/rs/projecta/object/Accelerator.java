package rs.projecta.object;

public class Accelerator
implements
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Can_Collide,
  rs.projecta.object.features.Has_Direction
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World world;
  public rs.projecta.ogl.shapes.Fast_Forward draw_shape;
  public float x, y, a_degrees;
  public rs.projecta.ogl.Color color;
  public static final float size=75;

  public Accelerator(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    org.jbox2d.collision.shapes.CircleShape shape;
    
    this.world=world;
    this.x = x;
    this.y = y;
    this.a_degrees = a_degrees;

    shape=new org.jbox2d.collision.shapes.CircleShape();
    shape.setRadius(this.world.To_Phys_Dim(size));
    this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 1, true, this);
  
    this.draw_shape = new rs.projecta.ogl.shapes.Fast_Forward();
    this.color = new rs.projecta.ogl.Color(0xff00ff00);
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(this.color, this.draw_shape);
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
  
    player = (rs.projecta.object.Player)
               rs.projecta.world.World.Get_Contact_Object_If_Type(c, rs.projecta.object.Player.class);
    if (player != null)
    {
      player.Add_Cmd(rs.projecta.object.cmds.Cmd.TYPE_TURN_TO, this.body.getAngle());
      player.Add_Cmd(rs.projecta.object.cmds.Cmd.TYPE_PUSH, 5000f);
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
