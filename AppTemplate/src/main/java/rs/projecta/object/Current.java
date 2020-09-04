package rs.projecta.object;

public class Current
implements
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Can_Collide,
  rs.projecta.object.features.Has_Direction,
    rs.projecta.object.features.Has_Update
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World world;
  public rs.projecta.ogl.shapes.Rectangle draw_shape;
  public float x, y, sx, sy, a_degrees;
  public rs.projecta.ogl.Color color;
  public boolean update_player;
  public rs.projecta.object.Player player;

  public Current(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    org.jbox2d.collision.shapes.PolygonShape shape;
    
    this.world=world;
    this.x = x;
    this.y = y;
    this.sx = sx;
    this.sy = sy;
    this.a_degrees = a_degrees;
  
    shape=new org.jbox2d.collision.shapes.PolygonShape();
    shape.setAsBox(
      this.world.To_Phys_Dim(sx),
      this.world.To_Phys_Dim(sy),
      new org.jbox2d.common.Vec2(0, 0),
      0);
    this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 1, true, this);
    //this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 2, false, org.jbox2d.dynamics.BodyType.STATIC, this);
  
    this.draw_shape = new rs.projecta.ogl.shapes.Rectangle();
    this.color = new rs.projecta.ogl.Color(0xff00ff00);
    
    this.update_player = false;
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    ctx.Draw(sx, sy,0, 0, 0, this.color, this.draw_shape, 0);
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
  
  public void Contact(org.jbox2d.dynamics.contacts.Contact c, boolean is_Start)
  {
    if (is_Start)
    {
      //android.util.Log.d("Fn Contact", "has contact");
      this.player = rs.projecta.world.World.Get_Player_Contact(c);
      if (this.player != null)
      {
        this.update_player = true;
        //android.util.Log.d("Fn Contact", "contact is player");
      }
    }
    else
    {
      this.update_player = false;
      //android.util.Log.d("Fn Contact", "has lost contact");
    }
  }
  
  public float Get_Angle_Degrees()
  {
    return this.a_degrees;
  }
  
  public void Set_Angle_Degrees(float a)
  {
  }
  
  public void Update(float dt)
  {
    if (this.update_player)
    {
      org.jbox2d.common.Vec2 current_vec = new org.jbox2d.common.Vec2(0, 400f);
      org.jbox2d.common.Vec2 force_vec = this.body.getWorldVector(current_vec);
      this.player.body.applyForceToCenter(force_vec);
      
      //android.util.Log.d("Fn Update", "player updated");
    }
  }
}
