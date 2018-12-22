#include <jni.h>
#include <string>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <termios.h>
#include <android/log.h>
#include <sys/ioctl.h>
#include <fcntl.h>

int buzzerfd=0;

extern "C"
JNIEXPORT jint JNICALL
Java_hardlib_hardbuzzer_buzzerfdopen(JNIEnv *env, jobject instance, jstring fdname_)
{
    const char *fdname = env->GetStringUTFChars(fdname_, 0);
    if((buzzerfd = open(fdname, O_RDWR|O_NOCTTY|O_NDELAY))<0)
    {
        __android_log_print(ANDROID_LOG_INFO, "buzzer", "[buzzer]can't open buzzer fd\n");
        goto err;
    }
    env->ReleaseStringUTFChars(fdname_, fdname);
    goto  fdok;
    err:
    return -1;
    fdok:
    return buzzerfd;
}extern "C"




JNIEXPORT jint JNICALL
Java_hardlib_hardbuzzer_buzzerioctl(JNIEnv *env, jobject instance, jint cmd) {

    // TODO
    if(buzzerfd>0)
    {
        ioctl(buzzerfd,cmd);
        return 0;
    }
    return -1;

}extern "C"



JNIEXPORT jint JNICALL
Java_hardlib_hardbuzzer_buzzerfdclose(JNIEnv *env, jobject instance) {

    if(buzzerfd > 0)
    {
        close(buzzerfd);
        __android_log_print(ANDROID_LOG_INFO, "buzzer", "[Sucess]close buzzer fd Sucess");
        return 0;
    }
    return -1;
}