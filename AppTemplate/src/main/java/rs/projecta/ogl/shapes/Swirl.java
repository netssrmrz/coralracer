package rs.projecta.ogl.shapes;

public class Swirl implements rs.projecta.ogl.Is_Drawable
{
  public static java.nio.FloatBuffer points = rs.projecta.ogl.Context.New_Buffer(Get_Points());
  
  public static float[] Get_Points()
  {
    float a=0, s=0f;
    float[] p;
    int i;
    
    p=new float[80];
    for (i=0; i<p.length; i+=2)
    {
      s+=0.075;
      a+=0.6;
      p[i]=(float) Math.cos(a)*s;
      p[i+1]=(float) Math.sin(a)*s;
    }
    
    return p;
  }
  
  public java.nio.FloatBuffer Get_Points_Buffer()
  {
    return this.points;
  };
  
  public void Draw(rs.projecta.ogl.Context ctx, int frame_idx)
  {
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 0, 40);
  }
}
