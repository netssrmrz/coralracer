package rs.projecta.ogl;

public class Matrix_Stack
{
  public java.util.Stack<float[]> matrices;
  public float[] vals;
  
  public Matrix_Stack()
  {
    this.vals = new float[16];
    this.matrices = new java.util.Stack<float[]>();
  }
  
  public void Init(float w, float h)
  {
    android.opengl.Matrix.orthoM(vals, 0, 0, w, h, 0, -1, 1);
  }
  
  public void Save()
  {
    this.matrices.push(java.util.Arrays.copyOf(this.vals, this.vals.length));
  }
  
  public void Restore()
  {
    this.vals = this.matrices.pop();
  }
}
