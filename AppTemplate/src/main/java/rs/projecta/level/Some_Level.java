package rs.projecta.level;

public class Some_Level
  extends Level
{
  @Override
  public Class<? extends Level> Get_Next_Level()
  {
    return rs.projecta.level.Some_Level.class;
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
    rs.projecta.object.Player player = new rs.projecta.object.Player(0, 0, w);
    Add_Wavy_Bkg(w, player);
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Finish(w, 0, -4200));
    w.objs.Add(new rs.projecta.object.Wall(w, -1f, -654.5f, 438f, 40f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, 0f, 292.5f, 97f, 26.5f, -180f));
    w.objs.Add(new rs.projecta.object.Wall(w, -335f, -474.5f, 100f, 100f, -42.27368900609373f));
    w.objs.Add(new rs.projecta.object.Wall(w, 337f, -462.5f, -109.65573620536557f, 100.88418863855372f, 42.229784202799806f));
  }
}