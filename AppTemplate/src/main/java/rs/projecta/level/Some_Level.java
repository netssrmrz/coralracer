package rs.projecta.level;

public class Some_Level
  extends Level
{
  public Some_Level()
  {
    this.next_level = rs.projecta.level.Some_Level.class;
  }
  
  @Override
  public String Get_Title()
  {
    return "Some Tile";
  }
  
  @Override
  public String Get_Description()
  {
    return "Some Description";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    super.Build(w);
    rs.projecta.object.Player player = new rs.projecta.object.Player(w, 0f, 300f, 25f, 25f, 180f);
    Add_Wavy_Bkg(w, player);
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 0f, 500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, 0f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, 0f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, -1000f, 500f, 20f, 810f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, -1000f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, -2000f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, -2000f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 0f, -2500f, 500f, 20f, 0f));
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Finish(w, 0f, -2200f, 100f, 100f, 0f));
  }
}
