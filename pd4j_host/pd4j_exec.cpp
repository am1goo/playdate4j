#include "pd4j_exec.h"
#include "pd4j_host.h"

int main()
{
	Options options;
	options.runningInSimulator = true;
	options.pathToJar = "C:\\prototypes\\playdate4j\\out\\artifacts\\playdate4j_jar\\playdate4j.jar";
	options.pathToLibs = "C:\\prototypes\\playdate4j\\pd4j_engine\\lib";
	options.gameCycleClass = "com.am1goo.playdate4j.example.ExampleGameCycle";
	pd4j_init(NULL, &options);
	for (int i = 0; i < 10; ++i)
	{
		int redraw;
		pd4j_update(&redraw);
	}
	pd4j_event(kEventLowPower);
	pd4j_shutdown();
	return 0;
}