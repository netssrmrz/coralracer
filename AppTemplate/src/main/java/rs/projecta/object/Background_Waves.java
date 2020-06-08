package rs.projecta.object;

public class Background_Waves
implements rs.projecta.object.features.Is_Drawable
{
  public static final int PTS=20;
  public rs.projecta.object.features.Has_Position cam;
  public android.graphics.Paint paint;
  public int tile_span, hint;
  public float tile_size, size_z, tile_z;
  public android.graphics.Point curr_tile_index; //, curr_paint_index;
  android.graphics.PointF curr_tile_pos;
  public float cam_x, cam_y;
  public android.graphics.Path pts;
  // open gl
  public java.nio.FloatBuffer b;
  public int OGL_POINT_COUNT;
  public float red, green, blue, alpha;
  
  public Background_Waves(rs.projecta.object.features.Has_Position cam, float height, int col, int hint)
  {
    this.cam=cam;
    this.tile_span=3;
    this.tile_z=height;
    this.tile_size=1000;
    this.size_z=tile_size/tile_z;
    this.hint=hint;
    curr_tile_index=new android.graphics.Point();
    curr_tile_pos=new android.graphics.PointF();
  
    if (this.hint==rs.projecta.world.World.HINT_ES2)
      Init_OpenGL(col);
    else
      Init_Canvas(col);
  }
  
  public void Init_Canvas(int col)
  {
    int c;
    
    //curr_paint_index=new android.graphics.Point();
    
    this.paint=new android.graphics.Paint();
    this.paint.setStyle(android.graphics.Paint.Style.STROKE);
    this.paint.setColor(col);
    this.paint.setAntiAlias(false);
    
    this.pts=new android.graphics.Path();
    this.pts.moveTo(0, size_z/2f);
    for (c=0; c<PTS; c++)
    {
      this.pts.lineTo(
        size_z/PTS*(c+1),
        (float)java.lang.Math.sin(java.lang.Math.PI*2f/PTS*(c+1))*(size_z/4f)+(size_z/2f));
    }
  }
  
  public void Init_OpenGL(int col)
  {
    float[] points;
    
    points=this.Get_Points();
    b=java.nio.ByteBuffer.allocateDirect(points.length*4)
        .order(java.nio.ByteOrder.nativeOrder())
        .asFloatBuffer();
    b.put(points);
    b.position(0);
    
    this.red=(float)android.graphics.Color.red(col)/255f;
    this.green=(float)android.graphics.Color.green(col)/255f;
    this.blue=(float)android.graphics.Color.blue(col)/255f;
    this.alpha=(float)android.graphics.Color.alpha(col)/255f;
  }
  
  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {    
    float tx, ty;
    
    this.cam_x=cam.Get_X();
    this.cam_y=cam.Get_Y();
    
    for (curr_tile_index.y=0; curr_tile_index.y<this.tile_span+1; curr_tile_index.y++)
    {
      for (curr_tile_index.x=0; curr_tile_index.x<this.tile_span+1; curr_tile_index.x++)
      {
        tx=cam_x+tile_size*(curr_tile_index.x-tile_span/2f);
        ty=cam_y+tile_size*(curr_tile_index.y-tile_span/2f);
        curr_tile_pos.x=(float)java.lang.Math.floor(tx/tile_size)*tile_size;
        curr_tile_pos.y=(float)java.lang.Math.floor(ty/tile_size)*tile_size;
        curr_tile_pos.x = (curr_tile_pos.x-cam_x) / tile_z + cam_x;
        curr_tile_pos.y = (curr_tile_pos.y-cam_y) / tile_z + cam_y;
  
        if (this.hint==rs.projecta.world.World.HINT_ES2)
          this.Draw_OpenGL((rs.projecta.view.OpenGL_View)v);
        else
          this.Draw_Canvas(v, c);
      }
    }
  }
  
  public void Draw_Canvas(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    c.save();
    c.translate(curr_tile_pos.x, curr_tile_pos.y);
    c.drawPath(pts, this.paint);
    c.restore();
  }
  
  public void Draw_OpenGL(rs.projecta.view.OpenGL_View v)
  {
    android.opengl.GLES20.glVertexAttribPointer(v.ogl_ctx.att_loc, 2, android.opengl.GLES20.GL_FLOAT, false, 0, b);
    android.opengl.GLES20.glEnableVertexAttribArray(v.ogl_ctx.att_loc);
    
    //v.Save_Transform();
    v.ogl_ctx.proj.Save();
    android.opengl.Matrix.translateM(v.ogl_ctx.proj.vals, 0, curr_tile_pos.x, curr_tile_pos.y, 0);
    
    android.opengl.GLES20.glUniformMatrix4fv(v.ogl_ctx.mat_loc, 1, false, v.ogl_ctx.proj.vals, 0);
    android.opengl.GLES20.glUniform4f(v.ogl_ctx.col_loc, this.red, this.green, this.blue, this.alpha);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 0, OGL_POINT_COUNT);
    
    //v.Restore_Transform();
    v.ogl_ctx.proj.Restore();
  }
  
  public static void Select_Tile_To_Render(
    rs.projecta.object.features.Has_Position cam, android.graphics.Point curr_tile_index,
    float tile_width, int tile_span,
    android.graphics.Point curr_paint_index)
  {
    float tx, ty;
    
    tx=cam.Get_X()/tile_width-(float)tile_span/2f+curr_tile_index.x;
    ty=cam.Get_Y()/tile_width-(float)tile_span/2f+curr_tile_index.y;
    curr_paint_index.x=(int)java.lang.Math.abs(java.lang.Math.floor(tx)%tile_span);
    curr_paint_index.y=(int)java.lang.Math.abs(java.lang.Math.floor(ty)%tile_span);
  }
  
  public float[] Get_Points()
  {
    float[] points;
    int c;
    
    points=new float[(PTS+1)*2];
    for (c=0; c<=PTS; c++)
    {
      points[c*2]=(this.size_z/PTS)*c;
      points[c*2+1]=
        (float)java.lang.Math.sin((java.lang.Math.PI*2f/PTS)*c)*(this.size_z/4f)+(this.size_z/2f);
    }
    this.OGL_POINT_COUNT=PTS+1;
    
    return points;
  }}
