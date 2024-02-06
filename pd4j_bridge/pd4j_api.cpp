#include "pd4j_api.h"

static thread_local PlaydateAPI* _api;

PlaydateAPI* PlaydateHost::getApi()
{
	return _api;
}

void PlaydateHost::setApi(PlaydateAPI* api)
{
	_api = api;
}