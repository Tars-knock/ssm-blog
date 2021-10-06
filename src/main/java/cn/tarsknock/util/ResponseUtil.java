package cn.tarsknock.util;

import lombok.*;

import java.io.Serializable;

/**
 *  返回状态用工具类
 */
@Data
@NoArgsConstructor
public class ResponseUtil<T> implements Serializable {

    private  String code;
    private  String msg;
    private T data;


    public static <T> ResponseUtil success(T data) {
        ResponseUtil<T> response = new ResponseUtil<>();
        response.setCode("success");
        response.setData(data);
        return response;
    }

    public static ResponseUtil success(){
        return success(null);
    }

    public static <T> ResponseUtil fail(T data, String massage){
        ResponseUtil<T> responseUtil = new ResponseUtil<>();
        responseUtil.setCode("fail");
        responseUtil.setData(data);
        responseUtil.setMsg(massage);
        return responseUtil;
    }

    public static ResponseUtil fail(String massage){
        return fail(null, massage);
    }

}

//@Getter
//@AllArgsConstructor
//enum ReturnCode {
//    SUCCESS(0, "OK"),
//    FAILED(-1, "Failed");
//
//    private final String code;
//    private final String message;
//}
