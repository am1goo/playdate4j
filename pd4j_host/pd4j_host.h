#include "pd_api.h"

#ifdef __cplusplus
#define EXTERNC extern "C"
#else
#define EXTERNC
#endif

EXTERNC int pd4j_init(PlaydateAPI* api);
EXTERNC int pd4j_update();
EXTERNC int pd4j_shutdown();

#undef EXTERNC