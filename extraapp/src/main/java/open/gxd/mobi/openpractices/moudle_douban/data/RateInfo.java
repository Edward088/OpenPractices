package open.gxd.mobi.openpractices.moudle_douban.data;

/**
 * Created by edward.ge on 2018/3/12.
 */

public class RateInfo {
    private int max;
    private double average;
    private String stars;
    private int min;

    public RateInfo(int max, double average, String stars, int min) {
        this.max = max;
        this.average = average;
        this.stars = stars;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
