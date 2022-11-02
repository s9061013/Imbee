package com.example.imbee.pojo;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObj<T> implements Serializable {

    private HttpStatus status; // success 或 error
    private List<String> msgs; // 錯誤集合
    private T result; //傳送的物件

    public enum RspMsg {

        SUCCESS("成功"),
        NOT_FOUND("檔案不存在");

        @Getter
        private String msg;

        RspMsg(String msg) {
            this.msg = msg;
        }
    }
}
