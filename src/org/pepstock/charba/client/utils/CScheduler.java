/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.items.Undefined;

/**
 * This class provides asynchronous and delayed task scheduling, based
 * <a href="https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/setTimeout">setTimeout</a> java script method.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CScheduler {

	// instance of singleton
	private static final CScheduler INSTANCE = new CScheduler();

	/**
	 * To avoid any instantiation
	 */
	private CScheduler() {
		// do nothing
	}

	/**
	 * Returns the instance of singleton.
	 *
	 * @return the instance of singleton
	 */
	public static CScheduler get() {
		return INSTANCE;
	}

	/**
	 * Schedules immediately a task, without any delay.
	 *
	 * @param task the command to execute
	 * @return the task scheduling registration for canceling, if needed
	 */
	public CSchedulerRegistration submit(Runnable task) {
		return submit(task, 0);
	}

	/**
	 * Schedules a task with a constant delay.
	 *
	 * @param task the command to execute
	 * @param delay the amount of time to wait after one invocation ends before the next invocation
	 * @return the task scheduling registration for canceling, if needed
	 */
	public CSchedulerRegistration submit(Runnable task, int delay) {
		// checks if command is consistent
		Checker.checkIfValid(task, "Task instance");
		// normalized the delay
		int normDelay = Math.max(delay, 0);
		// creates the wrapper for task
		RunnableWrapper wrapper = new RunnableWrapper(task);
		// invokes the runnable
		int timeoutID = Window.setTimeout(wrapper::run, normDelay);
		// stores id to wrapper
		wrapper.setTimeoutID(timeoutID);
		// return the registration for task
		return new CSchedulerRegistration(timeoutID);
	}

	/**
	 * Wrapper of user task in order to clear timeout the user logic is completed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class RunnableWrapper implements Runnable {

		/**
		 * Enums the status
		 */
		private enum Status
		{
			NOT_STARTED,
			STARTED,
			ENDED;
		}

		// delegate task to execute
		private final Runnable task;
		// timeout id instance
		private int timeoutID = Undefined.INTEGER;
		// flag to store the status
		// 0: not started, 1: running, 2: ended
		private Status status = Status.NOT_STARTED;

		/**
		 * Creates the object wrapping the runnable task to execute.
		 * 
		 * @param task delegated task to execute
		 */
		private RunnableWrapper(Runnable task) {
			this.task = task;
		}

		/**
		 * Sets the identifier of the timeout you want to cancel.
		 * 
		 * @param timeoutID the identifier of the timeout you want to cancel
		 */
		private void setTimeoutID(int timeoutID) {
			// stores the timeout id
			this.timeoutID = timeoutID;
			// checks if is already ended
			if (Status.ENDED.equals(status)) {
				// already ended
				// therefore clear the timeout
				// because this method has been invoked
				// after the execution of user tasks
				// which didn0t clear anything due to timeout id
				// wasn't consistent
				clear();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// changes status
			status = Status.STARTED;
			// execute the task
			task.run();
			// clears the timeout
			clear();
		}

		/**
		 * Cancels a timeout previously established.
		 */
		private void clear() {
			// checks if timeout id is consistent
			if (Undefined.isNot(timeoutID)) {
				// clears the timeout
				Window.clearTimeout(timeoutID);
			}
		}
	}

}