#include "com_am1goo_playdate4j_sdk_DisplayBridge.h"
#include "pd4j_host.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setRefreshRate
  (JNIEnv* env, jobject thisObject, jint rate) {
	PlaydateAPI* api = PlaydateHost::getApi();
	if (api == NULL)
		return;

	api->display->setRefreshRate(rate);
}
