package com.xiey94.xydialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiey94.xydialog.R;

/**
 * Created by xiey on 2017/10/26.
 */

public class XyDialog2 extends Dialog {
    private Context context;
    private boolean cancelTouchout;
    private View view;

    private XyDialog2(Builder builder) {
        super(builder.context);
        context = builder.context;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    private XyDialog2(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    public interface OnNoticeClickListener {
        /**
         * 点击响应方法
         *
         * @param view   获取对应的View
         * @param dialog 当前的Dialog对象
         * @param which  索引
         */
        void onNotice(View view, Dialog dialog, int which);
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
        lp.width = point.x - 200;
        win.setAttributes(lp);
    }

    public static final class Builder {
        private Context context;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        private String title;
        private String message;
        private OnNoticeClickListener cancelListener;
        private OnNoticeClickListener okListener;
        private String ok;
        private String cancel;
        private XyDialog2 xyDialog2;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder title(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder message(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder cancelTouchout(boolean cancelTouchout) {
            this.cancelTouchout = cancelTouchout;
            return this;
        }

        public Builder view(int resView) {
            this.view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        /**
         * @param viewRes
         * @param listener
         * @return
         * @deprecated
         */
        public Builder addViewOnClick(int viewRes, View.OnClickListener listener) {
            this.view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public Builder setPositiveButtonListener(String ok, OnNoticeClickListener okListener) {
            this.ok = ok;
            this.okListener = okListener;
            return this;
        }

        public Builder setNegativeButtonListener(String cancel, OnNoticeClickListener cancelListener) {
            this.cancel = cancel;
            this.cancelListener = cancelListener;
            return this;
        }

        //创建提示Dialog
        public XyDialog2 createNoticeDialog() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog2_layout_notice, null);
            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            if (message != null) {
                ((TextView) view.findViewById(R.id.message)).setText(message);
            }

            if (okListener != null && ok != null) {
                TextView okTv = view.findViewById(R.id.ok);
                okTv.setText(ok);
                okTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        okListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.ok).setVisibility(View.GONE);
            }

            if (cancelListener != null && cancel != null) {
                TextView cancelTv = view.findViewById(R.id.cancel);
                cancelTv.setText(cancel);
                cancelTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.cancel).setVisibility(View.GONE);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }
            return xyDialog2;
        }

    }
}
