package com.example.domain;

public class OpenWeatherMap {
	private Double lat;
	private Double lon;
	private Integer temp;
	private String weather;
	private String weatherIconPath;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeatherIconPath() {
		return weatherIconPath;
	}

	public void setWeatherIconPath(String weatherIconPath) {
		this.weatherIconPath = weatherIconPath;
	}

	@Override
	public String toString() {
		return "OpenWeatherMap [lat=" + lat + ", lon=" + lon + ", temp=" + temp + ", weather=" + weather
				+ ", weatherIconPath=" + weatherIconPath + "]";
	}

}
