#include "pd4j_consts.h"
#include "pd_api.h"

#ifdef __cplusplus
#define EXTERNC extern "C"
#else
#define EXTERNC
#endif

typedef struct Options Options;

struct Options
{
	const char* pathToJar;
	const char* pathToLibs;
	const char* gameCycleClass;
};

EXTERNC int pd4j_init(PlaydateAPI* api, Options* options);
EXTERNC int pd4j_update(int* redraw);
EXTERNC int pd4j_shutdown();

#undef EXTERNC