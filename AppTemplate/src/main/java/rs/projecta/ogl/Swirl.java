package rs.projecta.ogl;

public class Swirl
{
  public rs.projecta.ogl.Color color;
  public static rs.projecta.ogl.Pt_Buffer points;

  public Swirl(int col)
  {
    if (points==null)
    {
      points = new rs.projecta.ogl.Pt_Buffer(this.Get_Points());
      points.frames[0].segments[0].mode = android.opengl.GLES20.GL_LINE_STRIP;
    }
    this.color = new rs.projecta.ogl.Color(col);
  }
  
  public float[] Get_Points()
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
  
  public void Draw(Context ctx, float r, float x, float y)
  {
    ctx.Draw(r, 0, x, y, points, color);
  }
}
