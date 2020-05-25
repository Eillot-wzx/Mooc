package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;

public class DES {
    /**
     * DES加密与解密
     * encrypt方法进行加密数据
     * decrypt进行解密数据
     */
    //定义加密公匙 位数8位
    private static final String SKEY = "xiaoer66";
    //定义字符编码集
    private static final Charset CHARSET = Charset.forName("utf-8");

    /**
     * 加密算法 进行数据设置 调用真正的加密算法
     *
     * @param srcStr 要加密的数据
     * @return
     */
    public static String encrypt(String srcStr) {
        byte[] src = srcStr.getBytes(CHARSET);
        byte[] buf = encryptTrue(src, SKEY);
        return parseByte2HexStr(buf);
    }

    /**
     * 解密算法 进行数据处理 调用真正的解密算法
     *
     * @param hexStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr) {
        byte[] src = parseHexStr2Byte(hexStr);
        byte[] buf = decrypTrue(src, SKEY);
         return new String(buf, CHARSET);
    }

    /**
     * 真正的解密算法 具体的我也看不懂
     *
     * @param src
     * @param sKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypTrue(byte[] src, String sKey) {
        try {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 真正的加密算法 真心看不懂
     *
     * @param data
     * @param sKey
     * @return
     */
    public static byte[] encryptTrue(byte[] data, String sKey) {
        try {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
