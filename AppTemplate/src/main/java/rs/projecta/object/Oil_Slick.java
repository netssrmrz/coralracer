package rs.projecta.object;

public class Oil_Slick
implements
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Can_Collide,
  rs.projecta.object.features.Has_Direction,
  rs.projecta.object.features.Has_Auto_Movement
{
  public float x, y, a_degrees;
  public rs.projecta.ogl.Color color;
  public rs.projecta.ogl.shapes.Blob draw_shape;
  public rs.projecta.world.World world;
  public org.jbox2d.dynamics.Body body;
  
  public Oil_Slick(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    this.x = x;
    this.y = y;
    this.a_degrees = a_degrees;
    this.draw_shape = new rs.projecta.ogl.shapes.Blob();
    this.color = new rs.projecta.ogl.Color(0xff888888);
  
    this.world=world;
    org.jbox2d.collision.shapes.CircleShape shape=new org.jbox2d.collision.shapes.CircleShape();
    shape.setRadius(this.world.To_Phys_Dim(100));
    this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 1, true, this);
  }
  
  @Override
  public void Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    Player player;
  
    player = rs.projecta.world.World.Get_Player_Contact(c);
    if (player != null)
    {
    }
  }
  
  @Override
  public float Get_Angle_Degrees()
  {
    return this.a_degrees;
  }
  
  @Override
  public void Set_Angle_Degrees(float a)
  {
  
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
  }
  
  @Override
  public void Set_Y(float y)
  {
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(this.color, this.draw_shape);
  }
  
  @Override
  public void Update(float dt)
  {
  
  }
}
