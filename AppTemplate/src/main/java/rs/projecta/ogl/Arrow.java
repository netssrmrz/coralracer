package rs.projecta.ogl;

public class Arrow
{
  public Color color;
  public static Pt_Buffer points;

  public Arrow(int col)
  {
    if (points==null)
      points = new Pt_Buffer(this.Get_Points());
    this.color = new Color(col);
  }
  
  public float[] Get_Points()
  {
    float[] p =
      {
        -1, 0,
        0, -1,
        0, -0.5f,
        1, -0.5f,
        1, 0.5f,
        0, 0.5f,
        0, 1
      };
    
    return p;
  }
  
  public void Draw(Context ctx, float r, float a_degrees, float x, float y)
  {
    ctx.Draw(r, a_degrees, x, y, points, color);
  }
}
