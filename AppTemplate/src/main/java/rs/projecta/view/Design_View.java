package rs.projecta.view;

public class Design_View
extends Game_View
{ 
  public Design_View(android.content.Context context, rs.projecta.world.World world)
  {
    //super(context, world);
    super(context);
    this.camera=new rs.projecta.object.Camera(0, 0);
  }
}
