package rs.projecta.ogl.shapes;

public class Rectangle implements rs.projecta.ogl.Is_Drawable
{
  public static java.nio.FloatBuffer points = rs.projecta.ogl.Context.New_Buffer(Get_Points());
  
  public static float[] Get_Points()
  {
    float[] p = {1,1, 1,-1, -1,-1, -1,1};
    return p;
  }
  
  public java.nio.FloatBuffer Get_Points_Buffer()
  {
    return this.points;
  };
  
  public void Draw(rs.projecta.ogl.Context ctx, int frame_idx)
  {
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, 4);
  }
}
