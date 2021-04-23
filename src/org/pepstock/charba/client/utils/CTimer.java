/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.items.Undefined;

/**
 * Simple utility to execute repeatedly calls a {@link Runnable} with a fixed time delay between each call.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CTimer {

	/**
	 * Enumerates the possible status of the timer.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public enum Status
	{
		/**
		 * Initial status, when the timer has been created.
		 */
		INITIALIZED,
		/**
		 * When the timer has been started.
		 */
		RUNNING,
		/**
		 * When the timer has been stopped.
		 */
		STOPPED
	}

	// task to execute
	private final Runnable task;
	// interval between the executions in milliseconds
	private int interval;
	// status of the timer
	private Status status = Status.INITIALIZED;
	// an interval ID which uniquely identifies the interval
	private int intervalID = Undefined.INTEGER;

	/**
	 * Creates the timer with a {@link Runnable} to be executed repeatedly and the time, in milliseconds (thousands of a second), the timer should delay in between executions of
	 * the task.
	 * 
	 * @param task the task to be executed repeatedly
	 * @param interval the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task.<br>
	 *            Must be greater than 0.
	 */
	public CTimer(Runnable task, int interval) {
		// checks if task is consistent
		this.task = Checker.checkAndGetIfValid(task, "Task instance");
		// stores interval
		this.interval = Checker.checkAndGetIfGreaterThan(interval, 1, "Interval");
	}

	/**
	 * Returns the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task.
	 * 
	 * @return the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * Returns the status of the timer.
	 * 
	 * @return the status of the timer
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Starts the timer which executes repeatedly calls, setting the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified
	 * task.<br>
	 * The time will override the previous interval time.
	 * 
	 * @param interval the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task.<br>
	 *            Must be greater than 0.
	 */
	public void start(int interval) {
		// checks interval
		this.interval = Checker.checkAndGetIfGreaterThan(interval, 1, "Interval");
		// starts the timer.
		start();
	}

	/**
	 * Starts the timer which executes repeatedly calls.
	 */
	public void start() {
		// checks if the timer is not running
		if (Status.INITIALIZED.equals(status) || Status.STOPPED.equals(status)) {
			// starts the interval in java script
			intervalID = Window.setInterval(task::run, interval);
			// changes status
			status = Status.RUNNING;
		}
	}

	/**
	 * Stops the timer which is currently running.
	 */
	public void stop() {
		// checks if the timer is running
		if (Status.RUNNING.equals(status)) {
			// clears the interval in java script
			Window.clearInterval(intervalID);
			// changes status
			status = Status.STOPPED;
			// resets interval id
			this.intervalID = Undefined.INTEGER;
		}
	}

}