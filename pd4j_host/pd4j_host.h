#ifdef __cplusplus
#define EXTERNC extern "C"
#else
#define EXTERNC
#endif

EXTERNC int pd4j_init();
EXTERNC int pd4j_shutdown();
EXTERNC int pd4j_update();

#undef EXTERNC