package rs.projecta.level;

// introduce player to maneuvering
public class Tutorial_Turn
extends Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    return rs.projecta.level.Tutorial_Black_Hole.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Tutorial 2";
  }
  
  @Override
  public String Get_Description()
  {
    return "Practice negotiating turns.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    rs.projecta.object.Finish finish;
    rs.projecta.object.Player player;
  
    super.Build(w);
  
    player = new rs.projecta.object.Player(w, 0, 300, 0, 0, 0);
    finish = new rs.projecta.object.Finish(w, 2000, -4200, 0, 0, 0);
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(finish);
    w.objs.Add(player);
  
    this.Add_Start_Walls(0, 0);
    this.Add_Walls_Vertical(0, -1000);
    this.Add_Walls_Turn_Right_Up(0, -2000);
    this.Add_Walls_Horiz(1000, -2000);
    this.Add_Walls_Turn_Right_Up(2000, -2000);
    this.Add_Walls_Vertical(2000, -3000);
    this.Add_Finish_Walls(2000, -4000);
  }
}
