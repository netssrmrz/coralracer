package rs.projecta.object;

public class Player
implements
  Is_Drawable, Has_Position, Has_Direction, Can_Collide, Has_Auto_Movement
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World w;
  public float frame, frame_delta, frame_max;
  public final float size=24f;
  public final float trgt_v=60; // max velocity
  public final float torque_factor=40; // turn rate
  public float suspend_secs;
  public rs.projecta.ogl.Fish1 fish1;
  
  public Player(float x, float y, rs.projecta.world.World world)
  {
    org.jbox2d.dynamics.BodyDef body_def;
    org.jbox2d.dynamics.FixtureDef fix_def;
    
    this.w = world;
    this.suspend_secs = 0;
  
    body_def = new org.jbox2d.dynamics.BodyDef();
    body_def.type = org.jbox2d.dynamics.BodyType.DYNAMIC;
    body_def.position = new org.jbox2d.common.Vec2(x / this.w.phys_scale, y / this.w.phys_scale);
    body_def.angle = 0;
    body_def.userData = this;
    body = world.phys_world.createBody(body_def);
    
    fix_def = new org.jbox2d.dynamics.FixtureDef();
    fix_def.shape = new org.jbox2d.collision.shapes.CircleShape();
    fix_def.shape.setRadius(this.size / this.w.phys_scale);
    fix_def.density = 0.08f;
    fix_def.friction = 0;
    fix_def.restitution = 1;
    fix_def.filter.groupIndex=-1;
    body.createFixture(fix_def);
    
    this.frame=0;
    this.frame_delta=.01f;
    this.frame_max=4;
    
    if (this.w.sounds!=null)
      this.w.sounds.play(this.w.soundid_start, 1, 1, 0, 0, 1);
  
    this.fish1 = new rs.projecta.ogl.Fish1(0xffffffff);
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    this.frame=this.frame+this.frame_delta*((float)this.w.lapsed_time/1000000f);
    this.frame=this.frame%this.frame_max;
    this.fish1.Draw(((rs.projecta.view.OpenGL_View)v).ogl_ctx, 4, (int)this.frame);
  }
  
	public float Get_X()
	{
		return this.body.getPosition().x * this.w.phys_scale;
	}

	public float Get_Y()
	{
		return this.body.getPosition().y * this.w.phys_scale;
	}

  public void Set_X(float x)
  {
    rs.projecta.Util.Set_Transform(this.w, this.body, x, null, null);
  }

  public void Set_Y(float y)
  {
    rs.projecta.Util.Set_Transform(this.w, this.body, null, y, null);
  }

	public float Get_Angle_Degrees()
	{
		return (float)java.lang.Math.toDegrees(this.body.getAngle());
	}

  public void Set_Angle_Degrees(float a)
  {
    rs.projecta.Util.Set_Transform(this.w, this.body, null, null, a);
  }

  public void User_Action(float f, float t)
  {
  }

	public void Turn(float tilt)
	{
    float curr_v, trgt_v, f=0;

    curr_v = this.body.getAngularVelocity();
    trgt_v = tilt * torque_factor;
    f = trgt_v - curr_v;
    this.body.applyTorque(f);
	}

  public void Accelerate(float tilt)
  {
    /*String str;
    float curr_v, trgt_v, f=0;

    Remove_Lat_Vel(this.body);
    
    curr_v=this.body.getLinearVelocity().length();
    trgt_v=100f;
    if (curr_v<trgt_v)
    {
      f=tilt*100f;
      Apply_Frwd_Force(this.body, f);
    }

    if (this.world.debug)
    {
      str =
        "\nForward Tilt: "+tilt+"\n"+
        "Current Velocity: " + curr_v + "\n" +
        "Target Velocity: " + trgt_v + "\n"+
        "Force: "+f;
      this.world.debug_msg += str;
    }*/
  }
  
  public void Apply_Force(org.jbox2d.common.Vec2 f)
  {
    body.applyForceToCenter(f);
  }
  
  public void Apply_Frwd_Force(float f)
  {
    org.jbox2d.common.Vec2 frwd_vec, body_frwd_vec;
    
    frwd_vec = new org.jbox2d.common.Vec2(0, -f);
    body_frwd_vec = this.body.getWorldVector(frwd_vec);
    body.applyForceToCenter(body_frwd_vec);
  }
  
  public void Remove_Lat_Vel(org.jbox2d.dynamics.Body body)
  {
    org.jbox2d.common.Vec2 lat_vel, lat_impulse;
    
    lat_vel = Calc_Lat_Vel(body);
    lat_impulse = lat_vel.mul(-body.getMass());
    body.applyLinearImpulse(lat_impulse, body.getWorldCenter());
  }
  
  public org.jbox2d.common.Vec2 Calc_Lat_Vel(org.jbox2d.dynamics.Body body)
  {
    org.jbox2d.common.Vec2 res=null;
    org.jbox2d.common.Vec2 lat_vec, vel;
    float lat_vel;

    vel = this.body.getLinearVelocity();
    lat_vec = this.body.getWorldVector(new org.jbox2d.common.Vec2(1, 0));
    lat_vel = org.jbox2d.common.Vec2.dot(lat_vec, vel);
    res = lat_vec.mul(lat_vel);

    return res;
  }

  public void Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    this.suspend_secs = 0.2f;
  }
  
  public void Update(float sec_step)
  {
    org.jbox2d.common.Vec2 curr_abs_vel, req_rel_vel, req_abs_vel, diff_abs_vel, impulse;
    
    if (this.suspend_secs <= 0)
    {
      curr_abs_vel = this.body.getLinearVelocity();
  
      req_rel_vel = new org.jbox2d.common.Vec2(0, -trgt_v);
      req_abs_vel = this.body.getWorldVector(req_rel_vel);
  
      diff_abs_vel = req_abs_vel.sub(curr_abs_vel);
      impulse = diff_abs_vel.mul(this.body.getMass());
  
      this.body.applyLinearImpulse(impulse, this.body.getWorldCenter());
    }
    else
      this.suspend_secs -= sec_step;
  }
}
