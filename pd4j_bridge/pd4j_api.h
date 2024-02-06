#include <pd_api.h>

class PlaydateHost
{
	public:
		static PlaydateAPI* getApi();
		static void setApi(PlaydateAPI* api);

		// Disallow creating an instance of this object
		// (Making all constructors private also works but is not ideal and does not
		// convey your intent as well)
		PlaydateHost() = delete;
};