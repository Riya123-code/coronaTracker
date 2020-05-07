package io.javabrains.coronavirustracker.models;

public class Locationstate {
    private String state;
    private String country;
    private int latestTotalCases;
private int difffromprevday;

    public int getDifffromprevday() {
        return this.difffromprevday;
    }

    public void setDifffromprevday(final int difffromprevday) {
        this.difffromprevday = difffromprevday;
    }

    public int getLatestTotalCases() {
        return this.latestTotalCases;
    }

    public void setLatestTotalCases(final int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getState() {
        return this.state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Locationstate{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", difffromprevday=" + difffromprevday +
                '}';
    }
}
