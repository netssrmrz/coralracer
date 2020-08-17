package rs.projecta.view;

public class OpenGL_View
extends android.opengl.GLSurfaceView
implements
  android.opengl.GLSurfaceView.Renderer,
  rs.projecta.view.Game_View,
  rs.projecta.world.World_Step_Listener
{
  public rs.projecta.ogl.Context ogl_ctx;
  public rs.projecta.world.World world;
  public rs.projecta.Debug_Renderer debug_renderer;
  public Object camera;
  public float scale, w, h;

  public OpenGL_View(android.content.Context ctx, rs.projecta.world.World world)
  {
    super(ctx);
    
    world.Set_Listener(this);

    this.ogl_ctx = new rs.projecta.ogl.Context();
    this.setEGLContextClientVersion(2);
    this.setRenderer(this);
    this.Init(world);
  }

  public void Init(rs.projecta.world.World w)
  {
    this.world = w;

    if (this.world.debug)
    {
      this.debug_renderer = new rs.projecta.Debug_Renderer(this.world.phys_scale);
      this.world.phys_world.setDebugDraw(this.debug_renderer);
    }
  }

  @Override
  public void onSurfaceCreated(javax.microedition.khronos.opengles.GL10 p1, javax.microedition.khronos.egl.EGLConfig p2)
  {
    this.ogl_ctx.Init();
  }

  @Override
  public void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 p1, int w, int h)
  {
    //android.util.Log.d("Game2_View.onSurfaceChanged()", "Entered");
    android.opengl.GLES20.glViewport(0, 0, w, h);
    this.w = w;
    this.h = h;
    this.scale = -(0.0003f * (this.w + this.h) + 0.0928f);
    this.ogl_ctx.proj.Init(w, h);
  }
  
  @Override
  public void surfaceDestroyed(android.view.SurfaceHolder s)
  {
    //android.util.Log.d("surfaceDestroyed()", "Entered");
  }
  
  @Override
  public void onDrawFrame(javax.microedition.khronos.opengles.GL10 p1)
  {
    //android.util.Log.d("Game2_View.Draw_World_Step()", "Entered");
    this.world.Update();
  
    this.ogl_ctx.proj.Save();
    android.opengl.Matrix.translateM(this.ogl_ctx.proj.vals, 0, this.w / 2f, this.h / 2f, 0);
    android.opengl.Matrix.scaleM(this.ogl_ctx.proj.vals, 0, this.scale, this.scale, 1f);
  
    if (this.camera instanceof rs.projecta.object.features.Has_Direction)
      android.opengl.Matrix.rotateM(
        this.ogl_ctx.proj.vals, 0,
        -((rs.projecta.object.features.Has_Direction) this.camera).Get_Angle_Degrees(),
        0, 0, 1);
    
    if (this.camera instanceof rs.projecta.object.features.Has_Position)
      android.opengl.Matrix.translateM(
        this.ogl_ctx.proj.vals, 0,
        -((rs.projecta.object.features.Has_Position) this.camera).Get_X(),
        -((rs.projecta.object.features.Has_Position) this.camera).Get_Y(),
        0);
  
    android.opengl.GLES20.glClear(android.opengl.GLES20.GL_COLOR_BUFFER_BIT);
    this.world.objs.Draw_OpenGL(this);

    this.ogl_ctx.proj.Restore();;
  }
  
  public Object Get_Camera()
  {
    return camera;
  }
  
  public void On_World_State_Change(rs.projecta.world.World w, int state)
  {
    if (state==rs.projecta.world.World.STATE_INIT)
    {
      this.camera = w.objs.Get_Player();
    }
  }
  
  @Override
  public void onResume()
  {
    this.world.Init_Sound();
  }
}
