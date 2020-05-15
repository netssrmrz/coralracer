package rs.projecta.ogl;

public class Pt_Buffer
{
  public float[] points;
  public java.nio.FloatBuffer b;
  public rs.projecta.ogl.Buffer_Frame frames[];
  
  public Pt_Buffer(float[] points)
  {
    this.frames = new Buffer_Frame[1];
    this.frames[0] = new Buffer_Frame(android.opengl.GLES20.GL_LINE_LOOP, 0, points.length / 2);

    this.points = points;
    this.b = java.nio.ByteBuffer.allocateDirect(this.points.length * 4)
          .order(java.nio.ByteOrder.nativeOrder())
          .asFloatBuffer();
    this.b.put(this.points);
    this.b.position(0);
  }
}
