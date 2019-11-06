package rs.projecta.object;

public class Wall
implements Is_Drawable, Has_Position, Has_Direction, Has_Cleanup, Can_Collide
{
  public org.jbox2d.dynamics.Body body;
  public android.graphics.Paint p;
  public rs.projecta.world.World world;
  public float x1, y1, x2, y2;
  // open gl
  public java.nio.FloatBuffer b;
  public int pt_count;
  public float red, green, blue, alpha;
  
  public Wall(rs.projecta.world.World world, float x, float y, float rx, float ry, float a_degrees)
  {
    org.jbox2d.collision.shapes.PolygonShape shape;
  
    this.world=world;
  
    shape=new org.jbox2d.collision.shapes.PolygonShape();
    shape.setAsBox(this.world.To_Phys_Dim(rx), this.world.To_Phys_Dim(ry), new org.jbox2d.common.Vec2(0, 0), 0);
    this.body = this.world.Add_Single_Fixture_Body
      (shape, x, y, a_degrees, 2, false, org.jbox2d.dynamics.BodyType.STATIC, this);
  
    Init_OpenGL(rx, ry);
  }
  
  public void Init_Phys(org.jbox2d.common.Vec2 pos, float rx, float ry, float a_degrees)
  {
    org.jbox2d.collision.shapes.PolygonShape shape;
  
    shape=new org.jbox2d.collision.shapes.PolygonShape();
    shape.setAsBox(this.world.To_Phys_Dim(rx), this.world.To_Phys_Dim(ry), new org.jbox2d.common.Vec2(0, 0), 0);
    this.body = this.world.Add_Single_Fixture_Body
                 (shape, pos.x, pos.y, a_degrees, 2, false, org.jbox2d.dynamics.BodyType.STATIC, this);
  }
  
  public void Init_Canvas(float rx, float ry)
  {
    int col1;
    
    x1=-rx;
    y1=-ry;
    x2=rx;
    y2=ry;
    
    this.p = new android.graphics.Paint();
    this.p.setStyle(android.graphics.Paint.Style.STROKE);
    this.p.setStrokeWidth(2);
    this.p.setColor(0xffffff00);
    if (!this.world.debug)
      this.p.setPathEffect(new android.graphics.DiscretePathEffect(10, 10));
    this.p.setAntiAlias(false);
    
    col1=android.graphics.Color.rgb(
      128+world.rnd.nextInt(128),
      128+world.rnd.nextInt(128),
      128+world.rnd.nextInt(128));
    this.p.setColor(col1);
  }
  
  public void Init_OpenGL(float rx, float ry)
  {
    float[] points;
    
    points=this.Get_Points(rx, ry);
    pt_count=points.length/2;
    b=java.nio.ByteBuffer.allocateDirect(points.length*4)
        .order(java.nio.ByteOrder.nativeOrder())
        .asFloatBuffer();
    b.put(points);
    b.position(0);
    
    this.red=(128f+(float)world.rnd.nextInt(128))/255f;
    this.green=(128f+(float)world.rnd.nextInt(128))/255f;
    this.blue=(128f+(float)world.rnd.nextInt(128))/255f;
    this.alpha=255f;
  }
  
  public float[] Get_Points(float rx, float ry)
  {
    float[] points;
    java.util.ArrayList<Float> pts;
    
    pts=new java.util.ArrayList<Float>();
    Line_Effect(-rx, -ry, -rx, +ry, pts);
    Line_Effect(-rx, +ry, +rx, +ry, pts);
    Line_Effect(+rx, +ry, +rx, -ry, pts);
    Line_Effect(+rx, -ry, -rx, -ry, pts);
    points=rs.android.util.Type.To_Float_Array(pts);
    
    return points;
  }
  
  public void Line_Effect(float x1, float y1, float x2, float y2, java.util.ArrayList<Float> pts)
  {
    int c, seg_count=10;
    float x, y, pt[], d;
    java.util.Random rnd;
    
    rnd=new java.util.Random();
    pt=new float[2];
    
    d=Distance(x1, y1, x2, y2);
    seg_count=(int)(d/20f);
    if (seg_count<1)
      seg_count=1;
    
    for (c=0; c<seg_count; c++)
    {
      x=x1+((x2-x1)/(float)seg_count*(float)c);
      y=y1+((y2-y1)/(float)seg_count*(float)c);
      pt[0]=x; pt[1]=y;
      Deviate(pt, rnd, 10);
      pts.add(pt[0]);
      pts.add(pt[1]);
    }
  }
  
  public float[] Deviate(float[] pt, java.util.Random rnd, float dev)
  {
    pt[0]+=rnd.nextFloat()*2f*dev-dev;
    pt[1]+=rnd.nextFloat()*2f*dev-dev;
    return pt;
  }
  
  public float Distance(float x1, float y1, float x2, float y2)
  {
    double x, y, d;
    
    x=x2-x1;
    y=y2-y1;
    d=Math.sqrt(x*x+y*y);
    
    return (float)d;
  }
  
  @Override
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    this.Draw_OpenGL((rs.projecta.view.OpenGL_View)v);
  }
  
  public void Draw_Canvas(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    c.drawRect(x1, y1, x2, y2, p);
  }
  
  public void Draw_OpenGL(rs.projecta.view.OpenGL_View v)
  {
    android.opengl.GLES20.glVertexAttribPointer(v.ogl_ctx.att_loc, 2, android.opengl.GLES20.GL_FLOAT, false, 0, b);
    android.opengl.GLES20.glEnableVertexAttribArray(v.ogl_ctx.att_loc);
    
    android.opengl.GLES20.glUniformMatrix4fv(v.ogl_ctx.mat_loc, 1, false, v.ogl_ctx.proj.vals, 0);
    android.opengl.GLES20.glUniform4f(v.ogl_ctx.col_loc, this.red, this.green, this.blue, this.alpha);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, this.pt_count);
  }
  
  public float Get_X()
  {
    return this.body.getPosition().x*this.world.phys_scale;
  }

  public float Get_Y()
  {
    return this.body.getPosition().y*this.world.phys_scale;
  }

  public void Set_X(float x)
  {
    org.jbox2d.common.Vec2 curr_pos, new_pos;
    float angle;
    
    curr_pos=this.body.getPosition();
    new_pos=new org.jbox2d.common.Vec2(x/world.phys_scale, curr_pos.y);
    angle=this.body.getAngle();
    
    this.body.setTransform(new_pos, angle);
  }

  public void Set_Y(float y)
  {
    org.jbox2d.common.Vec2 curr_pos, new_pos;
    float angle;

    curr_pos=this.body.getPosition();
    new_pos=new org.jbox2d.common.Vec2(curr_pos.x, y/world.phys_scale);
    angle=this.body.getAngle();

    this.body.setTransform(new_pos, angle);
  }
  
  public float Get_Angle_Degrees()
  {
    return (float)java.lang.Math.toDegrees(this.body.getAngle());
  }

  public void Set_Angle_Degrees(float a)
  {
    this.body.setTransform(this.body.getPosition(), 
      (float)java.lang.Math.toRadians(a));
  }

  public void Remove()
  {
    this.world.phys_world.destroyBody(this.body);
  }
  
  public void Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    rs.projecta.object.Player player;
  
    player = (Player)rs.projecta.world.World.Get_Contact_Object_If_Type(c, Player.class);
    if (player != null)
    {
      Explosion.Add(this.world, player.Get_X(), player.Get_Y());
      //android.util.Log.d("Player", "Contact(): instanceof Wall");
    }
  }
}
