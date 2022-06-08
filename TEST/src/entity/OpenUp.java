package entity;

import java.io.Serializable;

/**
 * @ClassName OpenUp
 * @Description TODO
 * @Version 1.0
 **/
public class OpenUp implements Serializable {
    private String id;
    private String title;
    private String column;
    private String imgsrc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
