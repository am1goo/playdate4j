#include "com_am1goo_playdate4j_sdk_FilesystemBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

#define FS_ERR -1;

JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_getError
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return NULL;
	
	const char* err = api->file->geterr();
	return env->NewStringUTF(err);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_unlink
  (JNIEnv* env, jobject thisObject, jstring path_str, jboolean recursive) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	int ret = api->file->unlink(path, recursive);
	env->ReleaseStringUTFChars(path_str, path);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_mkdir
  (JNIEnv* env, jobject thisObject, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	int ret = api->file->mkdir(path);
	env->ReleaseStringUTFChars(path_str, path);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_rename
  (JNIEnv* env, jobject thisObject, jstring from_str, jstring to_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	const char* from = env->GetStringUTFChars(from_str, 0);
	const char* to = env->GetStringUTFChars(to_str, 0);
	int ret = api->file->rename(from, to);
	env->ReleaseStringUTFChars(from_str, from);
	env->ReleaseStringUTFChars(to_str, to);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_stat
  (JNIEnv* env, jobject thisObject, jstring path_str, jobject file_stat) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	FileStat stat;
	int ret = api->file->stat(path, &stat);
	env->ReleaseStringUTFChars(path_str, path);
	
	jclass class_file_state = env->GetObjectClass(file_stat);
	jmethodID class_file_state_method_set = env->GetMethodID(class_file_state, "set", "(ZJIIIIII)V");
	env->CallVoidMethod(file_stat, class_file_state_method_set, stat.isdir, stat.size, stat.m_year, stat.m_month, stat.m_day, stat.m_hour, stat.m_minute, stat.m_second);
	return ret;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_open
  (JNIEnv* env, jobject thisObject, jstring path_str, jint mode_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	FileOptions mode = static_cast<FileOptions>(mode_value);
	SDFile* file = api->file->open(path, mode);
	env->ReleaseStringUTFChars(path_str, path);
	if (file == NULL)
		return 0;
		
	uintptr_t file_ptr = reinterpret_cast<uintptr_t>(file);
	return file_ptr;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_close
  (JNIEnv* env, jobject thisObject, jlong file_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	SDFile* file = reinterpret_cast<SDFile*>(file_ptr);
	int ret = api->file->close(file);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_flush
  (JNIEnv* env, jobject thisObject, jlong file_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	SDFile* file = reinterpret_cast<SDFile*>(file_ptr);
	int ret = api->file->flush(file);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_FilesystemBridge_tell
  (JNIEnv* env, jobject thisObject, jlong file_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return FS_ERR;
	
	SDFile* file = reinterpret_cast<SDFile*>(file_ptr);
	int ret = api->file->tell(file);
	return ret;
}