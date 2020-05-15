package rs.projecta.ogl;

public class Fish1
{
  public rs.projecta.ogl.Color color;
  public static rs.projecta.ogl.Pt_Buffer points;
  
  public Fish1(int col)
  {
    if (points==null)
    {
      points = new rs.projecta.ogl.Pt_Buffer(this.Get_Points());
      points.frames = new Buffer_Frame[4];
      points.frames[0] = new Buffer_Frame(android.opengl.GLES20.GL_LINE_LOOP, 0, 11);
      points.frames[1] = new Buffer_Frame(android.opengl.GLES20.GL_LINE_LOOP, 11, 11);
      points.frames[2] = new Buffer_Frame(android.opengl.GLES20.GL_LINE_LOOP, 22, 11);
      points.frames[3] = new Buffer_Frame(android.opengl.GLES20.GL_LINE_LOOP, 33, 11);
    }
    this.color = new rs.projecta.ogl.Color(col);
  }
  
  public float[] Get_Points()
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
  
  public void Draw(Context ctx, float r, int frame)
  {
    ctx.Draw(r, r, 0, 0, 0, this.points, this.color, frame);
  }
}
