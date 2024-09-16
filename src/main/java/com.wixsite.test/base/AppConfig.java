package com.wixsite.test.base;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:config.properties" // mention the property file name
})
public interface AppConfig extends Config {
    String appUrl();

    String browserName();

}