package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;

public class ChartItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		innerRadius,
		chartArea
	}
	
	/**
	 * Needed for GWt injection
	 */
	protected ChartItem() {
		// do nothing
	}
	
	public final double getInnerRadius(){
		return getDouble(Property.innerRadius.name());
	}
	
	public final ChartAreaItem getChartArea(){
		return (ChartAreaItem)getJavaScriptObject(Property.chartArea.name());
	}

}