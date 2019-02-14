package rs.projecta.ogl;

public class Circle
{
  public Color color;
  public static Pt_Buffer points;

  public Circle(int col)
  {
    if (points==null)
      points = new Pt_Buffer(this.Get_Points());
    this.color = new Color(col);
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
    ctx.Draw(r, 0, 0, 0, points, color);
  }
}
