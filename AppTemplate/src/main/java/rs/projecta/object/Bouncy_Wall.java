package rs.projecta.object;

public class Bouncy_Wall
implements rs.projecta.object.features.Is_Drawable, rs.projecta.object.features.Has_Position, rs.projecta.object.features.Has_Direction, rs.projecta.object.features.Has_Cleanup
{
  public rs.projecta.world.World world;
  public org.jbox2d.dynamics.Body body;
  public float x, y, a_degrees;
  public rs.projecta.ogl.shapes.Rectangle rect;
  public float dx, dy;
  public rs.projecta.ogl.Color color;
  
  public Bouncy_Wall(rs.projecta.world.World world, float x, float y, float dx, float dy, float a_degrees)
  {
    org.jbox2d.collision.shapes.PolygonShape shape;
  
    this.world=world;
    this.x = x;
    this.y = y;
    this.a_degrees = a_degrees; this.dx = dx; this.dy = dy;
    
    shape=new org.jbox2d.collision.shapes.PolygonShape();
    shape.setAsBox(this.world.To_Phys_Dim(dx), this.world.To_Phys_Dim(dy), new org.jbox2d.common.Vec2(0, 0), 0);
    this.body = this.world.Add_Single_Fixture_Body
      (shape, x, y, a_degrees, 2, false, org.jbox2d.dynamics.BodyType.STATIC, this);
  
    this.rect=new rs.projecta.ogl.shapes.Rectangle();
    this.color = new rs.projecta.ogl.Color(0xff00ff00);
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(dx, dy,0, 0, 0, this.color, this.rect, 0);
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
  
  public float Get_Angle_Degrees()
  {
    return this.a_degrees;
  }

  public void Set_Angle_Degrees(float a)
  {
  }

  public void Remove()
  {
    this.world.phys_world.destroyBody(this.body);
  }
}
