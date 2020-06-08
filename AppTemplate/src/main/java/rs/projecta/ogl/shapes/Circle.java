package rs.projecta.ogl.shapes;

public class Circle implements rs.projecta.ogl.Is_Drawable
{
  public static java.nio.FloatBuffer points = rs.projecta.ogl.Context.New_Buffer(Get_Points());
  
  public static float[] Get_Points()
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
  
  public java.nio.FloatBuffer Get_Points_Buffer()
  {
    return this.points;
  };
  
  public void Draw(rs.projecta.ogl.Context ctx, int frame_idx)
  {
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, 20);
  }
}
