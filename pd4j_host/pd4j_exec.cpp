#include "pd4j_exec.h"
#include "pd4j_host.h"

int main()
{
	pd4j_init(NULL);
	for (int i = 0; i < 10; ++i)
	{
		pd4j_update(NULL);
	}
	pd4j_shutdown();
	return 0;
}