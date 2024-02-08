#include "com_am1goo_playdate4j_sdk_FilesystemBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_getError
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	const char* err = api->file->geterr();
	return env->NewStringUTF(err);
}