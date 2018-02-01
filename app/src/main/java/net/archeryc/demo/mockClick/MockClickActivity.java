package net.archeryc.demo.mockClick;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.archeryc.demo.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MockClickActivity extends AppCompatActivity {

    private ConstraintLayout clRoot;
    private Button btnStart;
    private TextView tvPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_click);
        clRoot=findViewById(R.id.cl_root);
        tvPosition=findViewById(R.id.tv_position);
        btnStart=findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnStart.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        execShellCmd("input tap 1100 550");
//                    }
//                },3000);
            }
        });
        clRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    tvPosition.setText("x:"+event.getRawX()+"\ty:"+event.getRawY()+
                            "\n按钮位置\t"+"x:"+btnStart.getX()+"\ty:"+btnStart.getY());
                }
                return false;
            }
        });
    }

    private void execShellCmd(String cmd){
        try {
            Process process=Runtime.getRuntime().exec("su");

            OutputStream outputStream=process.getOutputStream();
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
