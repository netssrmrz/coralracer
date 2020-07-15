package rs.projecta.world;

public class World
implements 
  org.jbox2d.callbacks.ContactListener,
  android.media.SoundPool.OnLoadCompleteListener
{
  public org.jbox2d.dynamics.World phys_world;
  public float phys_scale;
  public String debug_msg[];
  public boolean debug, prof;
  public rs.projecta.level.Level level;
  public java.util.Vector<rs.projecta.world.World_Step_Listener> world_step_listeners;
  public java.util.Vector<rs.projecta.world.World_Update_Listener> world_update_listeners;
  public rs.projecta.world.Object_List objs;
  public int state, hint;
  public boolean do_processing;
  public java.util.Random rnd;
  public long last_update, lapsed_time;
  public android.media.SoundPool sounds;
  public int soundid_whack, soundid_start, soundid_door;
  public android.content.Context ctx;

  public static final int STATE_NONE=0;
  public static final int STATE_PLAY=1;
  public static final int STATE_LEVELCOMPLETE=2;
  public static final int STATE_QUIT=3;
  public static final int STATE_LEVELFAIL=4;
  public static final int STATE_PAUSE=4;
  public static final int STATE_STEP=5;
  public static final int STATE_INIT=6;
  
  public static final int HINT_ES2=1;
  public static final int HINT_NONE=0;
  
  public World(android.content.Context ctx, rs.projecta.level.Level level, int hint)
  {
    //this.debug=true;
    //this.prof=true;
    this.hint = hint;
    this.ctx = ctx;
    this.debug_msg = new String[5];
    this.world_step_listeners = new java.util.Vector<rs.projecta.world.World_Step_Listener>();
    this.world_update_listeners = new java.util.Vector<rs.projecta.world.World_Update_Listener>();

    //this.Init_Sound();
    this.Init_Level(level);
  }
  
  public void Set_Listener(rs.projecta.world.World_Step_Listener l)
  {
    this.world_step_listeners.add(l);
    l.On_World_State_Change(this, STATE_INIT);
  }
  
  public void Set_Listener(rs.projecta.world.World_Update_Listener l)
  {
    this.world_update_listeners.add(l);
  }
  
  public void Init_Sound()
  {
    android.media.SoundPool.Builder b;
    android.media.AudioAttributes.Builder a;

    if (this.sounds == null)
    {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
      {
        a = new android.media.AudioAttributes.Builder();
        a.setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION);
        a.setUsage(android.media.AudioAttributes.USAGE_GAME);

        b = new android.media.SoundPool.Builder();
        b.setAudioAttributes(a.build());
        b.setMaxStreams(3);
        this.sounds = b.build();
      }
      else
      {
        this.sounds = new android.media.SoundPool(1, android.media.AudioManager.STREAM_MUSIC, 0);
      }

      this.soundid_whack = this.sounds.load(ctx, rs.projecta.R.raw.whack, 1);
      this.soundid_start = this.sounds.load(ctx, rs.projecta.R.raw.start, 1);
      this.soundid_door = this.sounds.load(ctx, rs.projecta.R.raw.door, 1);
    }
  }
  
  public void Change_State(int state)
  {
    int i;
    
    if (state == rs.projecta.world.World.STATE_LEVELFAIL)
      this.Init_Level();
    else
      this.Notify_State_Change(state);
  }
  
  public void Notify_State_Change(int state)
  {
    int i;
    
    this.state = state;
    for (i=0; i<this.world_step_listeners.size(); i++)
      this.world_step_listeners.get(i).On_World_State_Change(this, state);
  }
  
  public void Notify_Update()
  {
    int i;
    
    for (i=0; i<this.world_update_listeners.size(); i++)
      this.world_update_listeners.get(i).On_World_Update(this);
  }

  public void beginContact(org.jbox2d.dynamics.contacts.Contact c)
  {
    Object obj;

    obj = c.getFixtureA().getBody().getUserData();
    if (obj != null && obj instanceof rs.projecta.object.features.Can_Collide)
    {
      ((rs.projecta.object.features.Can_Collide)obj).Contact(c);
      //android.util.Log.d("World", "beginContact(): body A is " + obj.getClass().getName());
    }
    
    obj = c.getFixtureB().getBody().getUserData();
    if (obj != null && obj instanceof rs.projecta.object.features.Can_Collide)
    {
      ((rs.projecta.object.features.Can_Collide)obj).Contact(c);
      //android.util.Log.d("World", "beginContact(): body B is " + obj.getClass().getName());
    }
  }

  public void endContact(org.jbox2d.dynamics.contacts.Contact p1)
  {
  }

  public void preSolve(org.jbox2d.dynamics.contacts.Contact p1, org.jbox2d.collision.Manifold p2)
  {
  }

  public void postSolve(org.jbox2d.dynamics.contacts.Contact p1, org.jbox2d.callbacks.ContactImpulse p2)
  {
  }

  public void Init_Level(rs.projecta.level.Level level)
  {
    this.level = level;
    this.Init_Level();
  }

  public void Init_Level()
  {
    int i;
    //android.util.Log.d("Init_Level()", "Entered");

    this.rnd = new java.util.Random(0);
    this.last_update = System.nanoTime();
    this.phys_world = new org.jbox2d.dynamics.World(new org.jbox2d.common.Vec2(0, 0));
    this.phys_world.setAllowSleep(true);
    this.phys_world.setContactListener(this);

    this.phys_scale = 20f;
    this.objs = new rs.projecta.world.Object_List(this);

    if (this.level != null)
      this.level.Build(this);

    this.Notify_State_Change(STATE_INIT);
  }
  
  public void Init_Next_Level()
  {
    Class<? extends rs.projecta.level.Level> next_level_class;
    rs.projecta.level.Level next_level;
  
    next_level_class = this.level.Get_Next_Level();
    next_level = rs.projecta.level.Level.Get(next_level_class);
    this.Init_Level(next_level);
  }
  
  public void Do_Processing()
  {
    long now;
    float sec_step;
    int i;
    //android.util.Log.d("World.run()", "Entered");

    if (this.prof)
      android.os.Debug.startMethodTracing("rs.projecta");

    this.do_processing = true;
    while (this.do_processing)
    {
      this.Update();
    }

    if (this.prof)
      android.os.Debug.stopMethodTracing();
  }
  
  public void Update()
  {
    long now;
    float sec_step;
    int i;
    
    now = System.nanoTime();
    this.lapsed_time = now - this.last_update;
    this.last_update = now;
  
    sec_step = this.lapsed_time / 1800000000f;
    this.phys_world.step(sec_step, 8, 8);
  
    this.Notify_Update();
  
    if (this.level != null)
      this.level.Update();
  
    this.objs.Process(sec_step); // remove and update
  }

  public String Gen_Level_Script()
  {
    String s;

    s =
      "package rs.projecta.level;\n\n" +
      "public class ???\n" +
      "extends Level\n" +
      "{\n" +
      "  @Override\n" +
      "  public String Get_Next_Level()\n" +
      "  {\n" +
      "    return rs.projecta.level.???.class.getName();\n" +
      "  }\n\n" +
      "  @Override\n" +
      "  public String Get_Title()\n" +
      "  {\n" +
      "    return \"???\";\n" +
      "  }\n\n" +
      "  @Override\n" +
      "  public String Get_Description()\n" +
      "  {\n" +
      "    return \"???\";\n" +
      "  }\n\n" +
      "  @Override\n" +
      "  public void Build(rs.projecta.World w)\n" +
      "  {\n";

    for (Object obj: this.objs.objs)
    {
      if (obj instanceof rs.projecta.object.Finish)
      {
        s += "    w.objs.add(new rs.projecta.object.Finish(w, " +
          ((rs.projecta.object.Finish)obj).Get_X() + ", " +
          ((rs.projecta.object.Finish)obj).Get_Y() + "));\n";
      }
    }
    /*w.objs.add(new rs.projecta.object.Finish(w, 0, -3800));
     w.objs.add(new rs.projecta.object.Wall(w, 0, -3900, 180, 10, 0)); // top
     w.objs.add(new rs.projecta.object.Wall(w, 200, -1900, 10, 2000, 0)); // right
     w.objs.add(new rs.projecta.object.Wall(w, -200, -1900, 10, 2000, 0)); // left
     w.objs.add(new rs.projecta.object.Wall(w, 0, 100, 180, 10, 0)); // bottom
     w.objs.add(new rs.projecta.object.Player(0, 0, w));
     w.objs.add(new rs.projecta.object.Background());*/
    s = s +
      "  }\n" +
      "}\n";
    return s;
  }

  public void onLoadComplete(android.media.SoundPool sounds, int id, int status)
  {
    if (status == 0)
      android.util.Log.d("World.onLoadComplete()", "sound load complete");
    else
      android.util.Log.d("World.onLoadComplete()", "sound load failed");
  }
  
  public org.jbox2d.common.Vec2 To_Phys_Pt(org.jbox2d.common.Vec2 pt)
  {
    pt.x = To_Phys_Dim(pt.x);
    pt.y = To_Phys_Dim(pt.y);
    return pt;
  }
  
  public float To_Phys_Dim(float d)
  {
    return d / this.phys_scale;
  }
  
  public org.jbox2d.dynamics.Body Add_Single_Fixture_Body
    (org.jbox2d.collision.shapes.Shape shape, float x, float y, float a_degrees,
     float bounce, boolean is_sensor, Object obj)
  {
    return Add_Single_Fixture_Body
             (shape, x, y, a_degrees, bounce, is_sensor, org.jbox2d.dynamics.BodyType.STATIC, obj);
  }

  public org.jbox2d.dynamics.Body Add_Single_Fixture_Body
    (org.jbox2d.collision.shapes.Shape shape, float x, float y, float a_degrees,
     float bounce, boolean is_sensor, org.jbox2d.dynamics.BodyType body_type, Object obj)
  {
    return Add_Single_Fixture_Body
             (shape, x, y, a_degrees, bounce, is_sensor, org.jbox2d.dynamics.BodyType.STATIC, obj, 1f);
  }
  
  public org.jbox2d.dynamics.Body Add_Single_Fixture_Body
    (org.jbox2d.collision.shapes.Shape shape, float x, float y, float a_degrees,
     float bounce, boolean is_sensor, org.jbox2d.dynamics.BodyType body_type, Object obj, float density)
  {
    org.jbox2d.dynamics.Body body;
    org.jbox2d.dynamics.FixtureDef fix_def;
  
    fix_def = Create_Fixture(shape, bounce, is_sensor, density);
    body = Create_Body(fix_def, x, y, a_degrees, body_type, obj);
  
    return body;
  }
  
  public static org.jbox2d.dynamics.FixtureDef Create_Fixture
    (org.jbox2d.collision.shapes.Shape shape, float bounce, boolean is_sensor, float density)
  {
    org.jbox2d.dynamics.FixtureDef fix_def;

    fix_def=new org.jbox2d.dynamics.FixtureDef();
    fix_def.shape=shape;
    fix_def.density=density;
    fix_def.friction=0;
    fix_def.restitution=bounce;
    fix_def.isSensor=is_sensor;
    
    return fix_def;
  }
  
  public org.jbox2d.dynamics.Body Create_Body
    (org.jbox2d.dynamics.FixtureDef fix_def, float x, float y, float a_degrees,
     org.jbox2d.dynamics.BodyType body_type, Object obj)
  {
    org.jbox2d.dynamics.BodyDef body_def;
    org.jbox2d.dynamics.Body body;
    org.jbox2d.common.Vec2 pos;

    pos = new org.jbox2d.common.Vec2(x, y);
  
    body_def=new org.jbox2d.dynamics.BodyDef();
    body_def.type=body_type;
    body_def.position=To_Phys_Pt(pos);
    body_def.angle=(float) Math.toRadians(a_degrees);
    body_def.userData=obj;
    body=phys_world.createBody(body_def);
    body.createFixture(fix_def);
    
    return body;
  }
  
  public static rs.projecta.object.Player Get_Player_Contact(org.jbox2d.dynamics.contacts.Contact c)
  {
    return (rs.projecta.object.Player)Get_Contact_Object_If_Type(c, rs.projecta.object.Player.class);
  }
  
  public static Object Get_Contact_Object_If_Type(org.jbox2d.dynamics.contacts.Contact c, Class<? extends Object> obj_class)
  {
    Object a, b, res=null;

    a = c.getFixtureA().getBody().getUserData();
    b = c.getFixtureB().getBody().getUserData();
  
    if (a != null && obj_class.isInstance(a))
      res=a;
    else if (b != null && obj_class.isInstance(b))
      res=b;
    
    return res;
  }
}
