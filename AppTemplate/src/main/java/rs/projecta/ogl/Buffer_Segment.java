package rs.projecta.ogl;

public class Buffer_Segment
{
  public int mode;
  public int pt_start;
  public int pt_count;
  
  public Buffer_Segment(int mode, int pt_start, int pt_count)
  {
    this.mode = mode;
    this.pt_start = pt_start;
    this.pt_count = pt_count;
  }
}
