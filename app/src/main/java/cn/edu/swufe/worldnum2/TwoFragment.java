package cn.edu.swufe.worldnum2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.support.v4.content.ContextCompat.getSystemService;

public class TwoFragment  extends Fragment {
    public static TwoFragment newInstance() {
        return new TwoFragment();
    }
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private TextView text;
    private EditText inp;
    private ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.two_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        text = getView().findViewById(R.id.txtlabel);
        img = getView().findViewById(R.id.imageView);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sensorManager != null){
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            int medunValue = 10;
            if(Math.abs(x) > medunValue || Math.abs(y) > medunValue || Math.abs(z) > medunValue){
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = 10;
                handler.sendMessage(msg);

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            inp=getView().findViewById(R.id.inp);
            String food = inp.getText().toString();
            String[] eat = food.split("，");
            switch (msg.what){
                case 10:
                    java.util.Random r = new java.util.Random();
                    int num = Math.abs(r.nextInt())%(eat.length);
                    text.setText("今天吃"+eat[num]+"la!");
                    img.setImageResource(R.mipmap.p2);
                    break;
            }
        }
    };
}


