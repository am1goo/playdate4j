#include "pd4j_api.h"

static uintptr_t api_address;
static jclass class_api;
static jmethodID class_api_method_get_api_address;
static jmethodID class_api_method_set_api_address;

PlaydateAPI* pd4j_get_api(JNIEnv* env)
{
	if (api_address == 0)
	{
		if (class_api == NULL)
			class_api = env->FindClass("com/am1goo/playdate4j/sdk/Api");
		if (class_api_method_get_api_address == NULL)
			class_api_method_get_api_address = env->GetStaticMethodID(class_api, "getApiAddress", "()J");
		api_address = env->CallLongMethod(class_api, class_api_method_get_api_address);
	}
	return reinterpret_cast<PlaydateAPI*>(api_address);
}

void pd4j_set_api(JNIEnv * env, PlaydateAPI* api)
{
	if (class_api == NULL)
		class_api = env->FindClass("com/am1goo/playdate4j/sdk/Api");
	if (class_api_method_set_api_address == NULL)
		class_api_method_set_api_address = env->GetStaticMethodID(class_api, "setApiAddress", "(J)V");
	uintptr_t address = reinterpret_cast<uintptr_t>(api);
	env->CallVoidMethod(class_api, class_api_method_set_api_address, address);
}