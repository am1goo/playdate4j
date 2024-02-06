#include "pd4j_host.h"

PlaydateAPI* _api;

PlaydateAPI* PlaydateHost::getApi()
{
	return _api;
}

void PlaydateHost::setApi(PlaydateAPI* api)
{
	_api = api;
}