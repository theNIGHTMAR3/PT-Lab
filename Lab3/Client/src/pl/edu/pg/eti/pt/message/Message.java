package pl.edu.pg.eti.pt.message;

import java.io.Serializable;

public class Message implements Serializable {
    static final long serialVersionUID = 13;
    private int number;
    private String content;

    public Message(int number, String msg)
    {
        this.number=number;
        this.content=msg;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getNumber() {
        return number;
    }
}
