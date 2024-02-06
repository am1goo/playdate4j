#include "com_am1goo_playdate4j_sdk_GraphicsBridge.h"
#include "pd4j_host.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawMode
  (JNIEnv* env, jobject thisObject, jint modeValue) {
	PlaydateAPI* api = PlaydateHost::getApi();
	if (api == NULL)
		return;

	LCDBitmapDrawMode mode = static_cast<LCDBitmapDrawMode>(modeValue);
	api->graphics->setDrawMode(mode);
}