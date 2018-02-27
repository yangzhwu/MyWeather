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
                "MIIFVTCCBD2gAwIBAgIRAPTNY7MYRNvVt+n/YbysG0IwDQYJKoZIhvcNAQELBQAw\n" +
                "gZAxCzAJBgNVBAYTAkdCMRswGQYDVQQIExJHcmVhdGVyIE1hbmNoZXN0ZXIxEDAO\n" +
                "BgNVBAcTB1NhbGZvcmQxGjAYBgNVBAoTEUNPTU9ETyBDQSBMaW1pdGVkMTYwNAYD\n" +
                "VQQDEy1DT01PRE8gUlNBIERvbWFpbiBWYWxpZGF0aW9uIFNlY3VyZSBTZXJ2ZXIg\n" +
                "Q0EwHhcNMTgwMTI0MDAwMDAwWhcNMjEwMTIzMjM1OTU5WjBcMSEwHwYDVQQLExhE\n" +
                "b21haW4gQ29udHJvbCBWYWxpZGF0ZWQxHTAbBgNVBAsTFFBvc2l0aXZlU1NMIFdp\n" +
                "bGRjYXJkMRgwFgYDVQQDDA8qLmhld2VhdGhlci5jb20wggEiMA0GCSqGSIb3DQEB\n" +
                "AQUAA4IBDwAwggEKAoIBAQDYeKvZvkJc5lucSP0owOSF27DQMkhqGopaY+1byDzy\n" +
                "lcshG4ZgfEarqxV+ftMcnSwg2oCce8V0MsD39HoDjB/9n6rgM7ojkCKUpbsS9eo0\n" +
                "ep6LjKCtdXUrQ3XGNeHHAK50/xbTZdKmYeURkWQrynZ6dkto93QpodDDck9sgo/m\n" +
                "znN5Wcu57zCYuo35dCTErd19cnpjcolkhMruo2HhBjtgZwDYfW/uvzM6NgKZ6Fo/\n" +
                "RkcsJuH84SAgfOyhXY/L7CmxLvmuV0z0iE0HVC7dzdzAXuR7l1uCDk6rKBI0I1mG\n" +
                "F1ZVfYZPQSsDqPGVyhDSd3s+3Ey0EEiXBHkeW65ynk/hAgMBAAGjggHbMIIB1zAf\n" +
                "BgNVHSMEGDAWgBSQr2o6lFoL2JDqElZz30O0Oija5zAdBgNVHQ4EFgQUzCCl76T8\n" +
                "GplYmWotCtlqWjs6+/0wDgYDVR0PAQH/BAQDAgWgMAwGA1UdEwEB/wQCMAAwHQYD\n" +
                "VR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCME8GA1UdIARIMEYwOgYLKwYBBAGy\n" +
                "MQECAgcwKzApBggrBgEFBQcCARYdaHR0cHM6Ly9zZWN1cmUuY29tb2RvLmNvbS9D\n" +
                "UFMwCAYGZ4EMAQIBMFQGA1UdHwRNMEswSaBHoEWGQ2h0dHA6Ly9jcmwuY29tb2Rv\n" +
                "Y2EuY29tL0NPTU9ET1JTQURvbWFpblZhbGlkYXRpb25TZWN1cmVTZXJ2ZXJDQS5j\n" +
                "cmwwgYUGCCsGAQUFBwEBBHkwdzBPBggrBgEFBQcwAoZDaHR0cDovL2NydC5jb21v\n" +
                "ZG9jYS5jb20vQ09NT0RPUlNBRG9tYWluVmFsaWRhdGlvblNlY3VyZVNlcnZlckNB\n" +
                "LmNydDAkBggrBgEFBQcwAYYYaHR0cDovL29jc3AuY29tb2RvY2EuY29tMCkGA1Ud\n" +
                "EQQiMCCCDyouaGV3ZWF0aGVyLmNvbYINaGV3ZWF0aGVyLmNvbTANBgkqhkiG9w0B\n" +
                "AQsFAAOCAQEAZ+v+A6xhgq+ZQEbr7YjkGi2kms7Lu180cyo+1F7XPUSfJJ8yaYNJ\n" +
                "NQGb0Q5wBXzhVm3UQvJoVD55cKJioJnRLoySVqnmKR8oyMJ/FRb5nj2Vb0oPO86j\n" +
                "RqZfih39UkTetJf/5DSXOO4bh2r02XLoaTYDr/wlS1i7LzSUW2zsFstgszf1Gki9\n" +
                "2Ug71bysbhyLICTPBWqkX575gy5dAZpzaLcWXWT6XWxQSN27C2yEJxOCvlG6mgoK\n" +
                "ChtZLA3hodeEWxNZ0+JP9Kt7+xfp6mg4yVtDriTYDwaR/PfNXlvHyOOwVctPpxTo\n" +
                "ssAuJsyrA4Ml0/5NBsAwKGTKY3jL5K0cEg==\n" +
                "-----END CERTIFICATE-----";

    }
}
