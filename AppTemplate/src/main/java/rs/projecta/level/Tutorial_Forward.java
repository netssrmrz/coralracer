package rs.projecta.level;

// introduce player to tilt mechanism
public class Tutorial_Forward
extends Level
{
  public Tutorial_Forward()
  {
    this.next_level = rs.projecta.level.Tutorial_Turn.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Tutorial 1";
  }
  
  @Override
  public String Get_Description()
  {
    return "Tilt the device left and right to turn. Get to the blue arrows.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    rs.projecta.object.Finish finish;
    rs.projecta.object.Player player;
  
    super.Build(w);

    player = new rs.projecta.object.Player(w, 0, 300, 0, 0, 0);
    finish = new rs.projecta.object.Finish(w, 0, -2200, 0, 0, 0);
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(finish);
    w.objs.Add(player);

    this.Add_Start_Walls(0, 0);
    this.Add_Walls_Vertical(0, -1000);
    this.Add_Finish_Walls(0, -2000);
  }
}
