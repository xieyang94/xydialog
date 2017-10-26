/**
 * Copyright(c)2015
 * All right reserved.
 */
package com.xiey94.xydialog.util;

/**
 * Copyright(c)2015
 * All right reserved.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author rulang1
 * @Date 2014�?12�?2�?
 * @包名 cn.wzh.common.Common
 * @说明 �?些长用变量的说明
 */
public class XyCommon {
    /**
     *
     * @param context
     * @return 当前屏幕的宽�?
     */
    public static int getWidth(Context context) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        return dm2.widthPixels;
    }

    /**
     *
     * @param context
     * @return 当前屏幕的宽高度
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        return dm2.heightPixels;
    }

    /**
     *
     * @param context
     * @param dipValue
     * @return 将dp 装换成PX
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     *
     * @param context
     * @param pxValue
     * @return 将px �? dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     *
     * @param context
     * @return 获取当前的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (NameNotFoundException e1) {
            XyLogInfo.e(e1.getMessage());
        }
        return "0.0.0";
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (NameNotFoundException e1) {
            XyLogInfo.e(e1.getMessage());
        }
        return 0;
    }

    public static enum StateNet {
        BadNet, WifiNet, G2G3G4Net
    }

    ;

    /**
     *
     * @param context
     * @return 返回当前网络的状�?
     */
    public static StateNet netState(Context context) {
        ConnectivityManager cManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            NetworkInfo.State wifi = cManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).getState();
            // 如果3G、wifi�?2G等网络状态是连接的，则�??出，否则显示提示信息进入网络设置界面
            if (wifi == NetworkInfo.State.CONNECTED
                    || wifi == NetworkInfo.State.CONNECTING) {
                return StateNet.WifiNet;
            } else {
                return StateNet.G2G3G4Net;
            }
            // 能联�?
            // return true;
        }
        // 不能联网
        return StateNet.BadNet;
    }

    /**
     * 创建密匙
     *
     * @param algorithm
     *            加密算法,可用 DES,DESede,Blowfish
     * @return SecretKey 秘密（对称）密钥
     */
    public static SecretKey createSecretKey(String algorithm) {
        // 声明KeyGenerator对象
        KeyGenerator keygen;
        // 声明 密钥对象
        SecretKey deskey = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance(algorithm);
            // 生成�?个密�?
            deskey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            XyLogInfo.e(e.getMessage());
        }
        // 返回密匙
        return deskey;
    }

    /**
     * 根据密匙进行DES加密
     *
     * @param key
     *            密匙
     * @param info
     *            要加密的信息
     * @return String 加密后的信息
     */
    public static String encryptToDES(String info) {
        String skey = "12345678";
        SecretKey key = new SecretKeySpec(skey.getBytes(), "DES");
        // 定义 加密算法,可用 DES,DESede,Blowfish
        String Algorithm = "DES";
        // 加密随机数生成器 (RNG),(可以不写)
        SecureRandom sr = new SecureRandom();
        // 定义要生成的密文
        byte[] cipherByte = null;
        try {
            // 得到加密/解密�?
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
            c1.init(Cipher.ENCRYPT_MODE, key, sr);
            // 对要加密的内容进行编码处�?,
            cipherByte = c1.doFinal(info.getBytes());
        } catch (Exception e) {
            XyLogInfo.e(e.getMessage());
        }
        // 返回密文的十六进制形�?
        return byte2hex(cipherByte);
    }

    // DES解密
    public String decryptByDES(String sInfo) {
        String skey = "12345678";
        SecretKey key = new SecretKeySpec(skey.getBytes(), "DES");
        // 定义 加密算法,
        String Algorithm = "DES";
        // 加密随机数生成器 (RNG)
        SecureRandom sr = new SecureRandom();
        byte[] cipherByte = null;
        try {
            // 得到加密/解密�?
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key, sr);
            // 对要解密的内容进行编码处�?
            cipherByte = c1.doFinal(hex2byte(sInfo));
        } catch (Exception e) {
            XyLogInfo.e(e.getMessage());
        }
        // return byte2hex(cipherByte);
        return new String(cipherByte);
    }

    /**
     * 将二进制转化�?16进制字符�?
     *
     * @param b
     *            二进制字节数�?
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 十六进制字符串转化为2进制
     *
     * @param hex
     * @return
     */
    public byte[] hex2byte(String hex) {
        byte[] ret = new byte[8];
        byte[] tmp = hex.getBytes();
        for (int i = 0; i < 8; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成�?个字节； 如："EF"--> 0xEF
     *
     * @param src0
     *            byte
     * @param src1
     *            byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * 使用java正则表达式去掉多余的.�?0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余�?0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去�?
        }
        return s;
    }

    /**
     * 匿名用户名处�?
     *
     * @param s
     * @return
     */
    public static String dealUserName(String s) {
        String name = "";
        if (!TextUtils.isEmpty(s)) {
            int l = s.length();
            if (l > 2) {
                name = s.substring(0, 1) + "***" + s.substring(l - 1);
            } else {
                name = "匿名";
            }
        }
        return name;
    }

    public static String getDeviceIdIMEI(Context context) {
        String name = "";
        // 获取IMEI码
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        // 将获取到的IMEI码打印
        // System.out.println("IMEI：" + IMEI);我们设定字符串IMEI，最后打印字符串，就能看到IMEI码了。
        return IMEI;
    }

    public static String getSiginValue() {
        return "GuJinSuoIL6pvSsve9P0l8JswjcP4w";
    }

    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    public static String getSigin(Map<String, String> map) {
        String mStr = "";
        String[] mSort = new String[map.size()];
        int i = 0;
        for (String string : map.keySet()) {
            mSort[i] = string;
            i++;
        }
        Arrays.sort(mSort, null);
        for (int j = 0; j < mSort.length; j++) {
            if (map.get(mSort[j]) != null) {
                mStr = mStr + mSort[j] + "=" + map.get(mSort[j]) + "&";
            }
        }
        if (mStr.length() > 0)
            return mStr.substring(0, mStr.length() - 1);
        return mStr;
    }

    public static String getStr(EditText view) {
        return view.getText().toString().trim();
    }

}
