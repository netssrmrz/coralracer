package rs.projecta.ogl.shapes;

public class Fish1 implements rs.projecta.ogl.Is_Drawable
{
  public static java.nio.FloatBuffer points = rs.projecta.ogl.Context.New_Buffer(Get_Points());
  
  public static float[] Get_Points()
  {
    float[] points=
    {
      0,-15, 5,-5, 10,0, 5,0, 0,15,
      5,20, -5,20, 0,15, -5,0, -10,0,
      -5,-5,
      
      5,-15, 5,-5, 10,0, 5,0, 5,15,
      10,20, 5,25, 5,15, -5,0, -10,0,
      -5,-5,
      
      0,-15, 5,-5, 10,0, 5,0, 0,15,
      5,20, -5,20, 0,15, -5,0, -10,0,
      -5,-5,
      
      -5,-15, -5,-5, -10,0, -5,0, -5,15,
      -10,20, -5,25, -5,15, 5,0, 10,0,
      5,-5,
    };
    
    return points;
  }
  
  public java.nio.FloatBuffer Get_Points_Buffer()
  {
    return this.points;
  };
  
  public void Draw(rs.projecta.ogl.Context ctx, int frame)
  {
    if (frame == 0)
    {
      android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, 11);
    }
    else if (frame == 1)
    {
      android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 11, 11);
    }
    else if (frame == 2)
    {
      android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 22, 11);
    }
    else if (frame == 3)
    {
      android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 33, 11);
    }
  }
}
