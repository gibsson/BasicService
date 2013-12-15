Basic Android System Service
============================

https://github.com/gibsson/BasicService

This project intends to be an easy-to-use skeleton for any kind of Android System Service.

Its implementation is very simple and exposes only two functions get/set of an integer.

Its architecture has been inspired from the more featured Marakana Log Service example:
https://github.com/twitter-university/alpha

It consists of two main parts: one java library and one java application.
Below is a quick description of the project files.

1) com.gibsson.basic.service.lib (src/com/gibsson/basic/service/lib)
 - IBasicService.aidl: Android Interface Definition Language (AIDL) which describes the API
 - BasicManager.java: Manager that handles the connections from the application to the Service
 - com.gibsson.basic.service.lib.xml: Permission that allows application to use the generated library

2) BasicServiceApp (src/com/gibsson/basic/service/app)
 - IBasicServiceImpl.java: Actual implementation of the IBasicService API
 - BasicServiceApp.java: Application that registers the latter implementation as a System Service

In order to have the service built-in your Android device or emulator, the packages must be explicitly requested as follows:

PRODUCT_PACKAGES += \
    com.gibsson.basic.service.lib \
    com.gibsson.basic.service.lib.xml \
    BasicServiceApp

A sample client application is provided in another git repository:
https://github.com/gibsson/BasicClient

To enable application developers to access the library API, an SDK add-on can be generated:
https://github.com/gibsson/basic_sdk_addon

