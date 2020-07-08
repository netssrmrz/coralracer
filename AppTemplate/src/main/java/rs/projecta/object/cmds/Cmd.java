package rs.projecta.object.cmds;

public class Cmd
{
  public final static int TYPE_TURN_TO = 1;
  public final static int TYPE_PUSH = 2;
  
  public int type;
  public float val;
  
  public Cmd(int type, float val)
  {
    this.type = type;
    this.val = val;
  }
}
