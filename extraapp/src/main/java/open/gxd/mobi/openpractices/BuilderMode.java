package open.gxd.mobi.openpractices;

/**
 * Created by edward.ge on 2017/3/6.
 */

public class BuilderMode {
    private int a;
    private String b;
    private boolean c;

    public static class MyBuilder {
        private int a;
        private String b;
        private boolean c;

        public MyBuilder setInt(int x) {
            this.a = x;
            return this;
        }

        public MyBuilder setString(String s) {
            this.b = s;
            return this;
        }

        public MyBuilder setBoolean(boolean z) {
            this.c = z;
            return this;
        }

        public BuilderMode build() {
            return new BuilderMode(this);
        }
    }

    private BuilderMode(MyBuilder myBuilder) {
        this.a = myBuilder.a;
        b = myBuilder.b;
        c = myBuilder.c;

    }
}
