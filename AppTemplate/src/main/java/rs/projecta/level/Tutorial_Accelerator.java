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
    return "Tutorial 5";
  }
  
  @Override
  public String Get_Description()
  {
    return "Learn to fly.";
  }
  
  @Override
  public void Build(rs.projecta.world.World w)
  {
    super.Build(w);
    rs.projecta.object.Player player = new rs.projecta.object.Player(w, 0f, 250f, 25f, 25f, 180f);
    Add_Wavy_Bkg(w, player);
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 0f, 500f, 500f, 20f, 0f));
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, 0f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, 0f, 500f, 20f, -90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1000f, 500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -1000f, 500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1500f, 0f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1500f, -1000f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1000f, -1500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 0f, -1500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -1000f, -1500f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -1500f, 0f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -1500f, -1000f, 500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Finish(w, 1000f, 0f, 50f, 50f, 0f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 0f, -1000f, 1f, 1f, -63.758650347842234f));
  }
}