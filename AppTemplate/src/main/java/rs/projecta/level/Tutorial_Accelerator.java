package rs.projecta.level;

public class Tutorial_Accelerator
extends Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    return rs.projecta.level.Flappy_Bird.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Tutorial 1";
  }
  
  @Override
  public String Get_Description()
  {
    return "Tilt the device left and right to adjust direction and steer forward to the blue circle.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    rs.projecta.object.Finish finish;
    rs.projecta.object.Player player;
    //rs.projecta.object.Accelerator acc;
  
    super.Build(w);

    player = new rs.projecta.object.Player(0, 300, w);
    finish = new rs.projecta.object.Finish(w, 0, -2200);
    //acc = new rs.projecta.object.Accelerator(w, 0, -1000, 45);
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(finish);
    w.objs.Add(player);
    //w.objs.Add(acc);
    this.Add_Start_Walls(0, 0);
    this.Add_Walls_Vertical(0, -1000);
    this.Add_Finish_Walls(0, -2000);
  }
}
