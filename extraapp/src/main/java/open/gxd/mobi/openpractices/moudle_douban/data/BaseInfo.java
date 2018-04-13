package open.gxd.mobi.openpractices.moudle_douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edward.ge on 2018/3/13.
 */

public class BaseInfo {
    @SerializedName(value = "avatars", alternate = {"images"})
    private Images images;
    private String alt;
    protected String id;
    @SerializedName(value = "name", alternate = {"title"})
    private String name;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
