package com.example.imbee.controller;

import com.example.imbee.pojo.ResponseObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseController {

    @Deprecated
    protected <T> ResponseEntity<ResponseObj<T>> sendRsp(ResponseObj resultObj, HttpHeaders headers, HttpStatus httpStatus) {
        return new ResponseEntity(resultObj, headers, httpStatus);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendSuccessRsp(T result) {
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.OK)
                .result(result).build(), HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendSuccessWithHeaderRsp(T result, HttpHeaders headers) {
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.OK)
                .result(result).build(), headers, HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendBadRequestRsp(String result) {
        List<String> msgs = new ArrayList<>();
        msgs.add(result.toString());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.BAD_REQUEST)
                .msgs(msgs).build(), HttpStatus.BAD_REQUEST);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendMethodFailureRsp(T result) {
        List<String> msgs = new ArrayList<>();
        msgs.add(result.toString());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.METHOD_FAILURE)
                .msgs(msgs).build(), HttpStatus.METHOD_FAILURE);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendForbiddenRsp(T result) {
        List<String> msgs = new ArrayList<>();
        msgs.add(result.toString());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.FORBIDDEN)
                .msgs(msgs).build(), HttpStatus.FORBIDDEN);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendFailRsp(Exception e) {
        e.printStackTrace();
        log.error("伺服器發生錯誤: " + e.getMessage());
        List<String> msgs = new ArrayList<>();
        msgs.add("伺服器發生錯誤");
        msgs.add(e.getMessage());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .msgs(msgs).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected <T> ResponseEntity<ResponseObj<T>> sendDenyRsp() {
        log.info("驗證失敗，查無此人");
        List<String> msgs = new ArrayList<>();
        msgs.add("驗證失敗，查無此人");
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.UNAUTHORIZED)
                .msgs(msgs).build(), HttpStatus.UNAUTHORIZED);
    }

}
