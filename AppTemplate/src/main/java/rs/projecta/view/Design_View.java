package rs.projecta.view;

public class Design_View
extends android.view.SurfaceView
implements rs.projecta.view.Game_View
{
  public Object camera;
  
  public Design_View(android.content.Context context, rs.projecta.world.World world)
  {
    //super(context, world);
    super(context);
    this.camera=new rs.projecta.object.Camera(0, 0);
  }
  
  public void onResume()
  {
  }
  
  public void onPause()
  {
  }
  
  public Object Get_Camera()
  {
    return this.camera;
  }
}
