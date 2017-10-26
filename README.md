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
    	compile 'com.github.xieyang94:xydialog:0.0.6'
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
	    <version>0.0.6</version>
	</dependency>
```


- <font size=6>在代码中使用</font>


```
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
```


![提示框](https://github.com/xieyang94/xydialog/blob/master/1.png "提示框")



```
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
```


![输入框](https://github.com/xieyang94/xydialog/images/blob/master/2.png "输入框")


```
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
```

![单选框](https://github.com/xieyang94/xydialog/blob/master/images/3.png "单选框")




