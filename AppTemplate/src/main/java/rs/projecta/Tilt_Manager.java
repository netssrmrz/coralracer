package rs.projecta;

public class Tilt_Manager
implements
  android.hardware.SensorEventListener,
  rs.projecta.world.World_Step_Listener
{
  public android.hardware.Sensor mag_sensor, acc_sensor, rot_sensor;
	public float[] mag_vals, acc_vals, rot_vals, orig_orient, rot_matrix, orientation, diff_orient;
  public android.content.Context ctx;
  public rs.projecta.object.Player player;
  
  public Tilt_Manager(android.content.Context ctx, rs.projecta.world.World world)
  {
    this.ctx=ctx;
    this.rot_matrix = new float[9];
    this.orientation = new float[3];
    this.diff_orient=new float[3];
    //this.player=world.objs.Get_Player();
    world.Set_Listener(this);
  }
  
  public void Register()
  {
    android.hardware.SensorManager man;

    man = (android.hardware.SensorManager)this.ctx.getSystemService(android.content.Context.SENSOR_SERVICE);
    //this.Log_Sensors(man);
    
    this.mag_sensor = man.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD);
    if (this.mag_sensor != null)
      man.registerListener(this, this.mag_sensor, android.hardware.SensorManager.SENSOR_DELAY_GAME);
    else
      android.util.Log.w("Tilt_Manager.Register()", "sensor android.hardware.Sensor.TYPE_MAGNETIC_FIELD not found");
  
    this.acc_sensor = man.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
    if (this.acc_sensor != null)
      man.registerListener(this, this.acc_sensor, android.hardware.SensorManager.SENSOR_DELAY_GAME);
    else
      android.util.Log.w("Tilt_Manager.Register()", "sensor android.hardware.Sensor.TYPE_ACCELEROMETER not found");
  
    this.rot_sensor = man.getDefaultSensor(android.hardware.Sensor.TYPE_ROTATION_VECTOR);
    if (this.rot_sensor != null)
      man.registerListener(this, this.rot_sensor, android.hardware.SensorManager.SENSOR_DELAY_GAME);
    else
      android.util.Log.w("Tilt_Manager.Register()", "sensor android.hardware.Sensor.TYPE_ROTATION_VECTOR not found");
	}
 
	static public boolean Has_Tilt_Sensors(android.content.Context ctx)
  {
    boolean res = false;
    android.hardware.SensorManager man =
      (android.hardware.SensorManager)ctx.getSystemService(android.content.Context.SENSOR_SERVICE);
    java.util.List<android.hardware.Sensor> sensors = man.getSensorList(android.hardware.Sensor.TYPE_ALL);
    java.util.ArrayList<Integer> sensor_types = new java.util.ArrayList<Integer>();
  
    if (sensors != null)
    {
      for (android.hardware.Sensor sensor: sensors)
      {
        sensor_types.add(sensor.getType());
      }
  
      if (sensor_types.contains(android.hardware.Sensor.TYPE_MAGNETIC_FIELD) &&
            sensor_types.contains(android.hardware.Sensor.TYPE_ACCELEROMETER))
      {
        res = true;
      }
      else if (sensor_types.contains(android.hardware.Sensor.TYPE_ROTATION_VECTOR))
      {
        res = true;
      }
    }
  
    return res;
  }
  
	public void Log_Sensors(android.hardware.SensorManager man)
  {
    java.util.List<android.hardware.Sensor> sensors = man.getSensorList(android.hardware.Sensor.TYPE_ALL);
  
    if (sensors != null)
    {
      for (android.hardware.Sensor sensor: sensors)
      {
        //sensor_types.add(sensor.getType());
      }
    }
  }
  
  public void Unregister()
  {
    android.hardware.SensorManager man;

    man = (android.hardware.SensorManager)this.ctx.getSystemService(android.content.Context.SENSOR_SERVICE);
    man.unregisterListener(this);
	}
  
  public void onAccuracyChanged(android.hardware.Sensor p1, int p2)
  {
    // TODO: Implement this method
  }
  
  public void onSensorChanged(android.hardware.SensorEvent e)
  {
    if (e.sensor == this.mag_sensor)
      this.mag_vals = e.values;
    else if (e.sensor == this.acc_sensor)
      this.acc_vals = e.values;
    else if (e.sensor == this.rot_sensor)
      this.rot_vals = e.values;
    
    if (this.mag_vals != null && this.acc_vals != null)
    {
      android.hardware.SensorManager.getRotationMatrix(rot_matrix, null, this.acc_vals, this.mag_vals);
      android.hardware.SensorManager.getOrientation(rot_matrix, orientation);

      if (this.orig_orient==null)
        this.orig_orient=java.util.Arrays.copyOf(orientation, 3);

      this.diff_orient[0]=this.orientation[0]-this.orig_orient[0];
      this.diff_orient[1]=this.orientation[1]-this.orig_orient[1];
      this.diff_orient[2]=this.orientation[2]-this.orig_orient[2];
  
      if (this.player!=null)
        this.player.Turn(this.diff_orient[2]);
    }
    else if (this.rot_vals != null)
    {
      android.hardware.SensorManager.getRotationMatrixFromVector(rot_matrix , this.rot_vals);
      android.hardware.SensorManager.getOrientation(rot_matrix, orientation);
  
      if (this.orig_orient==null)
        this.orig_orient=java.util.Arrays.copyOf(orientation, 3);
  
      this.diff_orient[0]=this.orientation[0]-this.orig_orient[0];
      this.diff_orient[1]=this.orientation[1]-this.orig_orient[1];
      this.diff_orient[2]=this.orientation[2]-this.orig_orient[2];
  
      if (this.player!=null)
        this.player.Turn(this.diff_orient[2]);
    }
	}
  
  public void On_World_State_Change(rs.projecta.world.World w, int state)
  {
    if (state==rs.projecta.world.World.STATE_INIT)
      this.player=w.objs.Get_Player();
  }
}
