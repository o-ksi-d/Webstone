#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#ifdef __cplusplus
extern "C" {
#endif

char *cb(const char *data)
{
  static char hello[2048];
  snprintf(hello,sizeof(hello),"%p JML %s\n<br> YOé\n", data, data);
  return hello;
}

JNIEXPORT jstring JNICALL
Java_com_cod5_webstone_MainActivity_JNIcb(
        JNIEnv *env,
        jobject self,
        jstring arg)
{
  char hello[] = "Hello from C++ jml";
  const char *a;
#ifdef __cplusplus
  a = env->GetStringUTFChars(arg, NULL);
#else
  a = (*env)->GetStringUTFChars(env, arg, NULL);
#endif

#ifdef __cplusplus
  return env->NewStringUTF(cb(a));
#else
  return (*env)->NewStringUTF(env, cb(a));
#endif
}

#ifdef __cplusplus
}
#endif
