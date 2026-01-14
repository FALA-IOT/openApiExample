package org.example;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapBuilder;

/**
 * @Description
 * @Author wsk
 * @Date 2024-04-10
 */
public class Main {
    public static void main(String[] args) {
        try {

            //**** Need to modify USER_ID and USER_KEY in Constant class to your own first


            //**** HTTP interface address http://open.xzfala.com:9000
            //**** HTTPS interface address https://open.xzfala.com

            //**** API documentation see http://open.xzfala.com:9000/swagger-ui/index.html

            if ("your userId".equals(Constant.USER_ID)) {
                System.out.println("Please modify USER_ID and USER_KEY in Constant class to your own first!!!");
                return;
            }

            if ("your userKey".equals(Constant.USER_KEY)) {
                System.out.println("Please modify USER_ID and USER_KEY in Constant class to your own first!!!");
                return;
            }

            String deviceId = "211042230309001";

            if ("your device Id".equals(deviceId)) {
                System.out.println("Please modify deviceId to your own first!!!");
                return;
            }

            /**
             * Get device information (Non-K series devices)
             *
             * @param deviceId Device Id
             *
             */
            FalaHttp.INSTANCE.getDeviceInfo(deviceId);

            /**
             * Get device data (including latest sensor data from the device) Non-K series devices
             * 1. The system only keeps the latest data, request according to the device upload interval, do not frequently request data in a short time
             *
             * @param deviceId Device Id
             *
             */
            FalaHttp.INSTANCE.getDeviceData(deviceId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}