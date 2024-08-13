package com.akademicu.tattooii.model;

public class Photo {
    private int id;
    private String url;
    private Src src;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Src getSrc() {
        return src;
    }

    public class Src{
        private String original;
        private String large;
        private String medium;
        private String small;

        public String getOriginal() {
            return original;
        }

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }

        public String getSmall() {
            return small;
        }
    }
}
