# Dialog

- <font size=5>为自己而打造</font>


- <font size=5>定制自己需要的各种Dialog，还有不同的实现方法</font>


# How to use


- <font size=6>添加依赖  (要最新版)</font>



- <font size=5 >gradle方式</font>



- <font size=5>1、Add it in your root build.gradle at the end of repositories:</font>




```
allprojects {
    	repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


- <font size=5>2、 Add the dependency</font>



```
dependencies {
    	compile 'com.github.xieyang94:xydialog:0.2.0'
	}
```


- <font size=5 >maven方式</font>



- <font size=5>1、Add the JitPack repository to your build file</font>




```
<repositories>
    	<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```


- <font size=5>2、 Add the dependency</font>



```
<dependency>
        <groupId>com.github.xieyang94</groupId>
	    <artifactId>xydialog</artifactId>
	    <version>0.1.5</version>
	</dependency>
```


- <font size=6>在代码中使用</font>

![提示框](https://github.com/xieyang94/xydialog/blob/master/images/1.png "提示框")

![输入框](https://github.com/xieyang94/xydialog/blob/master/images/2.png "输入框")

![单选框](https://github.com/xieyang94/xydialog/blob/master/images/3.png "单选框")


```

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

```

```
        new XyDialog.Builder(this)
                .setTitle("输入框")
                .setHint("请输入内容")
                .setIsShow(true)
                .setIsInputType(false)
                .setPositiveButton("确定", new XyDialog.OnDialogEditClickListener() {
                    @Override
                    public void onPositiveButonListener(EditText input, Dialog dialog, int confirm) {
                        Toast.makeText(MainActivity.this, input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .createEdit()
                .show();

```

```

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


```


- <font size=6>XyDialog2在代码中使用</font>

![提示框](https://github.com/xieyang94/xydialog/blob/master/images/4.png "提示框")
![输入框](https://github.com/xieyang94/xydialog/blob/master/images/5.png "输入框")
![单选框](https://github.com/xieyang94/xydialog/blob/master/images/6.png "单选框")
![多选框](https://github.com/xieyang94/xydialog/blob/master/images/7.png "多选框")
![加载框](https://github.com/xieyang94/xydialog/blob/master/images/8.png "加载框")
![单选内容框](https://github.com/xieyang94/xydialog/blob/master/images/9.png "单选内容框")
![密码输入框](https://github.com/xieyang94/xydialog/blob/master/images/10.png "密码输入框")



```
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

```

```

        new XyDialog2.Builder(this)
                .title("Dialog2-输入密码")
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

```

```

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

```

```

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


```

```
        new XyDialog2.Builder(this)
                .title("")
                .message("正在加载...")
                .createProgressDialog()
                .show();


```

```
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

```

```
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

```


# Version
[VERSION.md](https://github.com/xieyang94/xydialog/blob/master/UPDATE.md)



