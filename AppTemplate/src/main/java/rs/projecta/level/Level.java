package rs.projecta.level;

public class Level
{
  public String Get_Next_Level()
  {
    return null;
  }
  
  public String Get_Title()
  {
    return null;
  }
  
  public void Build(rs.projecta.world.World w)
  {
    
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
}
