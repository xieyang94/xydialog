package com.xiey94.xydialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiey94.xydialog.R;
import com.xiey94.xydialog.util.XyCommon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xieyang
 *         created at 2017/10/26.
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

    public interface OnNoticeClickListener<T> {
        /**
         * 点击响应方法
         *
         * @param view
         * @param dialog
         * @param which
         */
        void onNotice(T view, Dialog dialog, int which);
    }

    /**
     * 多选接口
     *
     * @param <T>
     */
    public interface OnMulClickListener<T> {
        void onMulChoose(T view, Dialog dialog, List<String> strList, List<Integer> indexList);
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

        private String title;
        private String message;
        private OnNoticeClickListener cancelListener;
        private OnNoticeClickListener okListener;
        private String ok;
        private String cancel;
        private XyDialog2 xyDialog2;
        private String hint;
        private boolean isShow;
        private boolean isNumber;
        private boolean isChar;
        private boolean isNumberAndChar;
        private List<String> chooseList;
        private OnMulClickListener mulListener;
        private boolean isShowSoftKeyboard = true;
        private String editContent;
        private boolean isNeedLine = false;
        private int digit = -1;

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

        public Builder hint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder hint(int hint) {
            this.hint = (String) context.getText(hint);
            return this;
        }

        public Builder isShow(boolean isShow) {
            this.isShow = isShow;
            return this;
        }

        public Builder isNumber(boolean isNumber) {
            this.isNumber = isNumber;
            return this;
        }

        public Builder isChar(boolean isChar) {
            this.isChar = isChar;
            return this;
        }

        public Builder digit(int digit) {
            this.digit = digit;
            return this;
        }

        public Builder setEditContent(String editContent) {
            this.editContent = editContent;
            return this;
        }

        public Builder isNumberAndChar(boolean isNumberAndChar) {
            this.isNumberAndChar = isNumberAndChar;
            return this;
        }

        public Builder isShowSoftKeyboard(boolean isShowSoftKeyboard) {
            this.isShowSoftKeyboard = isShowSoftKeyboard;
            return this;
        }

        public Builder cancelTouchout(boolean cancelTouchout) {
            this.cancelTouchout = cancelTouchout;
            return this;
        }

        public Builder isNeedLine(boolean isNeedLine) {
            this.isNeedLine = isNeedLine;
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

        public <T> Builder setPositiveButtonListener(String ok, OnNoticeClickListener<T> okListener) {
            this.ok = ok;
            this.okListener = okListener;
            return this;
        }

        public <T> Builder setPositiveButtonListener(List<String> chooseList, OnNoticeClickListener<T> okListener) {
            this.chooseList = chooseList;
            this.okListener = okListener;
            return this;
        }

        public <T> Builder setPositiveButtonListener(int chooseList, OnNoticeClickListener<T> okListener) {
            String[] items = context.getResources().getStringArray(chooseList);
            this.chooseList = Arrays.asList(items);
            this.okListener = okListener;
            return this;
        }

        public <T> Builder setPositiveButtonListener(String ok, List<String> chooseList, OnMulClickListener<T> mulListener) {
            this.ok = ok;
            this.chooseList = chooseList;
            this.mulListener = mulListener;
            return this;
        }

        public <T> Builder setPositiveButtonListener(String ok, int chooseList, OnMulClickListener<T> mulListener) {
            this.ok = ok;
            String[] items = context.getResources().getStringArray(chooseList);
            this.chooseList = Arrays.asList(items);
            this.mulListener = mulListener;
            return this;
        }

        public <T> Builder setNegativeButtonListener(String cancel, OnNoticeClickListener<T> cancelListener) {
            this.cancel = cancel;
            this.cancelListener = cancelListener;
            return this;
        }

        //创建提示Dialog
        public XyDialog2 createNoticeDialog() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            if (message != null) {
                ((TextView) view.findViewById(R.id.message)).setText(message);
            }

            if (okListener != null && ok != null) {
                TextView okTv = view.findViewById(R.id.positiveButton);
                okTv.setText(ok);
                okTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        okListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            if (cancelListener != null && cancel != null) {
                TextView cancelTv = view.findViewById(R.id.negativeButton);
                cancelTv.setText(cancel);
                cancelTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }
            return xyDialog2;
        }

        //创建输入密码对话框
        public XyDialog2 createPwdDialog() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_edit, null);

            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            final EditText input = view.findViewById(R.id.message);
            input.setFocusable(true);

            if (digit != -1) {
                input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(digit)});
            }

            if (hint != null) {
                input.setHint(hint);
            }

            if (editContent != null) {
                input.setText(editContent);
            }

            if (!isShow) {
                input.setTransformationMethod(new PasswordTransformationMethod());
            }
            if (isNumber) {
                input.setKeyListener(new NumberKeyListener() {
                    @Override
                    protected char[] getAcceptedChars() {
                        return new char[]{
                                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                        };
                    }

                    @Override
                    public int getInputType() {
                        return InputType.TYPE_CLASS_PHONE;
                    }
                });
            }

            if (isChar) {
                input.setKeyListener(new NumberKeyListener() {
                    @Override
                    protected char[] getAcceptedChars() {
                        return new char[]{
                                'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                'o', 'p', 'q', 'r', 's', 't',
                                'u', 'v', 'w', 'x', 'y', 'z',
                                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                                'O', 'P', 'Q', 'R', 'S', 'T',
                                'U', 'V', 'W', 'X', 'Y', 'Z'
                        };
                    }

                    @Override
                    public int getInputType() {
                        return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                    }
                });
            }

            if (isNumberAndChar) {
                input.setKeyListener(new NumberKeyListener() {
                    @Override
                    protected char[] getAcceptedChars() {
                        return new char[]{
                                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                                'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                'o', 'p', 'q', 'r', 's', 't',
                                'u', 'v', 'w', 'x', 'y', 'z',
                                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                                'O', 'P', 'Q', 'R', 'S', 'T',
                                'U', 'V', 'W', 'X', 'Y', 'Z'
                        };
                    }

                    @Override
                    public int getInputType() {
                        return InputType.TYPE_CLASS_PHONE;
                    }
                });
            }

            if (okListener != null && ok != null) {
                TextView okTv = view.findViewById(R.id.positiveButton);
                okTv.setText(ok);
                okTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        okListener.onNotice(input, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            if (cancelListener != null && cancel != null) {
                TextView cancelTv = view.findViewById(R.id.negativeButton);
                cancelTv.setText(cancel);
                cancelTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }

            if (isShowSoftKeyboard) {
                input.setFocusable(true);
                input.setFocusableInTouchMode(true);
                input.requestFocus();
                input.post(new Runnable() {
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
            return xyDialog2;
        }

        //创建单选框
        public XyDialog2 createChooseButton() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_choose, null);

            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            LinearLayout linear = view.findViewById(R.id.linear);
            for (final String s : chooseList) {
                final TextView textView = new TextView(context);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp2);
                textView.setText(s);
                textView.setPadding(40, 25, 40, 25);
                if (Build.VERSION.SDK_INT >= 21) {
                    textView.setTextSize(XyCommon.dip2px(context, 6));
                } else {
                    textView.setTextSize(XyCommon.dip2px(context, 10));
                }
                textView.setBackgroundResource(R.drawable.xy_selector_text);
                if (okListener != null) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int which = chooseList.indexOf(s);
                            okListener.onNotice(textView, xyDialog2, which);
                        }
                    });
                }
                linear.addView(textView);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }

            return xyDialog2;
        }

        //多选
        public XyDialog2 createChooseMulButton() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_mul_choose, null);

            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            LinearLayout linear = view.findViewById(R.id.linear);
            final List<String> strList = new ArrayList<String>();
            final List<Integer> indexList = new ArrayList<Integer>();
            final List<CheckBox> checkBoxList = new ArrayList<CheckBox>();

            for (final String s : chooseList) {
                final CheckBox checkBox = new CheckBox(context);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                checkBox.setLayoutParams(lp2);
                checkBox.setText(s);
                checkBox.setPadding(40, 25, 40, 25);
                if (Build.VERSION.SDK_INT >= 21) {
                    checkBox.setTextSize(XyCommon.dip2px(context, 6));
                } else {
                    checkBox.setTextSize(XyCommon.dip2px(context, 10));
                }
                linear.addView(checkBox);
                checkBoxList.add(checkBox);
            }

            if (mulListener != null && ok != null) {
                TextView okTv = view.findViewById(R.id.positiveButton);
                okTv.setText(ok);
                okTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mulListener.onMulChoose(checkBoxList, xyDialog2, strList, indexList);
                    }
                });
            } else {
                view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            if (cancelListener != null && cancel != null) {
                TextView cancelTv = view.findViewById(R.id.negativeButton);
                cancelTv.setText(cancel);
                cancelTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelListener.onNotice(null, xyDialog2, -1);
                    }
                });
            } else {
                view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }

            return xyDialog2;
        }

        //加载框
        public XyDialog2 createProgressDialog() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_progress, null);
            if (title != null && !title.equals("")) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            } else {
                view.findViewById(R.id.title).setVisibility(View.GONE);
            }

            if (message != null) {
                ((TextView) view.findViewById(R.id.message)).setText(message);
            }

            if (resStyle != -1) {
                xyDialog2 = new XyDialog2(this, resStyle);
            } else {
                xyDialog2 = new XyDialog2(this, R.style.Dialog);
            }
            return xyDialog2;
        }

        //创建单选提示框
        public XyDialog2 createChooseContentButton() {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_choose_content, null);

            if (title != null) {
                ((TextView) view.findViewById(R.id.title)).setText(title);
            }

            if (context != null) {
                ((TextView) view.findViewById(R.id.message)).setText(message);
            }

            LinearLayout linear = view.findViewById(R.id.linear);
            for (final String s : chooseList) {
                final TextView textView = new TextView(context);
                //是否需要分割线
                if (isNeedLine) {
                    final View view = new View(context);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, XyCommon.dip2px(context, 1));
                    view.setLayoutParams(lp);
                    view.setBackgroundColor(context.getResources().getColor(R.color.xydialog_colorLine));
                    linear.addView(view);
                }

                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp2);
                textView.setText(s);
                textView.setPadding(0, 25, 0, 25);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(context.getResources().getColor(R.color.xydialog_colorSingle));
                if (Build.VERSION.SDK_INT >= 21) {
                    textView.setTextSize(XyCommon.dip2px(context, 6));
                } else {
                    textView.setTextSize(XyCommon.dip2px(context, 10));
                }
                textView.setBackgroundResource(R.drawable.xy_selector_text);
                if (okListener != null) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int which = chooseList.indexOf(s);
                            okListener.onNotice(textView, xyDialog2, which);
                        }
                    });
                }
                linear.addView(textView);
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
