package net.archeryc.demo.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import net.archeryc.demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yc
 */
public class DownloadActivity extends AppCompatActivity {

    String downloadUrl = "http://caidian365.qianfanapi.com/v3_0/user/authcode";

    SimpleDraweeView sdvCode;

    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        okHttpClient = new OkHttpClient();

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .build();
        Fresco.initialize(this, config);

        setContentView(R.layout.activity_download);

        sdvCode = findViewById(R.id.sdvCode);
        sdvCode.setImageURI(Uri.parse(downloadUrl));

    }

    public void downloadManager(View view) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setTitle("下载图片");

        request.setTitle("验证码测试");

        File saveFile = new File(Environment.getExternalStorageDirectory(), "aaaa.png");
        request.setDestinationUri(Uri.fromFile(saveFile));

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        manager.enqueue(request);
    }

    public void okHttp(View view) {
//创建一个Request
        final Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
//new call
        Call call = okHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("hhh", "下载成功");
                Log.d("hhh","code---->"+response.message());
                //String htmlStr =  response.body().string();
                writeToLocal(Environment.getExternalStorageDirectory() + "/bbbb.jpg", response.body().byteStream());
            }
        });
    }

    /**
     * 将InputStream写入本地文件
     *
     * @param destination 写入本地目录
     * @param input       输入流
     * @throws IOException
     */
    private static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[4 * 1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
