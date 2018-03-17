package techkids.vn.shortstory.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by ADMIN on 3/15/2018.
 */

public class Util {
    public static Bitmap decodeBase64(String base64){
        //chuỗi base64 gồm 1 định dạng            data:image/jpg;base64,...
        //để decode thì phải cắt chuỗi từ dấu (,) decode bằng đoạn      ...
        final String pureBase64Encoded = base64.substring(base64.indexOf(",")  + 1); //lấy chuỗi từ dấu phẩy
        final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
