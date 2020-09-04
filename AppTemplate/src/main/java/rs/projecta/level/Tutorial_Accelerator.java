package rs.projecta.level;

public class Tutorial_Accelerator
  extends Level
{
  public Tutorial_Accelerator()
  {
    this.next_level = null;
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
    
    rs.projecta.object.Portal portal1 = new rs.projecta.object.Portal(w, 1250f, -6980f, 100f, 100f, 0f);
    portal1.to_x = 10613f; portal1.to_y = -340f;
    rs.projecta.object.Portal portal2 = new rs.projecta.object.Portal(w, 1033f, -66f, 100f, 100f, 0f);
    portal1.to_x = 10224f; portal1.to_y = -5756f;
    rs.projecta.object.Portal portal3 = new rs.projecta.object.Portal(w, 8528f, 183f, 100f, 100f, 0f);
    portal1.to_x = 804f; portal1.to_y = -2534f;
    rs.projecta.object.Portal portal4 = new rs.projecta.object.Portal(w, 5066f, -1900f, 100f, 100f, 0f);
    portal1.to_x = 15224f; portal1.to_y = -8992f;
    rs.projecta.object.Portal portal5 = new rs.projecta.object.Portal(w, 10738f, -6263f, 100f, 100f, 0f);
    portal1.to_x = 10613f; portal1.to_y = -340f;
  
    rs.projecta.object.Finish finish1 = new rs.projecta.object.Finish(w, 9083f, -1723f, 50f, 50f, 0f);
    finish1.next_level = rs.projecta.level.Flappy_Bird.class;
    rs.projecta.object.Finish finish2 = new rs.projecta.object.Finish(w, 10602f, -5f, 50f, 50f, 0f);
    finish2.next_level = rs.projecta.level.Tutorial_Accelerator.class;
  
    Add_Wavy_Bkg(w, player);
    w.objs.Add(player);
    w.objs.Add(new rs.projecta.object.Current(w, 0f, -1000f, 200f, 1000f, 180f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 500f, -2500f, 3000f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 597f, -5988f, 500f, 20f, -258.952f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 932f, -6916f, 500f, 20f, -241.03f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1516f, -7711f, 500f, 20f, -226.149f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2347f, -8205f, 500f, 20f, -196.534f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 4332f, -8337f, 1532.64f, 30.482f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -432f, -5990f, 500f, 20f, -261.74f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -178f, -6948f, 500f, 20f, -247.946f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 287f, -7821f, 500f, 20f, -235.684f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 953f, -8555f, 500f, 20f, -219.6f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1786f, -9090f, 500f, 20f, -206.258f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2729f, -9358f, 500f, 20f, -184.82f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, -2500f, 3000f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6213f, -9390f, 3000f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14671f, -9373f, 5500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 20162f, -5885f, 3500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5988f, 459f, 5500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14702f, 485f, 5500f, 20f, 0f));
    w.objs.Add(finish1);
    w.objs.Add(finish2);
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8f, 473f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 20169f, -956f, 1435.124f, 20.32f, 89.63f));
    w.objs.Add(new rs.projecta.object.Oil_Slick(w, 9347f, -6973f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10440f, -8340f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10451f, -7394f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8264f, -7429f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10463f, -6483f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8287f, -6529f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9927f, -7865f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8773f, -7865f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9995f, -5997f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8773f, -6035f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7770f, -7850f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7801f, -6028f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10914f, -7858f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10929f, -5990f, 500f, 20f, 89.533f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8294f, -5519f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10474f, -5534f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9284f, -4694f, 2581.875f, 18.562f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6737f, -6529f, 1825.952f, 29.24f, -269.858f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 11990f, -7439f, 1937.329f, 29.809f, -269.858f));
    w.objs.Add(new rs.projecta.object.Oil_Slick(w, 11489f, -8831f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15547f, -4689f, 3713.343f, 37.968f, 0f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 12648f, -6770f, 2012.481f, 29.784f, -269.766f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 13361f, -7320f, 2012.481f, 29.784f, -269.766f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14090f, -6760f, 2012.481f, 29.784f, -269.766f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8261f, -8347f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 11454f, -6983f, 100f, 100f, -178.844f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 7233f, -6909f, 100f, 100f, -1.163f));
    w.objs.Add(new rs.projecta.object.Oil_Slick(w, 7290f, -5191f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 9363f, -5157f, 100f, 100f, -181.054f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 9340f, -8847f, 100f, 100f, -0.702f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14762f, -7320f, 2012.481f, 29.784f, -269.766f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15615f, -5447f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 16418f, -7036f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 17204f, -5515f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 18161f, -7036f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 18981f, -5566f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 17324f, -8403f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 19015f, -8454f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15684f, -8215f, 500f, 20f, -269.937f));
    w.objs.Add(new rs.projecta.object.Mine(w, 18587f, -1918f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 18577f, -3361f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17873f, -2592f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 18582f, -4414f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19817f, -1847f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19832f, -3361f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19235f, -2617f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17746f, -3953f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 16759f, -2339f, 2316.093f, 14.554f, 89.964f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17083f, -3270f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17103f, -1873f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17858f, -1164f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19194f, -1164f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17144f, -481f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 18566f, -597f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19847f, -587f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 17853f, 197f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19204f, 141f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Mine(w, 19235f, -3979f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 19255f, -3341f, 100f, 100f, 44.555f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 18546f, -3933f, 100f, 100f, 52.554f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 18602f, -2683f, 100f, 100f, -38.173f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 17741f, -3321f, 100f, 100f, 42.031f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 17817f, -1913f, 100f, 100f, 42.78f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 17842f, -476f, 100f, 100f, 48.34f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 19761f, -1205f, 100f, 100f, -175.634f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 19255f, -546f, 100f, 100f, -143.689f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 18577f, -1230f, 100f, 100f, -45f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 17194f, -1230f, 100f, 100f, -40.633f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 19240f, -1908f, 100f, 100f, 45f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 17139f, -2632f, 100f, 100f, -45.206f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 11462f, -1881f, 2316.093f, 14.554f, 89.964f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9765f, -1858f, 2316.093f, 14.554f, 89.964f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10267f, -565f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10965f, -1103f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10608f, -1657f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10266f, -2265f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10631f, -2826f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10965f, -3335f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 10281f, -3814f, 500f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15046f, -2179f, 1089.703f, 145.547f, -45f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14241f, -1086f, 1089.703f, 145.547f, 45.776f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 12441f, -2073f, 1089.703f, 145.547f, -44.309f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14218f, -3265f, 1089.703f, 145.547f, 46.273f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 12669f, -3599f, 1089.703f, 145.547f, -44.309f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15676f, -3561f, 1089.703f, 145.547f, 44.577f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 13466f, -508f, 1089.703f, 145.547f, 45.776f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 15539f, -486f, 1089.703f, 145.547f, -44.44f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 14110f, -2145f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 12356f, -243f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 16149f, -4070f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 3539f, -4850f, 2016.14f, 250.593f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 4120f, -2937f, 660.595f, 1148.23f, -34.624f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5806f, -2629f, 795.838f, 411.086f, -34.624f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 4109f, -6001f, 725.685f, 275.774f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5703f, -6240f, 149.434f, 1659.473f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6102f, -3962f, 1222.492f, 113.603f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7389f, -2982f, 133.673f, 1101.555f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2537f, -818f, 2016.14f, 250.593f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5544f, -1160f, 133.673f, 1101.555f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1227f, -3107f, 725.685f, 275.774f, 1.618f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7959f, -328f, 795.838f, 411.086f, -34.624f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8460f, -3711f, 1046.273f, 181.844f, 57.425f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1922f, -6661f, 467.015f, 1002.375f, 0f));
    w.objs.Add(portal1);
    w.objs.Add(portal2);
    w.objs.Add(portal3);
    w.objs.Add(portal4);
    w.objs.Add(portal5);
  }
}