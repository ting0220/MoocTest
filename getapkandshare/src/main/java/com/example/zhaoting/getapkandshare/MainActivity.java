package com.example.zhaoting.getapkandshare;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bean> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            switch (msg.what) {
                case 0:
                    mAdapter.setList(mList);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ApkHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });

        //获取手机中已经安装的应用
        //b.判断(applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)的值，该值大于0时，表示获取的应用为系统预装的应用，反之则为手动安装的应用
//        final List<PackageInfo> packageInfoList = this.getPackageManager().getInstalledPackages(0);
        final List<ResolveInfo> list = getResolveInfo(this.getPackageManager());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Bean bean = new Bean();
                    bean.setName((String) list.get(i).loadLabel(MainActivity.this.getPackageManager()));
                    bean.setIcon(list.get(i).loadIcon(MainActivity.this.getPackageManager()));
                    mList.add(bean);
//                    ApplicationInfo info = getApplicationInfo(packageInfoList.get(i));
//                    if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
//                        bean.setIcon(getIcon(info, MainActivity.this.getPackageManager()));
//                        bean.setName(getLabel(info, MainActivity.this.getPackageManager()));
//                        bean.setSize(getApkSize(info));
//                        bean.setTime(getLastUpdateTime(packageInfoList.get(i)));
//                        mList.add(bean);
//                    }
                }
                mHandler.sendEmptyMessage(0);
            }
        }).start();

    }

    public ApplicationInfo getApplicationInfo(PackageInfo info) {
        return info.applicationInfo;
    }

    //
    public String getLabel(ApplicationInfo info, PackageManager manager) {
        return (String) manager.getApplicationLabel(info);

    }

    public Drawable getIcon(ApplicationInfo info, PackageManager manager) {
        return manager.getApplicationIcon(info);
    }

    //
    public long getLastUpdateTime(PackageInfo info) {
        return info.lastUpdateTime;
    }

    public int getApkSize(ApplicationInfo info) {
        File apkFile = new File(info.sourceDir);
        return (int) (apkFile.length() / 1024);
    }


    public List<ResolveInfo> getResolveInfo(PackageManager manager) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("android.intent.category.LAUNCHER");
        return manager.queryIntentActivities(mainIntent, 0);
    }
}


