package com.weather.help.models;

public class OutfitRecommendationResponse {

    private Double temperature;

    private Integer outfitLevel;

    private String errorMessage;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getOutfitLevel() {
        return outfitLevel;
    }

    public void setOutfitLevel(Integer outfitLevel) {
        this.outfitLevel = outfitLevel;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
