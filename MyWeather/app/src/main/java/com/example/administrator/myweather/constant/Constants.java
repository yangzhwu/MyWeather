package com.example.administrator.myweather.constant;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:常量类
 */

public class Constants {

    /**
     * Created by zhengwuy on 2017/2/20.
     * Emali: 963460692@qq.com
     * description:存储文件相关的常量
     */

    public static class FileConstant {
        public static final String PROVINCE_FILE_NAME = "province.txt";
        public static final String CITY_FILE_NAME = "city.txt";
        public static final String COUNTY_FILE_NAME = "county.txt";
    }

    /**
     * Created by zhengwuy on 2017/2/19.
     * Email: 963460692@qq.com
     * description: 存储sharedPreference的key
     */
    public static class SharedPreferenceKeyConstant {
        public static final String KEY_HAS_LOAD_DATA = "has_load_data";
        public static final String KEY_CHOOSE_COUNTY_WEATHER_ID = "choose_county_weather_id";
        public static final String KEY_CHOOSE_COUNTY_NAME = "choose_county_name";
    }

    /**
     * Created by zhengwuy on 2017/1/31.
     * Email: 963460692@qq.com
     * description: http api的常量
     */

    public static class ApiConstant {
        //天气相关的key
        public static final String URL = "https://free-api.heweather.com/";
        public static final String WEATHER_KRY = "d74d81e2205841e98890a0a64980d14f";

