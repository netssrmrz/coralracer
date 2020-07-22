package rs.projecta.ogl.shapes;

public class Mine implements rs.projecta.ogl.Is_Drawable
{
  public static java.nio.FloatBuffer points = rs.projecta.ogl.Context.New_Buffer(Get_Points());
  
  public static float[] Get_Points()
  {
    float[] points=
      {
        100f, 0f, 95.108f, 30.891f, 80.914f, 58.761f, 58.804f, 80.882f
        , 30.942f, 95.092f, 0.053f, 99.999f, -30.841f, 95.125f, -58.718f, 80.945f, -80.851f, 58.847f
        , -95.076f, 30.992f, -99.999f, 0.106f, -95.141f, -30.79f, -80.976f, -58.675f, -58.89f, -80.82f
        , -31.043f, -95.059f, -0.159f, -99.999f, 30.739f, -95.158f, 58.632f, -81.007f, 80.789f, -58.933f
        , 95.043f, -31.093f, 100f, 0f,
        100f, -10f, 120f, -10f, 120f, 10f, 100f, 10f, 100f, -10f,
        -10f, 100f, 10f, 100f, 10f, 120f, -10f, 120f, -10f, 100f,
        -10f, -120f, 10f, -120f, 10f, -100f, -10f, -100f, -10f, -120f,
        -120f, -10f, -100f, -10f, -100f, 10f, -120f, 10f, -120f, -10f
      };
    
    return points;
  };
  
  public java.nio.FloatBuffer Get_Points_Buffer()
  {
    return this.points;
  };
  
  public void Draw(rs.projecta.ogl.Context ctx, int frame_idx)
  {
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_LOOP, 0, 21);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 21, 5);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 26, 5);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 31, 5);
    android.opengl.GLES20.glDrawArrays(android.opengl.GLES20.GL_LINE_STRIP, 36, 5);
  }
}