package rs.projecta.ogl;

public class Buffer_Frame
{
  public rs.projecta.ogl.Buffer_Segment segments[];
  
  public Buffer_Frame(int mode, int pt_start, int pt_count)
  {
    this.segments = new Buffer_Segment[1];
    this.segments[0] = new Buffer_Segment(mode, pt_start, pt_count);
  }
}
