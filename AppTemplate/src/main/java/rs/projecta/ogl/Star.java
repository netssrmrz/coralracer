package rs.projecta.ogl;

public class Star
{
  public Color color;
  public static Pt_Buffer points;

  public Star(int col)
  {
    if (points==null)
      points = new Pt_Buffer(this.Get_Points());
    this.color = new Color(col);
  }
  
  public float[] Get_Points()
  {
    float a, s=1f;
    float[] p;
    int i;
    
    p=new float[40];
    for (i=0; i<p.length; i+=2)
    {
      if (s==1f)
        s=2f;
      else
        s=1f;
      
      a=(float)java.lang.Math.PI/((float)p.length/2f)*(float)i;
      p[i]=(float)java.lang.Math.cos(a)*s;
      p[i+1]=(float)java.lang.Math.sin(a)*s;
    }
    
    return p;
  }
  
  public void Draw(rs.projecta.ogl.Context ctx, float r)
  {
    ctx.Draw(r, 0, 0, 0, points, color);
  }
}
