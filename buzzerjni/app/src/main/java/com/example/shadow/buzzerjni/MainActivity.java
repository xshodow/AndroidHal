package com.example.shadow.buzzerjni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import hardlib.hardbuzzer;
public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    hardbuzzer buzzer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int fd=0;
        // Example of a call to a native method
        Button   btn_open=(Button)findViewById(R.id.bnt_buzzeropen);
        Button   btn_close=(Button)findViewById(R.id.btn_buzzerclose);


        buzzer = new hardbuzzer();
        fd=buzzer.buzzerfdopen("/dev/buzzer_ctl");
        if (fd<=0)
        {
            Log.i("buzzerfd", "buzzerfd open err");
        }
        btn_open.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //匿名内部类
                Log.i("btn_open", "onClick: btn_open");
                buzzer.buzzerioctl(1);
            }
        });

        btn_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //匿名内部类
                Log.i("btn_close", "onClick: btn_close");
                buzzer.buzzerioctl(0);
            }
        });

//        tv.setText(stringFromJNI());
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}
