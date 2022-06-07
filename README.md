# WorkManager OOM

This project demonstrates how the `GreedyScheduler.mConstrainedWorkSpecs` can grow unbounded, as well as `SerialExecutor.mTasks` due to worker constraints. In degenerate cases, this can cause OOM errors. Filed at https://issuetracker.google.com/issues/235259756

## Repro
* Put your emulator/device into airplane mode
* Repeatedly launch and background the app
* Place breakpoints in `GreedyScheduler.schedule` (L156 in WorkManager v2.7.1) and see how `mConstrainedWorkSpecs` grows unbounded.
* You can also place a breakpoint in `SerialExecutor.execute` to see that its `mTasks` Array grows unbounded as well. When `mTasks` runs out of room, it doubles in size.
* Since WorkManager keeps it unexecuted tasks in a db, killing and restarting the app does not fix the issue.
