LOCAL_PATH:= $(call my-dir)

# Build the library
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := com.gibsson.basic.service.lib
LOCAL_SRC_FILES := $(call all-java-files-under,src/com/gibsson/basic/service/lib)
LOCAL_SRC_FILES += src/com/gibsson/basic/service/lib/IBasicService.aidl
include $(BUILD_JAVA_LIBRARY)

# Build the documentation
include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(call all-java-files-under,src/com/gibsson/basic/service/lib)
LOCAL_MODULE:= com.gibsson.basic.service.lib_doc
LOCAL_DROIDDOC_OPTIONS := com.gibsson.basic.service.lib
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_DROIDDOC_USE_STANDARD_DOCLET := true
include $(BUILD_DROIDDOC)

# Copy com.gibsson.basic.lib.xml to /system/etc/permissions/
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := com.gibsson.basic.service.lib.xml
LOCAL_MODULE_CLASS := ETC
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := src/com/gibsson/basic/service/lib/com.gibsson.basic.service.lib.xml
include $(BUILD_PREBUILT)

# Build the service implementation
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(call all-java-files-under,src/com/gibsson/basic/service/app)
LOCAL_REQUIRED_MODULES := \
	com.gibsson.basic.service.lib
LOCAL_JAVA_LIBRARIES := \
	com.gibsson.basic.service.lib \
	core \
	framework
LOCAL_PACKAGE_NAME := BasicServiceApp
LOCAL_SDK_VERSION := current
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_CERTIFICATE := platform
LOCAL_REQUIRED_MODULES := libjni_basicservice
include $(BUILD_PACKAGE)

# Build JNI Shared Library
LOCAL_PATH:= $(LOCAL_PATH)/jni
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_CFLAGS := -Werror -Wno-error=unused-parameter
LOCAL_SRC_FILES:= jni_basicservice.cpp
LOCAL_C_INCLUDES += $(JNI_H_INCLUDE)
LOCAL_MODULE := libjni_basicservice
LOCAL_SHARED_LIBRARIES := \
	libhardware \
	libnativehelper \
	libcutils
LOCAL_ARM_MODE := arm
include $(BUILD_SHARED_LIBRARY)
