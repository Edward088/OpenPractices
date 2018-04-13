package open.gxd.mobi.openpractices.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author toby.du
 */
public class MD5Utils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 用MD5算法加密字节数组
     *
     * @param bytes 要加密的字节
     * @return byte[] 加密后的字节数组，若加密失败，则返回null
     */
    public static byte[] encode(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] digesta = digest.digest(bytes);
            return digesta;
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String input) {
        String result = input;
        if (input != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(input.getBytes(DEFAULT_CHARSET));
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
                while (result.length() < 32) {
                    result = "0" + result;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    private static byte[] getHash(final String algorithm, final byte[] data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(data);
            return digest.digest();
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static final byte[] md5(byte[] data) {
        final byte[] md5 = getHash("MD5", data);
        return md5;
    }

    public static final String md5Hex(String data) {
        final byte[] md5 = getHash("MD5", data.getBytes());
        return bytesToHex(md5);
    }

    public static final String md5Hex(byte[] data) {
        final byte[] md5 = getHash("MD5", data);
        return bytesToHex(md5);
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * MD5UpperCase
     *
     * @param str
     * @return
     */
    public static String getMD5UpperCase(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString().toUpperCase();
    }

    /**
     * MD5LowerCase
     *
     * @param str
     * @return
     */
    public static String getMD5LowerCase(String str) {
        return getMD5UpperCase(str).toLowerCase();
    }

    /**
     * 计算文件的md5，文件较短的情况，打文件自己处理
     *
     * @param filePath 文件路径
     * @return md5结果，若加密失败，则返回null
     */
    public static byte[] encodeFile(String filePath) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            File file = new File(filePath);
            if (!file.exists())
                return null;
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            byte[] digesta = null;
            int readed = -1;
            try {
                while ((readed = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, readed);
                }
                digesta = digest.digest();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return digesta;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算文件的md5,转换成hex String
     *
     * @param filePath 文件路径
     * @return md5结果，若加密失败，则返回null
     */
    public static String encodeFileHexStr(String filePath) {
        return HexUtils.bytes2HexStr(encodeFile(filePath));
    }

    public static String encodeHexStr(String value) {
        if (value == null) {
            return null;
        }
        try {
            return HexUtils.bytes2HexStr(encode(value.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
