package rs.projecta.world;

public class Object_List
{
  public java.util.ArrayList<Object> objs, del_objs;
  public World world;

  public Object_List(World w)
  {
    this.world = w;
    this.objs = new java.util.ArrayList<Object>();
    this.del_objs = new java.util.ArrayList<Object>();
  }

  public void Process(float sec_step)
  {
    for (Object obj: this.objs)
    {
      if (obj instanceof rs.projecta.object.features.Has_Auto_Movement)
        ((rs.projecta.object.features.Has_Auto_Movement)obj).Update(sec_step);
    }

    for (Object obj: this.del_objs)
    {
      this.objs.remove(obj);
      if (obj instanceof rs.projecta.object.features.Has_Cleanup)
        ((rs.projecta.object.features.Has_Cleanup)obj).Remove();
    }
    this.del_objs.clear();
  }

  public void Remove(Object obj)
  {
    this.del_objs.add(obj);
  }

  public void Add(Object obj)
  {
    this.objs.add(obj);
  }

  public Object Get_One(Class obj_class, boolean get_newest)
  {
    Object res=null;

    for (Object obj: this.objs)
    {
      if (obj.getClass() == obj_class && !this.del_objs.contains(obj))
      {
        res = obj;
        if (!get_newest)
          break;
      }
    }

    return res;
  }

  public rs.projecta.object.Player Get_Player()
  {
    return (rs.projecta.object.Player)this.Get_One(
      rs.projecta.object.Player.class, false);
  }

  public int Get_Count(Class<? extends Object> obj_class)
  {
    int res=0;

    for (Object o: this.objs)
    {
      if (o.getClass() == obj_class)
      {
        res++;
      }
    }
    return res;
  }

  public void Draw(rs.projecta.view.Game_View v, android.graphics.Canvas c)
  {
    for (Object o: this.objs)
    {
      if (o instanceof rs.projecta.object.features.Is_Drawable)
      {
        c.save();

        if (o instanceof rs.projecta.object.features.Has_Position)
          c.translate(
            ((rs.projecta.object.features.Has_Position)o).Get_X(),
            ((rs.projecta.object.features.Has_Position)o).Get_Y());

        if (o instanceof rs.projecta.object.features.Has_Direction)
          c.rotate(((rs.projecta.object.features.Has_Direction)o).Get_Angle_Degrees());

        ((rs.projecta.object.features.Is_Drawable)o).Draw(v, c);

        c.restore();
      }
    }
  }
  
  public void Draw_OpenGL(rs.projecta.view.OpenGL_View v)
  {
    float x=0, y=0, a=0;
  
    for (Object o: this.objs)
    {
      if (o instanceof rs.projecta.object.features.Is_Drawable)
      {
        v.ogl_ctx.proj.Save();
  
        if (o instanceof rs.projecta.object.features.Has_Position)
        {
          x = ((rs.projecta.object.features.Has_Position) o).Get_X();
          y = ((rs.projecta.object.features.Has_Position) o).Get_Y();
          android.opengl.Matrix.translateM(v.ogl_ctx.proj.vals, 0, x, y, 0);
        }
  
        if (o instanceof rs.projecta.object.features.Has_Direction)
        {
          a = ((rs.projecta.object.features.Has_Direction) o).Get_Angle_Degrees();
          android.opengl.Matrix.rotateM(v.ogl_ctx.proj.vals, 0, a, 0, 0, 1);
        }
  
        ((rs.projecta.object.features.Is_Drawable)o).Draw(v, null);
  
        v.ogl_ctx.proj.Restore();
      }
    }
  }
}
