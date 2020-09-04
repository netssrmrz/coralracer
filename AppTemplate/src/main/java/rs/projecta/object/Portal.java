package rs.projecta.object;

public class Portal
implements
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Can_Collide,
  rs.projecta.object.features.Has_Update
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World world;
  public rs.projecta.ogl.shapes.Arrow arrow;
  public float x, y;
  public rs.projecta.ogl.Color color;
  public float to_x, to_y;
  public rs.projecta.object.Player contact_player;
  
  public Portal(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    this.world=world;
    this.x = x;
    this.y = y;
    this.arrow = new rs.projecta.ogl.shapes.Arrow();
    this.color = new rs.projecta.ogl.Color(0x00ff00ff);
  
    org.jbox2d.collision.shapes.CircleShape shape=new org.jbox2d.collision.shapes.CircleShape();
    shape.setRadius(this.world.To_Phys_Dim(100));
    this.body = this.world.Add_Single_Fixture_Body(shape, x, y, a_degrees, 1, true, this);
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
    
    ctx.Draw(50f, 50f,0, 70, 0, this.color, this.arrow, 0);
    ctx.Draw(50f, 50f,90, 0, 70, this.color, this.arrow, 0);
    ctx.Draw(50f, 50f,180, -70, 0, this.color, this.arrow, 0);
    ctx.Draw(50f, 50f,270, 0, -70, this.color, this.arrow, 0);
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
  
  public void Contact(org.jbox2d.dynamics.contacts.Contact c, boolean is_start)
  {
    if (is_start)
    {
      this.contact_player = rs.projecta.world.World.Get_Player_Contact(c);
    }
  }
  
  public void Update(float dt)
  {
    if (this.contact_player != null)
    {
      this.contact_player.Set_X(this.to_x);
      this.contact_player.Set_Y(this.to_y);
      
      this.contact_player = null;
    }
  }
}
