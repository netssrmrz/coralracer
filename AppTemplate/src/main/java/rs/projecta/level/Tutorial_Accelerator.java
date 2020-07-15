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
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 597f, -5988f, 500f, 20f, -258.9527933933154f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 932.937830777829f, -6916.003211855793f, 500f, 20f, -241.03094991126443f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1516.258283168078f, -7711.151888236403f, 500f, 20f, -226.14901209954235f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2347.8212868286573f, -8205.495880026401f, 500f, 20f, -196.53483785734517f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5700.183704076804f, -7523.517634939906f, 3000f, 20f, -164.07084439895772f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -432.3507353041266f, -5990.437638473231f, 500f, 20f, -261.7405620201212f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -178.10073719843672f, -6948.93763133185f, 500f, 20f, -247.9466634584182f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 287.64925933145537f, -7821.937624827493f, 500f, 20f, -235.68491240000273f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 953.6492543693687f, -8555.437619362492f, 500f, 20f, -219.60002657667494f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 1786.7119369900824f, -9090.937340714505f, 500f, 20f, -206.25865955480725f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 2729.2119001485407f, -9358.882820680737f, 500f, 20f, -184.82076607809267f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, -500f, -2500f, 3000f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6192.732397659682f, -9853.492206623312f, 3000f, 20f, -8.76655130865328f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 3979.788024195516f, -8754.557413260856f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 4791.617164491676f, -8976.961921807379f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5081.304651893675f, -8254.52540872991f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5460.992156349181f, -9383.462332431507f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5987.49215242645f, -7819.149844086543f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 5820.429653671163f, -8659.524837825262f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6372.242149559839f, -9241.712333487627f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6625.367147673911f, -8310.212340427843f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7126.554489444535f, -9651.774830432423f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 6979.741990538373f, -7545.774846123346f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7364.491987671762f, -8851.899836391956f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 7688.491985257774f, -7996.337342766394f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8169.4294816745105f, -7257.212348273304f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8533.929478958773f, -8178.587341408525f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8255.491981033294f, -9089.837334619184f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8012.491982843786f, -9879.587328735088f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 8852.866976582503f, -9803.649829300866f, 140f, 140f, -42.87890360333846f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 14292.43751343547f, -8367.230453556196f, 5500f, 20f, 20.677318035147934f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 10657.917946533766f, -8294.215840642806f, 100f, 100f, -40.23840308678268f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 11576.164079452137f, -6915.352520397115f, 3000f, 20f, -184.2169481089988f));
    w.objs.Add(new rs.projecta.object.Finish(w, 1000f, 250f, 50f, 50f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9500f, 500f, 10000f, 20f, 0f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 19445.976755112213f, -2941.1621767628912f, 3500f, 20f, 90f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 11377.183331577533f, -7445.616482077665f, 100f, 100f, -122.53506715793498f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 12265.652108469692f, -7999.960248856532f, 100f, 100f, -101.61148642388848f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 13381.933392257277f, -8235.366505981805f, 100f, 100f, -44.19307054489763f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 14179.277166391264f, -7407.647730928427f, 100f, 100f, -96.13725594926197f));
    w.objs.Add(new rs.projecta.object.Accelerator(w, 15591.714709142901f, -7544.335235065682f, 100f, 100f, -29.42745640318947f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9903.995786987118f, -9199.77278517244f, 508.7812653997826f, 72.14062718355126f, 38.418055344821994f));
    w.objs.Add(new rs.projecta.object.Bouncy_Wall(w, 9698.964530781235f, -7529.147734605987f, 508.7812653997826f, 72.14062718355126f, -208.6104596659652f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 14261.30863914013f, -4954.463033944587f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 13942.371138508934f, -3644.541156352172f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 12609.668010871434f, -4145.728657344052f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 13612.043012855194f, -1958.7286530158472f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Black_Hole(w, 12632.449260916519f, -2710.5099045036677f, 100f, 100f, 0f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 15914.63658716772f, -6550.134980767069f, 415.96868908710894f, 63.07024110402018f, 0f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 16620.855338565372f, -6994.369356646235f, 415.96868908710894f, 63.07024110402018f, -236.79342968491045f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14987.498023679187f, -6792.788486091071f, 415.96868908710894f, 63.07024110402018f, -149.93141717813754f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14744.498016324067f, -5828.382206900437f, 415.96868908710894f, 63.07024110402018f, -153.63812449438836f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 15496.279289078971f, -5418.319694488671f, 415.96868908710894f, 63.07024110402018f, -176.8435035296351f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 16354.37306505174f, -5441.100945178214f, 415.96868908710894f, 63.07024110402018f, -199.7988763545249f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 17060.591836427557f, -5911.91345942876f, 415.96868908710894f, 63.07024110402018f, -223.1323211605658f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 17622.52935343627f, -6618.132230804578f, 415.96868908710894f, 63.07024110402018f, -237.09475707701208f));
    w.objs.Add(new rs.projecta.object.Loose_Wall(w, 14061.06049563779f, -6534.600978276256f, 415.96868908710894f, 63.07024110402018f, -123.14699583225597f));  }
}