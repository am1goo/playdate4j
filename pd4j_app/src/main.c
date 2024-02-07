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
const char* fontpath = "/System/Fonts/Asheville-Sans-14-Bold.pft";
LCDFont* font = NULL;

#ifdef _WINDLL
__declspec(dllexport)
#endif
int eventHandler(PlaydateAPI* pd, PDSystemEvent event, uint32_t arg)
{
	(void)arg; // arg is currently only used for event = kEventKeyPressed

	switch (event)
	{
		case kEventInit:
			const char* err;
			font = pd->graphics->loadFont(fontpath, &err);

			if (font == NULL)
				pd->system->error("%s:%i Couldn't load font %s: %s", __FILE__, __LINE__, fontpath, err);

			// Note: If you set an update callback in the kEventInit handler, the system assumes the game is pure C and doesn't run any Lua code in the game
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
	PlaydateAPI* pd = userdata;
	
	pd->graphics->setFont(font);
	pd4j_update();

	return 1;
}

