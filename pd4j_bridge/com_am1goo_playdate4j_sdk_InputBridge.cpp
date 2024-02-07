#include "com_am1goo_playdate4j_sdk_InputBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_setPeripheralsEnabled
  (JNIEnv* env, jobject thisObject, jint maskValue) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDPeripherals mask = static_cast<PDPeripherals>(maskValue);
	api->system->setPeripheralsEnabled(mask);
}
  
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getAccelerometer
  (JNIEnv* env, jobject thisObject, jobject acc) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	float outx;
	float outy;
	float outz;
	api->system->getAccelerometer(&outx, &outy, &outz);
	
	jclass class_acc = env->GetObjectClass(acc);
	jmethodID class_acc_method_set = env->GetMethodID(class_acc, "set", "(FFF)V");
	env->CallVoidMethod(acc, class_acc_method_set, outx, outy, outz);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getButtonState
  (JNIEnv* env, jobject thisObject, jobject state) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDButtons current;
	PDButtons pushed;
	PDButtons released;
	api->system->getButtonState(&current, &pushed, &released);
	
	jclass class_state = env->GetObjectClass(state);
	jmethodID class_state_method_set = env->GetMethodID(class_state, "set", "(III)V");
	env->CallVoidMethod(state, class_state_method_set, static_cast<int>(current), static_cast<int>(pushed), static_cast<int>(released));
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getCrankAngle
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->system->getCrankAngle();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getCrankChange
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->system->getCrankChange();
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_isCrankDocked
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->system->isCrankDocked();
}