package open.gxd.mobi.openpractices.moudle_douban.data;

/**
 * Created by edward.ge on 2018/3/12.
 */

public class Images {
    private String small;
    private String large;
    private String medium;

    public Images(String small, String large, String medium) {
        this.small = small;
        this.large = large;
        this.medium = medium;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
