package com.example.suzukisusumu_sist.facialrecognition;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class FacialRecognition extends AppCompatActivity {
    FaceDetector.Face faces[]=new FaceDetector.Face[10];
    Bitmap bitmap;
    FaceDetector detector;
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facial_recognition);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView faceNum = (TextView) findViewById(R.id.FaceNum);

        Resources r= getResources();
        Bitmap bitmap2 = BitmapFactory.decodeResource(r, R.drawable.test);


        bitmap = bitmap2.copy(Bitmap.Config.RGB_565, true);

        detector = new FaceDetector(bitmap.getWidth(),bitmap.getHeight(),faces.length);
        num=detector.findFaces(bitmap,faces);

        faceNum.setText(String.valueOf(num));
        img.setImageBitmap(bitmap);

        for(int i=0;i<num;i++){

            PointF point = new PointF();
            faces[i].getMidPoint(point);
            Log.d("FaceDetector","Number"+ i);
            Log.d("FaceDetector", "Confidence:" + faces[i].confidence());
            Log.d("FaceDetector", "MidPoint X:" + point.x);
            Log.d("FaceDetector", "MidPoint Y:" + point.y);
            Log.d("FaceDetector", "EyesDistance:" + faces[i].eyesDistance());

        }

    }
}

