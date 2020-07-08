package rs.projecta.level;

// teach player to maneuver through tight spaces
public class Tutorial_Bouncy_Castle
extends Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    return rs.projecta.level.Tutorial_Accelerator.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Tutorial 4";
  }
  
  @Override
  public String Get_Description()
  {
    return "Some walls fight back.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    rs.projecta.object.Finish finish;
    rs.projecta.object.Player player;
    rs.projecta.object.Bouncy_Wall wall;
  
    super.Build(w);

    player = new rs.projecta.object.Player(w, 0, 300, 0, 0, 0);
    finish = new rs.projecta.object.Finish(w, 0, -2200, 0, 0, 0);
    wall = new rs.projecta.object.Bouncy_Wall(w, 0, -1000, 200, 20, 0);
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(finish);
    w.objs.Add(player);
    w.objs.Add(wall);

    this.Add_Start_Walls(0, 0);
    this.Add_Walls_Vertical(0, -1000);
    this.Add_Finish_Walls(0, -2000);
  }
}
