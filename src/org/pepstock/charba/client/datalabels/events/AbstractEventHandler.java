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
package org.pepstock.charba.client.datalabels.events;

import org.pepstock.charba.client.datalabels.DataLabelsContext;
import org.pepstock.charba.client.events.ChartEventContext;

/**
 * Abstract event handler which implements all interfaces to listen DATA LABELS events.<br>
 * Can be used as base class for custom implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEventHandler implements EnterEventHandler, LeaveEventHandler, ClickEventHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.ClickEventHandler#onClick(org.pepstock.charba.client.datalabels.DataLabelsContext,
	 * org.pepstock.charba.client.events.ChartEventContext)
	 */
	@Override
	public boolean onClick(DataLabelsContext context, ChartEventContext event) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.LeaveEventHandler#onLeave(org.pepstock.charba.client.datalabels.DataLabelsContext,
	 * org.pepstock.charba.client.events.ChartEventContext)
	 */
	@Override
	public boolean onLeave(DataLabelsContext context, ChartEventContext event) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.EnterEventHandler#onEnter(org.pepstock.charba.client.datalabels.DataLabelsContext,
	 * org.pepstock.charba.client.events.ChartEventContext)
	 */
	@Override
	public boolean onEnter(DataLabelsContext context, ChartEventContext event) {
		return true;
	}

}