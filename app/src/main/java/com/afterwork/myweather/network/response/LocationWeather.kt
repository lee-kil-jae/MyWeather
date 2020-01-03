package com.afterwork.myweather.network.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class LocationWeather (@SerializedName("title") val title: String,
                            @SerializedName("consolidated_weather") val consolidated_weather: ArrayList<Weather>): Parcelable {

    constructor(parcelIn: Parcel): this(
        parcelIn.readString()!!,
        arrayListOf<Weather>().apply {
            parcelIn.readTypedList(this, Weather.CREATOR)
        }
    )

    override fun writeToParcel(parcelOut: Parcel, flags: Int) {
        parcelOut.writeString(title)
        parcelOut.writeTypedList(consolidated_weather)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<LocationWeather> {
        override fun createFromParcel(parcel: Parcel): LocationWeather {
            return LocationWeather(parcel)
        }

        override fun newArray(size: Int): Array<LocationWeather?> {
            return arrayOfNulls(size)
        }
    }

    data class Weather(@SerializedName("weather_state_name") val weather_state_name: String,
                       @SerializedName("weather_state_abbr") val weather_state_abbr: String,
                       @SerializedName("the_temp") val the_temp: Double,
                       @SerializedName("humidity") val humidity: Int): Parcelable {

        protected constructor(parcelIn: Parcel) : this(
            parcelIn.readString()!!,
            parcelIn.readString()!!,
            parcelIn.readDouble(),
            parcelIn.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(weather_state_name)
            dest.writeString(weather_state_abbr)
            dest.writeDouble(the_temp)
            dest.writeInt(humidity)
        }

        override fun describeContents() = 0

        companion object CREATOR : Parcelable.Creator<Weather> {
            override fun createFromParcel(parcel: Parcel): Weather {
                return Weather(parcel)
            }

            override fun newArray(size: Int): Array<Weather?> {
                return arrayOfNulls(size)
            }
        }
    }
}

/*
{
    "consolidated_weather": [
        {
            "id": 5188527648145408,
            "weather_state_name": "Showers",
            "weather_state_abbr": "s",
            "wind_direction_compass": "NNW",
            "created": "2019-12-23T00:26:38.337433Z",
            "applicable_date": "2019-12-23",
            "min_temp": -0.8049999999999999,
            "max_temp": 5.445,
            "the_temp": 5.61,
            "wind_speed": 2.9911279161430584,
            "wind_direction": 347.29888912871354,
            "air_pressure": 1025,
            "humidity": 60,
            "visibility": 12.414996420901932,
            "predictability": 73
        },
        {
            "id": 6591106462515200,
            "weather_state_name": "Heavy Cloud",
            "weather_state_abbr": "hc",
            "wind_direction_compass": "N",
            "created": "2019-12-23T00:26:41.373884Z",
            "applicable_date": "2019-12-24",
            "min_temp": -1.965,
            "max_temp": 5.35,
            "the_temp": 5.295,
            "wind_speed": 2.7635044910045337,
            "wind_direction": 8.324042811587175,
            "air_pressure": 1024,
            "humidity": 59,
            "visibility": 9.919259027280681,
            "predictability": 71
        },
        {
            "id": 5773631109988352,
            "weather_state_name": "Light Rain",
            "weather_state_abbr": "lr",
            "wind_direction_compass": "NNE",
            "created": "2019-12-23T00:26:44.378533Z",
            "applicable_date": "2019-12-25",
            "min_temp": -2.115,
            "max_temp": 4.845,
            "the_temp": 3.985,
            "wind_speed": 2.316168961645325,
            "wind_direction": 24.245865079032786,
            "air_pressure": 1022,
            "humidity": 67,
            "visibility": 13.126155750417562,
            "predictability": 75
        },
        {
            "id": 6178416476815360,
            "weather_state_name": "Light Rain",
            "weather_state_abbr": "lr",
            "wind_direction_compass": "NNW",
            "created": "2019-12-23T00:26:47.376799Z",
            "applicable_date": "2019-12-26",
            "min_temp": -2.23,
            "max_temp": 2.08,
            "the_temp": 3.545,
            "wind_speed": 3.5764329499085346,
            "wind_direction": 336.39506855729115,
            "air_pressure": 1023,
            "humidity": 58,
            "visibility": 12.020736399427344,
            "predictability": 75
        },
        {
            "id": 6737666248802304,
            "weather_state_name": "Light Cloud",
            "weather_state_abbr": "lc",
            "wind_direction_compass": "NNW",
            "created": "2019-12-23T00:26:50.939359Z",
            "applicable_date": "2019-12-27",
            "min_temp": -3.4050000000000002,
            "max_temp": 2.9000000000000004,
            "the_temp": 3.04,
            "wind_speed": 4.223113938047137,
            "wind_direction": 342.14871513560155,
            "air_pressure": 1022,
            "humidity": 58,
            "visibility": 11.76348872584109,
            "predictability": 70
        },
        {
            "id": 5341831204175872,
            "weather_state_name": "Showers",
            "weather_state_abbr": "s",
            "wind_direction_compass": "ESE",
            "created": "2019-12-23T00:26:53.275042Z",
            "applicable_date": "2019-12-28",
            "min_temp": -2.5,
            "max_temp": 5.154999999999999,
            "the_temp": 3.87,
            "wind_speed": 1.841562673983934,
            "wind_direction": 108,
            "air_pressure": 1026,
            "humidity": 57,
            "visibility": 9.999726596675416,
            "predictability": 73
        }
    ],
    "time": "2019-12-23T11:38:20.105063+09:00",
    "sun_rise": "2019-12-23T07:44:13.785995+09:00",
    "sun_set": "2019-12-23T17:17:54.947331+09:00",
    "timezone_name": "LMT",
    "parent": {
        "title": "South Korea",
        "location_type": "Country",
        "woeid": 23424868,
        "latt_long": "36.448151,127.850166"
    },
    "sources": [
        {
            "title": "BBC",
            "slug": "bbc",
            "url": "http://www.bbc.co.uk/weather/",
            "crawl_rate": 360
        },
        {
            "title": "Forecast.io",
            "slug": "forecast-io",
            "url": "http://forecast.io/",
            "crawl_rate": 480
        },
        {
            "title": "HAMweather",
            "slug": "hamweather",
            "url": "http://www.hamweather.com/",
            "crawl_rate": 360
        },
        {
            "title": "Met Office",
            "slug": "met-office",
            "url": "http://www.metoffice.gov.uk/",
            "crawl_rate": 180
        },
        {
            "title": "OpenWeatherMap",
            "slug": "openweathermap",
            "url": "http://openweathermap.org/",
            "crawl_rate": 360
        },
        {
            "title": "Weather Underground",
            "slug": "wunderground",
            "url": "https://www.wunderground.com/?apiref=fc30dc3cd224e19b",
            "crawl_rate": 720
        },
        {
            "title": "World Weather Online",
            "slug": "world-weather-online",
            "url": "http://www.worldweatheronline.com/",
            "crawl_rate": 360
        }
    ],
    "title": "Seoul",
    "location_type": "City",
    "woeid": 1132599,
    "latt_long": "37.557121,126.977379",
    "timezone": "Asia/Seoul"
}
 */