        //和风天气接口https证书
        public static final String WEATHER_HTTPS_CER = "-----BEGIN CERTIFICATE-----\n" +
                "MIIG9zCCBd+gAwIBAgIRAJGoxTDnpKfPu9tpFAwWiZ8wDQYJKoZIhvcNAQELBQAw\n" +
                "gZAxCzAJBgNVBAYTAkdCMRswGQYDVQQIExJHcmVhdGVyIE1hbmNoZXN0ZXIxEDAO\n" +
                "BgNVBAcTB1NhbGZvcmQxGjAYBgNVBAoTEUNPTU9ETyBDQSBMaW1pdGVkMTYwNAYD\n" +
                "VQQDEy1DT01PRE8gUlNBIERvbWFpbiBWYWxpZGF0aW9uIFNlY3VyZSBTZXJ2ZXIg\n" +
                "Q0EwHhcNMTgxMTAxMDAwMDAwWhcNMjAxMDMxMjM1OTU5WjBeMSEwHwYDVQQLExhE\n" +
                "b21haW4gQ29udHJvbCBWYWxpZGF0ZWQxITAfBgNVBAsTGFBvc2l0aXZlU1NMIE11\n" +
                "bHRpLURvbWFpbjEWMBQGA1UEAxMNaGV3ZWF0aGVyLmNvbTCCASIwDQYJKoZIhvcN\n" +
                "AQEBBQADggEPADCCAQoCggEBANsOiIwzCLjZQPapbQxGUvtMQRUNT1/hc6FvAr0E\n" +
                "u1NArntJQ+7X90tCoAbH6sC4yBEOwYgBQMZepbyuFAA/8hbRhW1R2LA7T4zTvMTM\n" +
                "F25fIsJ25kOcbXuwnmnby89f8UsxPrWWuCJYEJ9ptk6ZJV89WPgHWGtCmCcqR/O1\n" +
                "jtSYhULdzR90F0OIeQKTBdeCb2nYJaAdQ8BOoOrOfkI/YPa3TICwmP8Hozr9+5zZ\n" +
                "wTIrGFUS62XsvX5FSIh7Qu/t6tfFuwauBbAftaSw11Lq6xhaZFcJb9l60Z+HXZ2I\n" +
                "7XRmuYA+nF/9A6XfXrITjJ2RSWzMOOTin5q4AXDNhL+ledsCAwEAAaOCA3swggN3\n" +
                "MB8GA1UdIwQYMBaAFJCvajqUWgvYkOoSVnPfQ7Q6KNrnMB0GA1UdDgQWBBSKr7b+\n" +
                "8aEadsVoGuecdwfeAW/hzjAOBgNVHQ8BAf8EBAMCBaAwDAYDVR0TAQH/BAIwADAd\n" +
                "BgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIwTwYDVR0gBEgwRjA6BgsrBgEE\n" +
                "AbIxAQICBzArMCkGCCsGAQUFBwIBFh1odHRwczovL3NlY3VyZS5jb21vZG8uY29t\n" +
                "L0NQUzAIBgZngQwBAgEwVAYDVR0fBE0wSzBJoEegRYZDaHR0cDovL2NybC5jb21v\n" +
                "ZG9jYS5jb20vQ09NT0RPUlNBRG9tYWluVmFsaWRhdGlvblNlY3VyZVNlcnZlckNB\n" +
                "LmNybDCBhQYIKwYBBQUHAQEEeTB3ME8GCCsGAQUFBzAChkNodHRwOi8vY3J0LmNv\n" +
                "bW9kb2NhLmNvbS9DT01PRE9SU0FEb21haW5WYWxpZGF0aW9uU2VjdXJlU2VydmVy\n" +
                "Q0EuY3J0MCQGCCsGAQUFBzABhhhodHRwOi8vb2NzcC5jb21vZG9jYS5jb20wSgYD\n" +
                "VR0RBEMwQYINaGV3ZWF0aGVyLmNvbYIOKi5oZXdlYXRoZXIuY26CDyouaGV3ZWF0\n" +
                "aGVyLmNvbYIPKi5oZXdlYXRoZXIubmV0MIIBewYKKwYBBAHWeQIEAgSCAWsEggFn\n" +
                "AWUAdQDuS723dc5guuFCaR+r4Z5mow9+X7By2IMAxHuJeqj9ywAAAWbP7LvnAAAE\n" +
                "AwBGMEQCIDuw19H1pj+2YM0EWsGtS5eGTjRrFPHQQBfxy7o9d/RVAiApmQgycCoH\n" +
                "lIgd7sB3wj9Qm+kmDG3LW8XdCgQdFy7IUwB1AF6nc/nfVsDntTZIfdBJ4DJ6kZoM\n" +
                "hKESEoQYdZaBcUVYAAABZs/svBkAAAQDAEYwRAIgKzbuwnf44mhQmhs3dg4qDXTx\n" +
                "11TOt+pTSVXE9+Hm67MCIFh77glvKgdxnjG1UAD3qAmr4oYmM16Dz4/ba/9GFjpW\n" +
                "AHUA8JWkWfIA0YJAEC0vk4iOrUv+HUfjmeHQNKawqKqOsnMAAAFmz+y8MgAABAMA\n" +
                "RjBEAiBOMK5DUkrC9QEwogsEilPRSzcc3e74RRdMAh1nF6m0hgIgFNbNO8jEw0Op\n" +
                "LqfS6wPQQhGD2tARP6xAVIk7ewUp+cIwDQYJKoZIhvcNAQELBQADggEBAHLa78Ho\n" +
                "xYFy38v5SA9tfDIeKHsnFeJe9bLl5VGMx+v6A4PUwSiGch25YZJaJRD85M0mIZlb\n" +
                "aTrXHwdxA5N0xJwYzQMMD3cxfxGJjUMNMzFJxDZqFdWH53lnQJGPL3ah3DM/JhFF\n" +
                "oC0C0njJEVnEh2DN8fYEznXPuJHfN4iIswILrxTvYLM/cwiddgqJxPiLmpEkE6fJ\n" +
                "3H7W9Rao/+qmG32nhZUpkKqEnNYcj7XlmJsatz+CCPrucRQqnmIcAiBzqD8veq15\n" +
                "AYNhZUAS4+yrdWzOmKfh7V1VrQ1tMUm21lkWkvDDOLV+slPEAS9StagT48BRSny1\n" +
                "g9eq+11B3uxr6so=\n" +
                "-----END CERTIFICATE-----";

    }
}
