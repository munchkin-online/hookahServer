package hello.helper;

public enum Status {
    OK_STATUS(1),BAD_STATUS(2);

    public Integer getStatusCode() {
        return statusCode;
    }


    Integer statusCode;
    Status(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
