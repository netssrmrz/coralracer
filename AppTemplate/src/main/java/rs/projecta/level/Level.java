package rs.projecta.level;

public class Level
{
  public rs.projecta.world.World w;

  public Class<? extends Level> Get_Next_Level()
  {
    return null;
  }
  
  public String Get_Title()
  {
    return null;
  }
  
  public void Build(rs.projecta.world.World w)
  {
    this.w=w;
  }
  
  public String Get_Description()
  {
    return null;
  }
  
  public static rs.projecta.level.Level Get(android.app.Activity a)
  {
    String class_name;
    rs.projecta.level.Level res=null;

    class_name=a.getIntent().getStringExtra("level_class");
    if (rs.android.Util.NotEmpty(class_name))
      res=Level.Get(class_name);

    return res;
  }
  
  public static rs.projecta.level.Level Get(String class_name)
  {
    Class<? extends Level> level_class=null;
    rs.projecta.level.Level res=null;

    try 
    {
      level_class=(Class<? extends Level>)Class.forName(class_name);
    }
    catch (Exception e) 
    {
      level_class=null;
    }

    if (level_class!=null)
      res=Level.Get(level_class);

    return res;
  }
  
  public static rs.projecta.level.Level Get(Class<? extends Level> level_class)
  {
    rs.projecta.level.Level res=null;

    try 
    {
      res = level_class.newInstance();
    }
    catch (Exception e) 
    {
      res=null;
    }

    return res;
  }
  
  public void Update()
  {
    
  }
  
  public void Add_Wavy_Bkg(rs.projecta.world.World w, rs.projecta.object.Player player)
  {
    rs.projecta.object.Background_Waves bd, bd2, bd3;

    bd=new rs.projecta.object.Background_Waves(player, 1.2f, 0xff0000ff, w.hint);
    bd2=new rs.projecta.object.Background_Waves(player, 1.4f, 0xff0000cc, w.hint);
    bd3=new rs.projecta.object.Background_Waves(player, 1.6f, 0xff000088, w.hint);
  
    w.objs.Add(bd3);
    w.objs.Add(bd2);
    w.objs.Add(bd);
  }
  
  public void Add_Walls_Vertical(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x+500, y, 20, 500, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x-500, y, 20, 500, 0f));
  }
  
  public void Add_Start_Walls(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y+500, 500, 20, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x+500, y, 20, 500, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x-500, y, 20, 500, 0f));
  }
  
  public void Add_Finish_Walls(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x+500, y, 20, 500, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x-500, y, 20, 500, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y-500, 500, 20, 0f));
  }
  
  public void Add_Walls_Horiz(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y+500, 500, 20, 0f));
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y-500, 500, 20, 0f));
  }
  
  public void Add_Walls_Turn_Right_Up(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y, 20, 707, 45f));
  }
  
  public void Add_Walls_Turn_Left_Down(float x, float y)
  {
    this.w.objs.Add(new rs.projecta.object.Bouncy_Wall(this.w, x, y, 20, 707, -45f));
  }
}
