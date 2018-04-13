package open.gxd.mobi.openpractices.moudle_douban.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward.ge on 2018/3/12.
 */

public class MovieInfo extends BaseInfo {
    @SerializedName("rating")
    private RateInfo rateInfo;//评分
    @SerializedName("genres")
    private List<String> gender = new ArrayList<>();
    private List<BaseInfo> casts = new ArrayList<>();
    @SerializedName("collect_count")
    private int collectCount;
    @SerializedName("original_title")
    private String originalTitle;
    private String subtype;
    private List<BaseInfo> directors = new ArrayList<>();
    private String year;

    public MovieInfo() {
    }

    public RateInfo getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(RateInfo rateInfo) {
        this.rateInfo = rateInfo;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public List<BaseInfo> getCasts() {
        return casts;
    }

    public void setCasts(List<BaseInfo> casts) {
        this.casts = casts;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<BaseInfo> getDirectors() {
        return directors;
    }

    public void setDirectors(List<BaseInfo> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieInfo)) return false;

        MovieInfo movieInfo = (MovieInfo) o;

        return id != null ? id.equals(movieInfo.id) : movieInfo.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
