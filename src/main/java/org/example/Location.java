package org.example;

public class Location {
    private String country;
    private String city;
    private String address;
    private double latitude;
    private double longitude;

    private Location(Builder b) {
        this.country = b.country;
        this.city = b.city;
        this.address = b.address;
        this.latitude = b.latitude;
        this.longitude = b.longitude;
    }

    public static class Builder {
        private String country;
        private String city;
        private String address;
        private double latitude;
        private double longitude;

        public Builder(String country) {
            this.country = country;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder setCoordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
            return this;
        }
        public Location build() {
            return new Location(this);
        }
    }
}
