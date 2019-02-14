package rs.projecta.ogl;

public class Color
{
  float red, green, blue, alpha;
  
  public Color(int col)
  {
    this.red=(float)android.graphics.Color.red(col)/255f;
    this.green=(float)android.graphics.Color.green(col)/255f;
    this.blue=(float)android.graphics.Color.blue(col)/255f;
    this.alpha=(float)android.graphics.Color.alpha(col)/255f;
  }
}
