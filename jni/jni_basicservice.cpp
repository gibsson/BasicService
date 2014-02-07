#include <jni.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <hardware/hardware.h>
#include "JNIHelp.h"

static const char * class_name = "com/gibsson/basic/service/app/IBasicServiceImpl";
static const char * random_dev = "/dev/random";

static int jniGetValue(JNIEnv *env, jobject object)
{
	int value;
	int fd;

	fd = open(random_dev, O_RDONLY);
	if (fd < 0) {
		android_printLog(ANDROID_LOG_ERROR, "IBasicServiceImpl", "%s: couldn't open %s",
				 __func__, random_dev);
		return -1;
	}

	read(fd, &value, sizeof(int));

	android_printLog(ANDROID_LOG_DEBUG, "IBasicServiceImpl", "%s: returns %d",
			 __func__, value);

	close(fd);

	return value;
}

static JNINativeMethod method_table[] = {
	{ "jniGetValue", "()I", (void *) jniGetValue},
};

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
	JNIEnv* env = NULL;
	if (vm->GetEnv((void**) &env, JNI_VERSION_1_6) == JNI_OK) {
		if (jniRegisterNativeMethods(env, class_name, method_table,
					     NELEM(method_table)) == 0) {
			return JNI_VERSION_1_6;
		}
	}
	return JNI_ERR;
}

