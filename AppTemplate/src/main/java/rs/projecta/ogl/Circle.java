package rs.projecta.ogl;

public class Circle
{
  public float red, green, blue, alpha;
  public static float[] points;
  public static java.nio.FloatBuffer b;
  public static int pt_count;

  public Circle(int col)
  {
    if (points==null)
    {
      points = this.Get_Points();
      pt_count = points.length / 2;
      b = java.nio.ByteBuffer.allocateDirect(points.length * 4)
        .order(java.nio.ByteOrder.nativeOrder())
        .asFloatBuffer();
      b.put(points);
      b.position(0);
    }
    this.red=(float)android.graphics.Color.red(col)/255f;
    this.green=(float)android.graphics.Color.green(col)/255f;
    this.blue=(float)android.graphics.Color.blue(col)/255f;
    this.alpha=(float)android.graphics.Color.alpha(col)/255f;
  }
  
  public float[] Get_Points()
  {
    float a;
    float[] p;
    int i;
    
    p=new float[40];
    for (i=0; i<p.length; i+=2)
    {
      a=(float) Math.PI/((float)p.length/2f)*(float)i;
      p[i]=(float) Math.cos(a);
      p[i+1]=(float) Math.sin(a);
    }
    
    return p;
  }
  
  public void Draw(rs.projecta.ogl.Context ctx, float r)
  {
    android.opengl.GLES20.glVertexAttribPointer(ctx.att_loc, 2, android.opengl.GLES20.GL_FLOAT, false, 0, b);
    android.opengl.GLES20.glEnableVertexAttribArray(ctx.att_loc);
  
    ctx.proj.Save();
    android.opengl.Matrix.scaleM(ctx.proj.vals, 0, r, r, 1f);
    android.opengl.GLES20.glUniformMatrix4fv(ctx.mat_loc, 1, false, ctx.proj.vals, 0);
    android.opengl.GLES20.glUniform4f(ctx.col_loc, this.red, this.green, this.blue, this.alpha);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, pt_count);
    ctx.proj.Restore();
  }
}
