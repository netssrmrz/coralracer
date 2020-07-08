package rs.projecta.object;

public class Finish
implements
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Can_Collide
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World world;
  public rs.projecta.ogl.shapes.Arrow arrow;
  public float x, y;
  public rs.projecta.ogl.Color color;

  public Finish(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    org.jbox2d.dynamics.BodyDef body_def;
    org.jbox2d.dynamics.FixtureDef fix_def;
    org.jbox2d.collision.shapes.CircleShape shape;
    
    this.world=world;
    this.x = x;
    this.y = y;

    body_def=new org.jbox2d.dynamics.BodyDef();
    body_def.type=org.jbox2d.dynamics.BodyType.DYNAMIC;
    body_def.position=new org.jbox2d.common.Vec2(x/this.world.phys_scale, y/this.world.phys_scale);
    body_def.angle=0;
    body_def.userData=this;
    body=world.phys_world.createBody(body_def);
  
    shape=new org.jbox2d.collision.shapes.CircleShape();
    shape.setRadius(50f/this.world.phys_scale);
    fix_def=new org.jbox2d.dynamics.FixtureDef();
    fix_def.shape=shape;
    fix_def.density=1;
    fix_def.friction=0;
    fix_def.restitution=1;
    fix_def.isSensor=true;
    body.createFixture(fix_def);
    //this.body = this.world.Add_Single_Fixture_Body(shape, x, y, 0, 1, true);
  
    this.arrow = new rs.projecta.ogl.shapes.Arrow();
    this.color = new rs.projecta.ogl.Color(0x00ff00ff);
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
    return rs.projecta.Util.Get_Transform(this.world, this.body, rs.projecta.Util.TID_X);
  }

  public float Get_Y()
  {
    return rs.projecta.Util.Get_Transform(this.world, this.body, rs.projecta.Util.TID_Y);
  }
  
  public void Set_X(float x)
  {
    rs.projecta.Util.Set_Transform(this.world, this.body, x, null, null);
  }

  public void Set_Y(float y)
  {
    rs.projecta.Util.Set_Transform(this.world, this.body, null, y, null);
  }
  
  public void Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    rs.projecta.object.Player player;
    
    player = (Player)rs.projecta.world.World.Get_Contact_Object_If_Type(c, Player.class);
    if (player != null)
    {
      this.world.Change_State(rs.projecta.world.World.STATE_LEVELCOMPLETE);
    }
  }
}
