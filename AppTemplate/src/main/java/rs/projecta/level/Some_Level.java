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
    rs.projecta.object.Player player = new rs.projecta.object.Player(w, 0f, -585f, 100f, 100f, -180f);
    Add_Wavy_Bkg(w, player);
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Wall(w, -2f, 357f, 1242f, 57.99999999999999f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, -1240f, -1035f, 1561.2735548654414f, 81.39340807355393f, -90f));
    w.objs.Add(new rs.projecta.object.Wall(w, 1430f, -1465f, 1892.0879144680932f, -99.71621695482955f, -84.55966796899449f));
    w.objs.Add(new rs.projecta.object.Finish(w, 10f, -11835f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, -130f, -4285f, 2060f, 120f, -51.95295746817391f));
    w.objs.Add(new rs.projecta.object.Wall(w, 2750f, -4755f, 1880f, 150f, -51.70983680775693f));
    w.objs.Add(new rs.projecta.object.Wall(w, 1250f, -6705f, 844.5011611073305f, 140.77566866603672f, -265.26544984244947f));
    w.objs.Add(new rs.projecta.object.Wall(w, 3940f, -7225f, 1120f, 90f, 86.82016988013577f));
    w.objs.Add(new rs.projecta.object.Wall(w, -450f, -7565f, 1920f, 130f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, 660f, -9495f, 2130f, 140f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, 3340f, -8925f, 1030f, 110.00000000000001f, 46.8476102659946f));
    w.objs.Add(new rs.projecta.object.Wall(w, -870f, -10595f, 2510f, 170f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, 1490f, -11745f, 150f, 1019.9999999999999f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, -1400f, -12775f, 3050f, 130f, 0f));
    w.objs.Add(new rs.projecta.object.Wall(w, -5100f, -10195f, 1600f, 110.00000000000001f, -90.88140399658214f));
    w.objs.Add(new rs.projecta.object.Wall(w, -3750f, -8055f, 1639.9999999999998f, 130f, -158.19859051364818f));
    w.objs.Add(new rs.projecta.object.Wall(w, -4760f, -12255f, 770f, 100f, -56.309932474020215f));
    w.objs.Add(new rs.projecta.object.Wall(w, -2420f, -10015f, 1132.1555094424857f, 138.28919856237644f, 24.77514056883193f));
  }
}
