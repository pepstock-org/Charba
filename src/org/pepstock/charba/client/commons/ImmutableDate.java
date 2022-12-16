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
package org.pepstock.charba.client.commons;

import java.util.Date;

/**
 * Class acting as an immutable date class based on the {@link Date} class.<br>
 * Throws {@link UnsupportedOperationException} when mutable methods are invoked.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ImmutableDate extends Date {

	private static final long serialVersionUID = 1L;
	// exception message
	private static final String IMMUTABLE_EXCEPTION_MESSAGE = "ImmutableDate is immutable";

	/**
	 * Allocates a <code>Date</code> object and initializes it so that it represents the time at which it was allocated, measured to the nearest millisecond.
	 */
	public ImmutableDate() {
		super();
	}

	/**
	 * Allocates a <code>Date</code> object and initializes it to represent the specified number of milliseconds since the standard base time known as "the epoch", namely January
	 * 1, 1970, 00:00:00 GMT.
	 *
	 * @param date the milliseconds since January 1, 1970, 00:00:00 GMT.
	 */
	public ImmutableDate(long date) {
		super(date);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param date the day of the month value between 1-31.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setDate(int date) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param hours the hour value.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setHours(int hours) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param minutes the value of the minutes.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setMinutes(int minutes) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param month the month value between 0-11.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setMonth(int month) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param seconds the seconds value.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setSeconds(int seconds) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}.
	 * 
	 * @param time the number of milliseconds.
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setTime(long time) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

	/**
	 * As an ImmutableDate is immutable, this method throws an {@link UnsupportedOperationException}f.
	 * 
	 * @param year the year value
	 * @deprecated As of JDK version 1.1.
	 */
	// Ignores SonarCloud issue, java:S6355 - Deprecated annotations should include explanations, because the annotations are not mapped in J2CL.
	// Ignores SonarCloud issue, java:S1133 - Deprecated code should be removed, because the deprecation must be maintained.
	@SuppressWarnings({ "java:S1133", "java:S6355" })
	@Deprecated
	@Override
	public void setYear(int year) {
		throw new UnsupportedOperationException(IMMUTABLE_EXCEPTION_MESSAGE);
	}

}