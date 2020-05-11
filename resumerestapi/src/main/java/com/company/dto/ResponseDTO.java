package com.company.dto;

public class ResponseDTO {
    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object obj;

    private ResponseDTO() {

    }

    public static ResponseDTO of(Object obj) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObj(obj);

        return responseDTO;
    }

    public static ResponseDTO of (String successMessage,Object obj) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccessMessage(successMessage);
        responseDTO.setObj(obj);

        return responseDTO;
    }

    public static ResponseDTO of(Integer errorCode,String errorMessage,Object obj) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorCode(errorCode);
        responseDTO.setErrorMessage(errorMessage);
        responseDTO.setObj(obj);

        return responseDTO;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
