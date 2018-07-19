# 更新日志
* 2017-10-30：修改Dialog的宽度，使之适配到4.0
* 2017-11-01：密码输入框添加是否弹出软键盘
* 2017-11-01：输入框添加是否内容回显

**v0.1.7**
>* 添加加载框
>* 添加颜色资源修改,可以覆写修改
```
    <!--标题颜色-->
    <color name="xydialog_colorTitle">#000000</color>
    <!--内容颜色-->
    <color name="xydialog_colorMessage">#000000</color>
    <!--输入内容颜色-->
    <color name="xydialog_colorEditText">#000000</color>
    <!--进度条颜色-->
    <color name="xydialog_colorProgressBar">#63AEFF</color>
```

**v0.1.8**
>* 去掉了layout-v21，将点击效果的不同写到了values-v21中的styles中

**v0.1.9**
>* 精简库代码

**v0.2.0**
>* 添加了一个加载框
>* 添加了单选内容框
>* 添加了一个资源颜色
```
    <!--分割线颜色-->
    <color name="xydialog_colorLine">#efeff5</color>
    <!--单选文字颜色-->
    <color name="xydialog_colorSingle">#2269D4</color>
```
>* 更改了系统资源颜色
```
    <color name="theme">#2269D4</color>
```

**v0.2.1**
>* 修改按钮的长度限制为不限制
>* 输入框加入一个输入长度限制的字段

**v0.2.2**
>* 增加一个点式的密码输入框
>* 引用了 [PasswordInputView][1],感谢


**v0.2.5**
>* 将Dialog中有单选类型的字号当版本>=21时修改字号为18

**v0.2.7**
>* 添加图片验证码dialog,XySevDialog


**v0.2.8**
>* XySevDialog添加hint提示


**v0.3.2**
>* 添加协议框


**v0.3.3**
>* 修改SecurityCodeView超过6位的异常













-------------------------
[1]:https://github.com/Ericsongyl/PasswordInputView





