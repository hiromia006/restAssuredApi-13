package json.fake.server.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("views")
    private Integer views;

    public Comment() {
    }

    public Comment(String id, String title, Integer views) {
        this.id = id;
        this.title = title;
        this.views = views;
    }

    public Comment(String title, Integer views) {
        this.title = title;
        this.views = views;
    }

    public Comment(Integer views) {
        this.views = views;
    }

    public Comment(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getViews() {
        return views;
    }
}