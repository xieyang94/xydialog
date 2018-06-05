package com.xiey94.xydialog.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiey94.xydialog.R;
import com.xiey94.xydialog.view.SecurityCodeView;

/**
 * @author xieyang
 *         created at 2017/10/26.
 */
public class XySevDialog extends Dialog {
    private Context context;
    private boolean cancelTouchout;
    private View view;

    private XySevDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    private XySevDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    /**
     * 验证码
     */
    public interface OnSevListener {
        void sevComplete();

        void sevDeleteContent(boolean isDelete);

        void sevRefresh(XySevDialog xySevDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        Point point = new Point();
        display.getSize(point);
        if (Build.VERSION.SDK_INT >= 21) {
            lp.width = point.x - 200;
        } else {
            lp.width = point.x - 40;
        }
        win.setAttributes(lp);
    }

    public static final class Builder {
        private Context context;
        private boolean cancelTouchout = true;
        private View view;
        private int resStyle = -1;
        private XySevDialog xyDialog2;
        private boolean isShowSoftKeyboard = true;
        private OnSevListener sevListener;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder cancelTouchout(boolean cancelTouchout) {
            this.cancelTouchout = cancelTouchout;
            return this;
        }

        public Builder isShowSoftKeyboard(boolean isShowSoftKeyboard) {
            this.isShowSoftKeyboard = isShowSoftKeyboard;
            return this;
        }


        public Builder view(int resView) {
            this.view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder setSevListener(OnSevListener sevListener) {
            this.sevListener = sevListener;
            return this;
        }

        //创建输入密码对话框3
        public XySevDialog createSeVDialog() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_code, null);

            final SecurityCodeView input = view.findViewById(R.id.securityCode);
            final ImageView imageView = view.findViewById(R.id.codeImage);
            final RelativeLayout refreshCode = view.findViewById(R.id.refreshCode);
            final ImageView refreshImage = view.findViewById(R.id.refreshImage);
            refreshCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sevListener.sevRefresh(xyDialog2);
                    xyDialog2.startRefresh(refreshImage);
                }
            });

            input.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
                @Override
                public void inputComplete() {
                    sevListener.sevComplete();
                }

                @Override
                public void deleteContent(boolean isDelete) {
                    sevListener.sevDeleteContent(isDelete);
                }
            });

            input.setFocusable(true);


            if (resStyle != -1) {
                xyDialog2 = new XySevDialog(this, resStyle);
            } else {
                xyDialog2 = new XySevDialog(this, R.style.Dialog);
            }

            if (isShowSoftKeyboard) {
                input.getEditText().setFocusable(true);
                input.getEditText().setFocusableInTouchMode(true);
                input.getEditText().requestFocus();
                input.getEditText().post(new Runnable() {
                    @Override
                    public void
                    run() {
                        InputMethodManager inputMethodManager = (InputMethodManager) ((Activity) context).
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(0,
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                });
            }
            xyDialog2.setImageView(imageView);

            return xyDialog2;
        }

    }


    private  ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public  void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * 刷新-属性动画
     */
    private  ObjectAnimator rotate;

    private  void startRefresh(View view) {
        if (rotate == null) {
            rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        }
        rotate.setDuration(1000);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.start();
    }

    public void stopRefresh() {
        if (rotate != null && rotate.isRunning()) {
            rotate.end();
            rotate.clone();
        }
    }
}
