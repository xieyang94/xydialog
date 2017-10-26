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
        new XyDialog.Builder(this)
                .setTitle("提示框")
                .setMessage("提示消息内容")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .createWarn()
                .show();
    }

    public void onEdit(View v) {
        new XyDialog.Builder(this)
                .setTitle("输入框")
                .setHint("请输入内容")
                .setIsShow(true)
                .setPositiveButton("确定", new XyDialog.OnDialogEditClickListener() {
                    @Override
                    public void onPositiveButonListener(EditText input, Dialog dialog, int confirm) {
                        Toast.makeText(MainActivity.this, input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
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
                    public void onChoose(TextView choose, Dialog dialog) {
                        Toast.makeText(MainActivity.this, "choose.getText():" + choose.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createChoose()
                .show();

    }
}
