package com.mccarty.fivedayweather

object JsonData {
    const val JSON_DATA = """{
      "cod": "200",
      "message": 0,
      "cnt": 40,
      "list": [
        {
          "dt": 1725440400,
          "main": {
            "temp": 305.05,
            "feels_like": 303.74,
            "temp_min": 304.82,
            "temp_max": 305.05,
            "pressure": 1009,
            "sea_level": 1009,
            "grnd_level": 967,
            "humidity": 29,
            "temp_kf": 0.23
          },
          "weather": [
            {
              "id": 800,
              "main": "Clear",
              "description": "clear sky",
              "icon": "01n"
            }
          ],
          "clouds": {
            "all": 0
          },
          "wind": {
            "speed": 3.78,
            "deg": 133,
            "gust": 4.86
          },
          "visibility": 10000,
          "pop": 0,
          "sys": {
            "pod": "n"
          },
          "dt_txt": "2024-09-04 09:00:00"
        }
      ],
      "city": {
        "id": 0,
        "name": "Chandler",
        "coord": {
          "lat": 33.2509,
          "lon": -111.8593
        },
        "country": "US",
        "population": 0,
        "timezone": -25200,
        "sunrise": 1725455054,
        "sunset": 1725500923
      }
    }"""
}