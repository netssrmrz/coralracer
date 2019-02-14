package rs.projecta.level;

public class Tutorial_Black_Hole
extends rs.projecta.level.Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    return Flappy_Bird.class;
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
    rs.projecta.object.Black_Hole black_hole;
  
    super.Build(w);

    player = new rs.projecta.object.Player(0, 300, w, w.hint);
    finish = new rs.projecta.object.Finish(w, 0, -2200);
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
