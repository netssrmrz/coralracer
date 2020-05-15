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
    world.Set_Listener(this);
  }
  
  public void Register()
  {
    android.hardware.SensorManager man;

    man = (android.hardware.SensorManager)this.ctx.getSystemService(android.content.Context.SENSOR_SERVICE);
    
    this.rot_sensor = Register_Sensor_Listener(man, android.hardware.Sensor.TYPE_ROTATION_VECTOR);
    if (this.rot_sensor == null)
    {
      this.mag_sensor = Register_Sensor_Listener(man, android.hardware.Sensor.TYPE_MAGNETIC_FIELD);
      this.acc_sensor = Register_Sensor_Listener(man, android.hardware.Sensor.TYPE_ACCELEROMETER);
    }
	}
	
	public android.hardware.Sensor Register_Sensor_Listener(android.hardware.SensorManager man, int sensor_type)
  {
    android.hardware.Sensor sensor;
  
    sensor = man.getDefaultSensor(sensor_type);
    if (sensor != null)
    {
      man.registerListener(this, sensor, android.hardware.SensorManager.SENSOR_DELAY_GAME);
      android.util.Log.i("Tilt_Manager",
        "Tilt_Manager.Register_Sensor_Listener(): using sensor " + this.getSensorName(sensor_type));
    }
    else
    {
      android.util.Log.w("Tilt_Manager",
        "Tilt_Manager.Register_Sensor_Listener(): sensor " + this.getSensorName(sensor_type) + " not found");
    }
    return sensor;
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
    
    if (!res)
    {
      android.util.Log.w("Tilt_Manager", "Tilt_Manager.Log_Sensors(): No tilt sensors found");
      Log_Sensors(sensors);
    }
  
    return res;
  }
  
	static public void Log_Sensors(java.util.List<android.hardware.Sensor> sensors)
  {
    if (sensors != null)
    {
      for (android.hardware.Sensor sensor: sensors)
      {
        android.util.Log.i("Tilt_Manager", "Tilt_Manager.Log_Sensors(): " + sensor.getName());
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
      this.Turn_Player();
    }
    else if (this.rot_vals != null)
    {
      android.hardware.SensorManager.getRotationMatrixFromVector(rot_matrix , this.rot_vals);
      this.Turn_Player();
    }
	}
  
  public void On_World_State_Change(rs.projecta.world.World w, int state)
  {
    if (state==rs.projecta.world.World.STATE_INIT)
      this.player=w.objs.Get_Player();
  }
  
  public void Turn_Player()
  {
    android.hardware.SensorManager.getOrientation(this.rot_matrix, this.orientation);
  
    if (this.orig_orient==null)
      this.orig_orient=java.util.Arrays.copyOf(this.orientation, 3);
  
    this.diff_orient[0]=this.orientation[0]-this.orig_orient[0];
    this.diff_orient[1]=this.orientation[1]-this.orig_orient[1];
    this.diff_orient[2]=this.orientation[2]-this.orig_orient[2];
  
    if (this.player!=null)
      this.player.Turn(this.diff_orient[2]);
  }
  
  public String getSensorName(int value)
  {
    String mStringType = "Unknown";
    
    switch (value)
    {
      case android.hardware.Sensor.TYPE_ACCELEROMETER:
        mStringType = android.hardware.Sensor.STRING_TYPE_ACCELEROMETER;
        break;
      case android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE:
        mStringType = android.hardware.Sensor.STRING_TYPE_AMBIENT_TEMPERATURE;
        break;
      case android.hardware.Sensor.TYPE_GAME_ROTATION_VECTOR:
        mStringType = android.hardware.Sensor.STRING_TYPE_GAME_ROTATION_VECTOR;
        break;
      case android.hardware.Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
        mStringType = android.hardware.Sensor.STRING_TYPE_GEOMAGNETIC_ROTATION_VECTOR;
        break;
      //case android.hardware.Sensor.TYPE_GLANCE_GESTURE:
        //mStringType = android.hardware.Sensor.STRING_TYPE_GLANCE_GESTURE;
        //break;
      case android.hardware.Sensor.TYPE_GRAVITY:
        mStringType = android.hardware.Sensor.STRING_TYPE_GRAVITY;
        break;
      case android.hardware.Sensor.TYPE_GYROSCOPE:
        mStringType = android.hardware.Sensor.STRING_TYPE_GYROSCOPE;
        break;
      case android.hardware.Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
        mStringType = android.hardware.Sensor.STRING_TYPE_GYROSCOPE_UNCALIBRATED;
        break;
      case android.hardware.Sensor.TYPE_HEART_RATE:
        mStringType = android.hardware.Sensor.STRING_TYPE_HEART_RATE;
        break;
      case android.hardware.Sensor.TYPE_LIGHT:
        mStringType = android.hardware.Sensor.STRING_TYPE_LIGHT;
        break;
      case android.hardware.Sensor.TYPE_LINEAR_ACCELERATION:
        mStringType = android.hardware.Sensor.STRING_TYPE_LINEAR_ACCELERATION;
        break;
      case android.hardware.Sensor.TYPE_MAGNETIC_FIELD:
        mStringType = android.hardware.Sensor.STRING_TYPE_MAGNETIC_FIELD;
        break;
      case android.hardware.Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
        mStringType = android.hardware.Sensor.STRING_TYPE_MAGNETIC_FIELD_UNCALIBRATED;
        break;
      //case android.hardware.Sensor.TYPE_PICK_UP_GESTURE:
        //mStringType = android.hardware.Sensor.STRING_TYPE_PICK_UP_GESTURE;
        //break;
      case android.hardware.Sensor.TYPE_PRESSURE:
        mStringType = android.hardware.Sensor.STRING_TYPE_PRESSURE;
        break;
      case android.hardware.Sensor.TYPE_PROXIMITY:
        mStringType = android.hardware.Sensor.STRING_TYPE_PROXIMITY;
        break;
      case android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY:
        mStringType = android.hardware.Sensor.STRING_TYPE_RELATIVE_HUMIDITY;
        break;
      case android.hardware.Sensor.TYPE_ROTATION_VECTOR:
        mStringType = android.hardware.Sensor.STRING_TYPE_ROTATION_VECTOR;
        break;
      case android.hardware.Sensor.TYPE_SIGNIFICANT_MOTION:
        mStringType = android.hardware.Sensor.STRING_TYPE_SIGNIFICANT_MOTION;
        break;
      case android.hardware.Sensor.TYPE_STEP_COUNTER:
        mStringType = android.hardware.Sensor.STRING_TYPE_STEP_COUNTER;
        break;
      case android.hardware.Sensor.TYPE_STEP_DETECTOR:
        mStringType = android.hardware.Sensor.STRING_TYPE_STEP_DETECTOR;
        break;
      //case android.hardware.Sensor.TYPE_TILT_DETECTOR:
        //mStringType = android.hardware.Sensor.SENSOR_STRING_TYPE_TILT_DETECTOR;
        //break;
      //case android.hardware.Sensor.TYPE_WAKE_GESTURE:
        //mStringType = android.hardware.Sensor.STRING_TYPE_WAKE_GESTURE;
        //break;
      case android.hardware.Sensor.TYPE_ORIENTATION:
        mStringType = android.hardware.Sensor.STRING_TYPE_ORIENTATION;
        break;
      case android.hardware.Sensor.TYPE_TEMPERATURE:
        mStringType = android.hardware.Sensor.STRING_TYPE_TEMPERATURE;
        break;
      //case android.hardware.Sensor.TYPE_DEVICE_ORIENTATION:
        //mStringType = android.hardware.Sensor.STRING_TYPE_DEVICE_ORIENTATION;
        //break;
      //case android.hardware.Sensor.TYPE_DYNAMIC_SENSOR_META:
        //mStringType = android.hardware.Sensor.STRING_TYPE_DYNAMIC_SENSOR_META;
        //break;
      case android.hardware.Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
        mStringType = android.hardware.Sensor.STRING_TYPE_LOW_LATENCY_OFFBODY_DETECT;
        break;
      case android.hardware.Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
        mStringType = android.hardware.Sensor.STRING_TYPE_ACCELEROMETER_UNCALIBRATED;
        break;
      default:
        break;
    }
    
    return mStringType;
  }
}
