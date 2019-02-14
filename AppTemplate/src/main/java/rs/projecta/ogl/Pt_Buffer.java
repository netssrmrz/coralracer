package rs.projecta.ogl;

public class Pt_Buffer
{
  public float[] points;
  public java.nio.FloatBuffer b;
  public int pt_count;
  public int mode;
  
  public Pt_Buffer(float[] points)
  {
    this.mode = android.opengl.GLES20.GL_LINE_LOOP;
    this.points = points;
    this.pt_count = this.points.length / 2;
    this.b = java.nio.ByteBuffer.allocateDirect(this.points.length * 4)
          .order(java.nio.ByteOrder.nativeOrder())
          .asFloatBuffer();
    this.b.put(this.points);
    this.b.position(0);
  }
}
