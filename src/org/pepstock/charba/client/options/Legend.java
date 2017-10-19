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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.EventProvider;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.items.LegendItem;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * 
 */
public final class Legend extends EventProvider {
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private static final boolean DEFAULT_FULL_WIDTH = true;
	
	private static final boolean DEFAULT_REVERSE = false;
	
	private final LegendLabels labels;
	
	private int onClickHandlers = 0;
	
	private int onHoverHandlers = 0;
	
	private enum Property implements Key{
		display,
		position,
		fullWidth,
		reverse,
		labels,
		onClick,
		onHover
	}
	
	Legend(AbstractChart<?, ?> chart) {
		super(chart);
		labels = new LegendLabels(chart);
		setValue(Property.labels, labels);
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)){
			if (!has(Property.onClick)){
				registerNativeClickHandler(getJavaScriptObject());
			}
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)){
			if (!has(Property.onHover)){
				registerNativeHoverHandler(getJavaScriptObject());
			}
			onHoverHandlers++;
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)){
			onClickHandlers--;
			if (onClickHandlers == 0){
				remove(Property.onClick);
			}
		} else if (type.equals(ChartHoverEvent.TYPE)){
			onHoverHandlers--;
			if (onHoverHandlers == 0){
				remove(Property.onHover);
			}
		}
	}

	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

    public boolean isDisplay(){
    	return getValue(Property.display, DEFAULT_DISPLAY);
    }

	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
	}

    public boolean isFullWidth(){
    	return getValue(Property.fullWidth, DEFAULT_FULL_WIDTH);
    }

	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

    public boolean isReverse(){
    	return getValue(Property.reverse, DEFAULT_REVERSE);
    }
    
     public void setPosition(Position position){
    	 setValue(Property.position, position);
    }

    public Position getPosition(){
    	return getValue(Property.position, Position.values(), Position.top);
    }    
    
	protected void onClick(ChartNativeEvent event, LegendItem item) {
		getChart().fireEvent(new LegendClickEvent(event, item));
	}

	protected void onHover(ChartNativeEvent event, LegendItem item) {
		getChart().fireEvent(new LegendHoverEvent(event, item));
	}
    
    private native void registerNativeClickHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onClick = function(event, legendItem){
	    	self.@org.pepstock.charba.client.options.Legend::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/LegendItem;)(event, legendItem);
	    }
	}-*/;

    private native void registerNativeHoverHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onHover = function(event, legendItem){
	    	self.@org.pepstock.charba.client.options.Legend::onHover(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/LegendItem;)(event, legendItem);
	    }
	}-*/;

    
}