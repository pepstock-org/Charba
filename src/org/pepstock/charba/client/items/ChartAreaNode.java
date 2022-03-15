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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.JSON;

/**
 * Object which maps the chart area item of CHART.JS chart java script object.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartAreaNode extends BaseBoxItem {

	/**
	 * Creates the item using envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public ChartAreaNode(ChartEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates and returns a {@link IsArea} instance, cloning the chart area.
	 * 
	 * @return a {@link IsArea} instance, cloning the chart area
	 */
	public IsArea toArea() {
		return new ClonedChartAreaNode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.stringify(getNativeObject());
	}

	/**
	 * Internal class which is implementing {@link IsArea} interface filling it with the chart area values.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ClonedChartAreaNode implements IsArea {

		private double top = 0;

		private double left = 0;

		private double right = 0;

		private double bottom = 0;

		/**
		 * To avoid any instantiation
		 * 
		 * @param chartArea chart area instance
		 */
		private ClonedChartAreaNode(ChartAreaNode chartArea) {
			this.top = chartArea.getTop();
			this.left = chartArea.getLeft();
			this.right = chartArea.getRight();
			this.bottom = chartArea.getBottom();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.IsArea#getTop()
		 */
		@Override
		public double getTop() {
			return top;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.IsArea#getRight()
		 */
		@Override
		public double getRight() {
			return right;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.IsArea#getBottom()
		 */
		@Override
		public double getBottom() {
			return bottom;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.IsArea#getLeft()
		 */
		@Override
		public double getLeft() {
			return left;
		}

	}

}