package com.xiey94.simple;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xiey94.xydialog.dialog.XyDialog;
import com.xiey94.xydialog.dialog.XyDialog2;
import com.xiey94.xydialog.dialog.XySevDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String str = "";

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
                .setIsInputType(false)
                .setPositiveButton("确定", new XyDialog.OnDialogEditClickListener() {
                    @Override
                    public void onPositiveButonListener(EditText input, Dialog dialog, int confirm) {
                        str = input.getText().toString().trim();
                        Toast.makeText(MainActivity.this, input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createEdit()
                .show();

    }

    public void onChooseOne(View v) {
        new XyDialog.Builder(this)
                .setTitle("单选")
                .setOnChooseOneButton(R.array.select_dialog_change_pwd, new XyDialog.OnDialogChooseClickListener() {
                    @Override
                    public void onChoose(TextView choose, Dialog dialog, int whitch) {
                        Toast.makeText(MainActivity.this, "" + whitch + "--" + choose.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createChoose()
                .show();

    }

    public void tiShi2(View v) {
        new XyDialog2.Builder(this)
                .title("Dialog2")
                .message("这是第二种Dialog")
                .setPositiveButtonListener("确定", new XyDialog2.OnNoticeClickListener<Object>() {
                    @Override
                    public void onNotice(Object view, Dialog dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButtonListener("取消", new XyDialog2.OnNoticeClickListener<Object>() {
                    @Override
                    public void onNotice(Object view, Dialog dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .createNoticeDialog()
                .show();
    }

    public void onEdit2(View v) {
        new XyDialog2.Builder(this)
                .title("Dialog2-输入密码")
                .digit(6)
                .hint("最多输入6位")
                .isChar(true)
                .setPositiveButtonListener("确定", new XyDialog2.OnNoticeClickListener<EditText>() {
                    @Override
                    public void onNotice(EditText view, Dialog dialog, int which) {
                        String input = view.getText().toString().trim();
                        Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createPwdDialog()
                .show();
    }

    public void onChooseOne2(View v) {
        new XyDialog2.Builder(this)
                .title("单选")
                .setPositiveButtonListener(R.array.select_dialog_change_pwd, new XyDialog2.OnNoticeClickListener<TextView>() {
                    @Override
                    public void onNotice(TextView view, Dialog dialog, int which) {
                        Toast.makeText(MainActivity.this, "" + which + "--" + view.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createChooseButton()
                .show();
    }

    public void onChooseMul(View v) {
        new XyDialog2.Builder(this)
                .title("多选")
                .setPositiveButtonListener("确定", R.array.select_dialog_change_pwd, new XyDialog2.OnMulClickListener<List<CheckBox>>() {
                    @Override
                    public void onMulChoose(List<CheckBox> checkBoxList, Dialog dialog, List<String> strList, List<Integer> indexList) {

                        dialog.dismiss();
                    }
                })

                .createChooseMulButton()
                .show();
    }

    public void onChooseProgress(View v) {
        new XyDialog2.Builder(this)
                .title("")
                .message("正在加载...")
                .createProgressDialog()
                .show();

    }

    public void onChooseOneContent(View v) {
        new XyDialog2.Builder(this)
                .title("更新")
                .message("XXX已更新到4.0.1版本")
                .isNeedLine(true)
                .setPositiveButtonListener(R.array.select_dialog_notice, new XyDialog2.OnNoticeClickListener<TextView>() {
                    @Override
                    public void onNotice(TextView view, Dialog dialog, int which) {
                        Toast.makeText(MainActivity.this, "" + which + "--" + view.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createChooseContentButton()
                .show();
    }

    public void onEdit3(View v) {
        new XyDialog2.Builder(this)
                .title("Dialog2-输入密码")
                .digit(6)
                .isNumber(true)
                .setPositiveButtonListener("确定", new XyDialog2.OnNoticeClickListener<EditText>() {
                    @Override
                    public void onNotice(EditText view, Dialog dialog, int which) {
                        String input = view.getText().toString().trim();
                        Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createPwd2Dialog()
                .show();
    }


    public void onEdit4(View v) {
        new XySevDialog.Builder(this)
                .setSevListener(new XySevDialog.OnSevListener() {
                    @Override
                    public void sevComplete() {
                        Toast.makeText(MainActivity.this, "完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void sevDeleteContent(boolean isDelete) {
                        Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void sevRefresh(final XySevDialog dialog) {
                        Toast.makeText(MainActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                        dialog.getImageView().setBackgroundResource(R.color.colorAccent);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.stopRefresh();
                            }
                        }, 2000);
                    }
                })
                .createSeVDialog().show();
    }


}
