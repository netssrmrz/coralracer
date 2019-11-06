package rs.projecta.ogl;

public class Rectangle
{
  public rs.projecta.ogl.Color color;
  
  public static rs.projecta.ogl.Pt_Buffer points = Get_Points();

  public Rectangle(int col)
  {
    this.color = new rs.projecta.ogl.Color(col);
  }
  
  public static rs.projecta.ogl.Pt_Buffer Get_Points()
  {
    float[] p = {1,1, 1,-1, -1,-1, -1,1};
    return new rs.projecta.ogl.Pt_Buffer(p);
  }
  
  public void Draw(Context ctx, float dx, float dy, float a_degrees, float x, float y)
  {
    ctx.Draw(dx, dy, a_degrees, x, y, points, color);
  }
}
