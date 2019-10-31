package hello.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private String message;
    private Integer status;
    private Object jsonObject;

    public Answer(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public Answer(String message, Integer status, Object jsonObject) {
        this.message = message;
        this.status = status;
        this.jsonObject = jsonObject;
    }
}
