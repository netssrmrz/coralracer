package rs.projecta.object;

public class Player
implements
  rs.projecta.object.features.Is_Drawable,
  rs.projecta.object.features.Has_Position,
  rs.projecta.object.features.Has_Direction,
  rs.projecta.object.features.Can_Collide,
  rs.projecta.object.features.Has_Auto_Movement
{
  public org.jbox2d.dynamics.Body body;
  public rs.projecta.world.World w;
  public float frame, frame_delta, frame_max;
  public final float size=24f;
  public final float trgt_v=60; // max velocity
  public final float torque_factor=40; // turn rate
  public float suspend_secs;
  public rs.projecta.ogl.shapes.Fish1 fish1;
  public rs.projecta.ogl.Color color;
  public java.util.ArrayList<rs.projecta.object.cmds.Cmd> cmds;
  
  public Player(rs.projecta.world.World world, float x, float y, float sx, float sy, float a_degrees)
  {
    org.jbox2d.dynamics.BodyDef body_def;
    org.jbox2d.dynamics.FixtureDef fix_def;
    
    this.cmds = new java.util.ArrayList<rs.projecta.object.cmds.Cmd>();
    this.w = world;
    this.suspend_secs = 0;
  
    body_def = new org.jbox2d.dynamics.BodyDef();
    body_def.type = org.jbox2d.dynamics.BodyType.DYNAMIC;
    body_def.position = new org.jbox2d.common.Vec2(x / this.w.phys_scale, y / this.w.phys_scale);
    body_def.angle = (float)java.lang.Math.toRadians(a_degrees);
    body_def.userData = this;
    body = world.phys_world.createBody(body_def);
    
    fix_def = new org.jbox2d.dynamics.FixtureDef();
    fix_def.shape = new org.jbox2d.collision.shapes.CircleShape();
    fix_def.shape.setRadius(this.size / this.w.phys_scale);
    fix_def.density = 0.05f;
    fix_def.friction = 0;
    fix_def.restitution = 1;
    fix_def.filter.groupIndex=-1;
    body.createFixture(fix_def);
    
    this.frame=0;
    this.frame_delta=.01f;
    this.frame_max=4;
    
    if (this.w.sounds!=null)
      this.w.sounds.play(this.w.soundid_start, 1, 1, 0, 0, 1);
  
    this.fish1 = new rs.projecta.ogl.shapes.Fish1();
    this.color = new rs.projecta.ogl.Color(0xffffffff);
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    rs.projecta.ogl.Context ctx = ((rs.projecta.view.OpenGL_View)v).ogl_ctx;
  
    this.frame=this.frame+this.frame_delta*((float)this.w.lapsed_time/1000000f);
    this.frame=this.frame%this.frame_max;
    ctx.Draw(4f, -4f,0, 0, 0, this.color, this.fish1, (int)this.frame);
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

  public void xUser_Action(float f, float t)
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

  public void xAccelerate(float tilt)
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
  
  public void xApply_Force(org.jbox2d.common.Vec2 f)
  {
    body.applyForceToCenter(f);
  }
  
  public void Apply_Frwd_Force(float f)
  {
    org.jbox2d.common.Vec2 frwd_vec, body_frwd_vec;
    
    frwd_vec = new org.jbox2d.common.Vec2(0, f);
    body_frwd_vec = this.body.getWorldVector(frwd_vec);
    body.applyForceToCenter(body_frwd_vec);
  }
  
  public void xRemove_Lat_Vel(org.jbox2d.dynamics.Body body)
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
      req_rel_vel = new org.jbox2d.common.Vec2(0, trgt_v);
      req_abs_vel = this.body.getWorldVector(req_rel_vel);
      diff_abs_vel = req_abs_vel.sub(curr_abs_vel);
      
      //impulse = diff_abs_vel.mul(this.body.getMass());
      //this.body.applyLinearImpulse(impulse, this.body.getWorldCenter());
  
      diff_abs_vel = diff_abs_vel.mul(2f);
      this.body.applyForceToCenter(diff_abs_vel);
    }
    else
      this.suspend_secs -= sec_step;
    
    this.Process_Cmds();
  }
  
  public void Force_To(float x, float y)
  {
    org.jbox2d.common.Vec2 force;
    
    force = new org.jbox2d.common.Vec2(x, y);
    this.body.applyForceToCenter(force);
  }
  
  public void Add_Cmd(int type, float val)
  {
    rs.projecta.object.cmds.Cmd cmd;
    
    cmd = new rs.projecta.object.cmds.Cmd(type, val);
    this.cmds.add(cmd);
  }
  
  public void Process_Cmds()
  {
    if (this.cmds != null && this.cmds.size() > 0)
    {
      for (rs.projecta.object.cmds.Cmd cmd: this.cmds)
      {
        if (cmd.type == rs.projecta.object.cmds.Cmd.TYPE_TURN_TO)
        {
          body.setAngularVelocity(0);
          body.setTransform(body.getPosition(), (float)cmd.val);
        }
        else if (cmd.type == rs.projecta.object.cmds.Cmd.TYPE_PUSH)
        {
          this.body.setLinearVelocity(new org.jbox2d.common.Vec2(0, 0));
          this.Apply_Frwd_Force(cmd.val);
        }
      }
      this.cmds.clear();
    }
  }
}
