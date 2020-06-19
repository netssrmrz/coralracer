package rs.projecta.level;

// introduce player to blackhole
public class Tutorial_Black_Hole
extends rs.projecta.level.Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    //return Tutorial_Bouncy_Castle.class;
    return Tutorial_Black_Hole.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Tutorial 3";
  }
  
  @Override
  public String Get_Description()
  {
    return "Reach the end but stay away from the black hole.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    rs.projecta.object.Finish finish;
    rs.projecta.object.Player player;
    rs.projecta.object.Black_Hole black_hole;
  
    super.Build(w);

    player = new rs.projecta.object.Player(w, 0, 300, 0, 0, 0);
    finish = new rs.projecta.object.Finish(w, 0, -2200, 0, 0, 0);
    black_hole = new rs.projecta.object.Black_Hole(w, 400, -1000, player);
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(finish);
    w.objs.Add(player);
    w.objs.Add(black_hole);

    this.Add_Start_Walls(0, 0);
    this.Add_Walls_Vertical(0, -1000);
    this.Add_Finish_Walls(0, -2000);
  }
}
