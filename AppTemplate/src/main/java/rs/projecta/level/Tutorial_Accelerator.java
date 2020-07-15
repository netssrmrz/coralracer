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
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, -2500f, 3000f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 597f, -5988f, 500f, 20f, -258.952f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 932f, -6916f, 500f, 20f, -241.03f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1516f, -7711f, 500f, 20f, -226.149f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2347f, -8205f, 500f, 20f, -196.534f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5700f, -7523f, 3000f, 20f, -164.07f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -432f, -5990f, 500f, 20f, -261.74f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -178f, -6948f, 500f, 20f, -247.946f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 287f, -7821f, 500f, 20f, -235.684f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 953f, -8555f, 500f, 20f, -219.6f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1786f, -9090f, 500f, 20f, -206.258f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2729f, -9358f, 500f, 20f, -184.82f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, -2500f, 3000f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6192f, -9853f, 3000f, 20f, -8.766f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 3979f, -8754f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 4791f, -8976f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5081f, -8254f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5460f, -9383f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5987f, -7819f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5820f, -8659f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6372f, -9241f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6625f, -8310f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7126f, -9651f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6979f, -7545f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7364f, -8851f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7688f, -7996f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8169f, -7257f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8533f, -8178f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8255f, -9089f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8012f, -9879f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8852f, -9803f, 140f, 140f, -42.878f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14292f, -8367f, 5500f, 20f, 20.677f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 10657f, -8294f, 100f, 100f, -40.238f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 11576f, -6915f, 3000f, 20f, -184.216f));
    w.objs.Add(new rs.projecta.object.Finish(w, 1000f, 250f, 50f, 50f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9500f, 500f, 10000f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 19445f, -2941f, 3500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 11377f, -7445f, 100f, 100f, -122.535f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 12265f, -7999f, 100f, 100f, -101.611f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 13381f, -8235f, 100f, 100f, -44.193f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 14179f, -7407f, 100f, 100f, -96.137f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 15591f, -7544f, 100f, 100f, -29.427f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9903f, -9199f, 508.781f, 72.14f, 38.418f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9698f, -7529f, 508.781f, 72.14f, -208.61f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 12601f, -6248f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 11234f, -6145f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 9924f, -6008f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 8671f, -5929f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 7361f, -6305f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 15914f, -6550f, 415.968f, 63.07f, 0f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 16620f, -6994f, 415.968f, 63.07f, -236.793f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14987f, -6792f, 415.968f, 63.07f, -149.931f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14744f, -5828f, 415.968f, 63.07f, -153.638f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 15496f, -5418f, 415.968f, 63.07f, -176.843f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 16354f, -5441f, 415.968f, 63.07f, -199.798f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 17060f, -5911f, 415.968f, 63.07f, -223.132f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 17622f, -6618f, 415.968f, 63.07f, -237.094f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14061f, -6534f, 415.968f, 63.07f, -123.146f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 16441f, -4874f, 3000f, 20f, -184.216f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 13457f, -5656f, 1000f, 20f, 90f));
  }
}