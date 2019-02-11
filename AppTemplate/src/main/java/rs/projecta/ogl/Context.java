package rs.projecta.ogl;

public class Context
{
  public int col_loc, mat_loc, att_loc;
  public rs.projecta.ogl.Matrix_Stack proj;
  
  public Context()
  {
    this.proj = new rs.projecta.ogl.Matrix_Stack();
  }
  
  public void Init()
  {
    String source;
    int v_shader_id, f_shader_id, prog_id;
    java.nio.FloatBuffer b;
  
    //android.util.Log.d("onSurfaceCreated()", "Entered");
    android.opengl.GLES20.glClearColor(0, 0, 0, 0.13f);
  
    source =
      "uniform mat4 u_Matrix;" +
        "attribute vec4 a_Position; " +
        "void main(){ gl_Position=u_Matrix*a_Position; }";
    v_shader_id = this.Compile_Shader(android.opengl.GLES20.GL_VERTEX_SHADER, source);
  
    source =
      "precision mediump float;" +
        "uniform vec4 u_Color;" +
        "void main(){ gl_FragColor=u_Color; }";
    f_shader_id = this.Compile_Shader(android.opengl.GLES20.GL_FRAGMENT_SHADER, source);
  
    if (v_shader_id != 0 && f_shader_id != 0)
    {
      prog_id = this.Link_Program(v_shader_id, f_shader_id);
      // dev only
      this.Validate_Program(prog_id);
      android.opengl.GLES20.glUseProgram(prog_id);
    
      col_loc = android.opengl.GLES20.glGetUniformLocation(prog_id, "u_Color");
      mat_loc = android.opengl.GLES20.glGetUniformLocation(prog_id, "u_Matrix");
      att_loc = android.opengl.GLES20.glGetAttribLocation(prog_id, "a_Position");
    
      android.opengl.GLES20.glLineWidth(8);
    }
  }
  
  public void Validate_Program(int prog_id)
  {
    int[] status;
    String log;
    
    android.opengl.GLES20.glValidateProgram(prog_id);
    
    status = new int[1];
    android.opengl.GLES20.glGetProgramiv(prog_id, android.opengl.GLES20.GL_VALIDATE_STATUS, status, 0);
    /*if (status[0]==0)
    {
      log = android.opengl.GLES20.glGetProgramInfoLog(prog_id);
      android.util.Log.d("Validate_Program()", log);
    }*/
  }
  
  public int Compile_Shader(int shader_type, String source)
  {
    int shader_id;
    int[] status;
    String log;
    
    shader_id = android.opengl.GLES20.glCreateShader(shader_type);
    if (shader_id != 0)
    {
      android.opengl.GLES20.glShaderSource(shader_id, source);
      android.opengl.GLES20.glCompileShader(shader_id);
      
      status = new int[1];
      android.opengl.GLES20.glGetShaderiv(shader_id,
        android.opengl.GLES20.GL_COMPILE_STATUS, status, 0);
      if (status[0] == 0)
      {
        log = android.opengl.GLES20.glGetShaderInfoLog(shader_id);
        android.opengl.GLES20.glDeleteShader(shader_id);
        android.util.Log.d("Compile_Shader()", log);
        shader_id = 0;
      }
    }
    return shader_id;
  }
  
  public int Link_Program(int v_shader_id, int f_shader_id)
  {
    int prog_id = 0;
    int[] status;
    String log;
    
    prog_id = android.opengl.GLES20.glCreateProgram();
    if (prog_id != 0)
    {
      android.opengl.GLES20.glAttachShader(prog_id, v_shader_id);
      android.opengl.GLES20.glAttachShader(prog_id, f_shader_id);
      android.opengl.GLES20.glLinkProgram(prog_id);
      
      status = new int[1];
      android.opengl.GLES20.glGetProgramiv(prog_id,
        android.opengl.GLES20.GL_LINK_STATUS, status, 0);
      if (status[0] == 0)
      {
        log = android.opengl.GLES20.glGetProgramInfoLog(prog_id);
        android.opengl.GLES20.glDeleteProgram(prog_id);
        android.util.Log.d("Link_Program()", log);
        prog_id = 0;
      }
    }
    return prog_id;
  }
}
