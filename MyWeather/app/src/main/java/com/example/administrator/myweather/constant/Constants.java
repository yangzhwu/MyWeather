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
        public static final String KEY_LAST_CHOOSE_WEATHER_ID = "last_choose_weather_id";
        public static final String KEY_LAST_CHOOSE_CITY_ID = "last_choose_city_id";
    }

    /**
     * Created by zhengwuy on 2017/1/31.
     * Email: 963460692@qq.com
     * description: http api的常量
     */

    public static class ApiConstant {
        //高德地图apiKey
        public static final String Amap_KEY = "1afff7faebdab35f9d8edf48a3542433";

        //天气相关的key
        public static final String URL = "https://free-api.heweather.com/";
        public static final String WEATHER_KRY = "d74d81e2205841e98890a0a64980d14f";

        //和风天气接口https证书
        public static final String WEATHER_HTTPS_CER = "-----BEGIN CERTIFICATE-----\n" +
                "MIIFZzCCBE+gAwIBAgIRAPbag0CenpmnokQRyZnr754wDQYJKoZIhvcNAQELBQAw\n" +
                "gZAxCzAJBgNVBAYTAkdCMRswGQYDVQQIExJHcmVhdGVyIE1hbmNoZXN0ZXIxEDAO\n" +
                "BgNVBAcTB1NhbGZvcmQxGjAYBgNVBAoTEUNPTU9ETyBDQSBMaW1pdGVkMTYwNAYD\n" +
                "VQQDEy1DT01PRE8gUlNBIERvbWFpbiBWYWxpZGF0aW9uIFNlY3VyZSBTZXJ2ZXIg\n" +
                "Q0EwHhcNMTYxMDI3MDAwMDAwWhcNMTkxMDI3MjM1OTU5WjBaMSEwHwYDVQQLExhE\n" +
                "b21haW4gQ29udHJvbCBWYWxpZGF0ZWQxFDASBgNVBAsTC1Bvc2l0aXZlU1NMMR8w\n" +
                "HQYDVQQDExZmcmVlLWFwaS5oZXdlYXRoZXIuY29tMIIBIjANBgkqhkiG9w0BAQEF\n" +
                "AAOCAQ8AMIIBCgKCAQEA0GT0rqdpEFBYn95wxy57B6XrClqiBk/9/6hX3Ls0ee8K\n" +
                "3osdyeH/OV/dTMXWu332QbNx/Wff2zFfuh+wS5DDCSY8OPu4AbN817j8/pQT9A22\n" +
                "lzkkfTrYnV0lR7sRWXYUD2fvTjcmlp7EF/4ZiVX6Bg/u50DMCb4SNDXaIfM0hxR/\n" +
                "EXryX2NNXx+KSPviUNol+TrWXMeEArfNukfEz4SGrS2u0dt+BEioNr/W9CGKzVTe\n" +
                "Tuj4xCG0Jwu8+s0s61OgKx5Ogn1hs1cP+UnR0HXWwe88kRlTIbFLkQZ/XobdiMHi\n" +
                "+TnqovkmHtpbd6Bso5JxHCFOz8Dc30PEfEssxI1oSQIDAQABo4IB7zCCAeswHwYD\n" +
                "VR0jBBgwFoAUkK9qOpRaC9iQ6hJWc99DtDoo2ucwHQYDVR0OBBYEFBoBL4TWYmxk\n" +
                "fVr2SDJsyeQ8MH3vMA4GA1UdDwEB/wQEAwIFoDAMBgNVHRMBAf8EAjAAMB0GA1Ud\n" +
                "JQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjBPBgNVHSAESDBGMDoGCysGAQQBsjEB\n" +
                "AgIHMCswKQYIKwYBBQUHAgEWHWh0dHBzOi8vc2VjdXJlLmNvbW9kby5jb20vQ1BT\n" +
                "MAgGBmeBDAECATBUBgNVHR8ETTBLMEmgR6BFhkNodHRwOi8vY3JsLmNvbW9kb2Nh\n" +
                "LmNvbS9DT01PRE9SU0FEb21haW5WYWxpZGF0aW9uU2VjdXJlU2VydmVyQ0EuY3Js\n" +
                "MIGFBggrBgEFBQcBAQR5MHcwTwYIKwYBBQUHMAKGQ2h0dHA6Ly9jcnQuY29tb2Rv\n" +
                "Y2EuY29tL0NPTU9ET1JTQURvbWFpblZhbGlkYXRpb25TZWN1cmVTZXJ2ZXJDQS5j\n" +
                "cnQwJAYIKwYBBQUHMAGGGGh0dHA6Ly9vY3NwLmNvbW9kb2NhLmNvbTA9BgNVHREE\n" +
                "NjA0ghZmcmVlLWFwaS5oZXdlYXRoZXIuY29tghp3d3cuZnJlZS1hcGkuaGV3ZWF0\n" +
                "aGVyLmNvbTANBgkqhkiG9w0BAQsFAAOCAQEAYjTe3An5dTtwh5QYvGULccXI7CBm\n" +
                "zq/Ly/8ai3z1BBTv4iuQUW3iVprbTcLreKQOLsGD77tM0uIw9Nh44kgrZD9MutEb\n" +
                "TWHsThFL4S+p3TRmQgAyZdefFQKGPaeRbyJDvi/6/NE30abC9Ju+OIuhAwZdSM6u\n" +
                "gslavHeWuM/SDJ9y4IC2G+smPUa12P3vd/9z5oRQC2KVrqRpJECRyxsbBpYtx21T\n" +
                "NyLlluGmJt6Igt5n5THPCNqq7iVDRxvZ0ne+2vXbfkPkB8QzIaba+fICdpclNQBE\n" +
                "RsZwpqKurLH3+WCWy+s7EiHrC9RFY1Bxv4FAdSdt7OwcJncCaxPh+54veg==\n" +
                "-----END CERTIFICATE-----";

    }
}
