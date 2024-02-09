//
//  main.c
//  Extension
//
//  Created by Dave Hayden on 7/30/14.
//  Copyright (c) 2014 Panic, Inc. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

#include "pd_api.h"
#include "pd4j_host.h"

static int update(void* userdata);

#ifdef _WINDLL
__declspec(dllexport)
#endif
int eventHandler(PlaydateAPI* pd, PDSystemEvent event, uint32_t arg)
{
	(void)arg; // arg is currently only used for event = kEventKeyPressed

	switch (event)
	{
		case kEventInit:
			pd->system->setUpdateCallback(update, pd);
			pd4j_init(pd);
			return 0;

		case kEventTerminate:
			pd4j_shutdown();
			return 0;
	}

	return 0;
}

static int update(void* userdata)
{
	int redraw = 0;
	int ret = pd4j_update(&redraw);
	if (ret != PD4J_OK)
		return 0;

	return redraw;
}

