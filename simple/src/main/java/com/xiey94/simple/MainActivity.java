package com.xiey94.simple;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xiey94.xydialog.dialog.XyDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tiShi(View v) {
        //构建
        new XyDialog.Builder(this)
                //标题
                .setTitle("提示框")
                //内容
                .setMessage("提示消息内容")
                //确定按钮
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                //创建提示
                .createWarn()
                .show();
    }

    public void onEdit(View v) {
        //构建
        new XyDialog.Builder(this)
                //标题
                .setTitle("输入框")
                //输入提示
                .setHint("请输入内容")
                //输入内容是否可见
                .setIsShow(true)
                //是否限制输入类型（目前只支持输入数字和字母）
                .setIsInputType(false)
                //确定按钮
                .setPositiveButton("确定", new XyDialog.OnDialogEditClickListener() {
                    @Override
                    public void onPositiveButonListener(EditText input, Dialog dialog, int confirm) {
                        Toast.makeText(MainActivity.this, input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                //创建输入
                .createEdit()
                .show();
    }

    public void onChooseOne(View v) {
        List<String> list = new ArrayList<String>();
        list.add("选项一");
        list.add("选项二");
        list.add("选项三");
        list.add("选项四");
        list.add("选项五");
        new XyDialog.Builder(this)
                .setTitle("单选")
                .setOnChooseOneButton(list, new XyDialog.OnDialogChooseClickListener() {
                    @Override
                    public void onChoose(TextView choose, Dialog dialog,int whitch) {
                        Toast.makeText(MainActivity.this, "" +whitch+"--"+ choose.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createChoose()
                .show();

    }
}
