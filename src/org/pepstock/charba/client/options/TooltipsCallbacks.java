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

import org.pepstock.charba.client.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.callbacks.TooltipLabelColor;
import org.pepstock.charba.client.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipItemArray;

/**
 * 
 */
public final class TooltipsCallbacks extends JavaScriptObjectContainer{
	
	private static final String EMPTY = "";
	
	private static final TooltipLabelColor DEFAULT_LABEL_COLOR = new TooltipLabelColor();
	
	private final Tooltips tooltips;
	
	private TooltipTitleCallback titleCallback = null;
	
	private TooltipBodyCallback bodyCallback = null;
	
	private TooltipLabelCallback labelCallback = null;
	
	private TooltipFooterCallback footerCallback = null;

	private enum Property implements Key{
	    beforeTitle,
	    title,
	    afterTitle,
	    beforeBody,
	    beforeLabel,
	    label,
	    labelColor,
	    labelTextColor,
	    afterLabel,
	    afterBody,
	    beforeFooter,
	    footer,
	    afterFooter
	}
	
	TooltipsCallbacks(Tooltips tooltips) {
		this.tooltips = tooltips;
		DEFAULT_LABEL_COLOR.setBackgroundColor(tooltips.getBackgroundColor());
		DEFAULT_LABEL_COLOR.setBackgroundColor(tooltips.getBorderColor());
	}
	
	/**
	 * @return the titleCallback
	 */
	public TooltipTitleCallback getTitleCallback() {
		return titleCallback;
	}

	/**
	 * @param titleCallback the titleCallback to set
	 */
	public void setTitleCallback(TooltipTitleCallback titleCallback) {
		if (hasToBeRegistered(titleCallback, Property.beforeTitle, Property.afterTitle, Property.title)){
			registerNativeTitleHandlers(getJavaScriptObject());
		}
		this.titleCallback = titleCallback;
	}

	/**
	 * @return the bodyCallback
	 */
	public TooltipBodyCallback getBodyCallback() {
		return bodyCallback;
	}

	/**
	 * @param bodyCallback the bodyCallback to set
	 */
	public void setBodyCallback(TooltipBodyCallback bodyCallback) {
		if (hasToBeRegistered(bodyCallback, Property.beforeBody, Property.afterBody)){
			registerNativeBodyHandlers(getJavaScriptObject());
		}
		this.bodyCallback = bodyCallback;
	}

	/**
	 * @return the labelCallback
	 */
	public TooltipLabelCallback getLabelCallback() {
		return labelCallback;
	}

	/**
	 * @param labelCallback the labelCallback to set
	 */
	public void setLabelCallback(TooltipLabelCallback labelCallback) {
		if (hasToBeRegistered(labelCallback, Property.beforeLabel, Property.afterLabel, Property.label, Property.labelColor, Property.labelTextColor)){
			registerNativeLabelHandlers(getJavaScriptObject());
		}
		this.labelCallback = labelCallback;
	}

	/**
	 * @return the footerCallback
	 */
	public TooltipFooterCallback getFooterCallback() {
		return footerCallback;
	}

	/**
	 * @param footerCallback the footerCallback to set
	 */
	public void setFooterCallback(TooltipFooterCallback footerCallback) {
		if (hasToBeRegistered(footerCallback, Property.beforeFooter, Property.afterFooter, Property.footer)){
			registerNativeFooterHandlers(getJavaScriptObject());
		}
		this.footerCallback = footerCallback;
	}

	/**
	 * 
	 * @param chartId
	 * @param items
	 * @return
	 */
	protected String[] onBeforeBody(TooltipItemArray items){
		if (bodyCallback != null){
			return bodyCallback.onBeforeBody(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}
	
	protected String[] onAfterBody(TooltipItemArray items){
		if (bodyCallback != null){
			return bodyCallback.onAfterBody(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onBeforeTitle(TooltipItemArray items){
		if (titleCallback != null){
			return titleCallback.onBeforeTitle(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onTitle(TooltipItemArray items){
		if (titleCallback != null){
			return titleCallback.onTitle(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onAfterTitle(TooltipItemArray items){
		if (titleCallback != null){
			return titleCallback.onAfterTitle(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onBeforeFooter(TooltipItemArray items){
		if (footerCallback != null){
			return footerCallback.onBeforeFooter(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onFooter(TooltipItemArray items){
		if (footerCallback != null){
			return footerCallback.onFooter(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String[] onAfterFooter(TooltipItemArray items){
		if (footerCallback != null){
			return footerCallback.onAfterFooter(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}

	protected String onBeforeLabel(TooltipItem item){
		if (labelCallback != null){
			return labelCallback.onBeforeLabel(tooltips.getChart(), item);
		}
		return EMPTY;
	}

	protected String onLabel(TooltipItem item){
		if (labelCallback != null){
			return labelCallback.onLabel(tooltips.getChart(), item);
		}
		return EMPTY;
	}
	
	protected String onLabelTextColor(TooltipItem item){
		if (labelCallback != null){
			return labelCallback.onLabelTextColor(tooltips.getChart(), item);
		}
		return EMPTY;
	}
	
	protected GenericJavaScriptObject onLabelColor(TooltipItem item){
		if (labelCallback != null){
			TooltipLabelColor result = labelCallback.onLabelColor(tooltips.getChart(), item);
			if (result != null){
				return result.getObject();
			}
		}
		return DEFAULT_LABEL_COLOR.getObject();
	}

	protected String onAfterLabel(TooltipItem item){
		if (labelCallback != null){
			return labelCallback.onAfterLabel(tooltips.getChart(), item);
		}
		return EMPTY;
	}

	/**
	 * 
	 * @param options
	 */
    private native void registerNativeTitleHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.title = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onTitle(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	    options.beforeTitle = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeTitle(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	    options.afterTitle = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterTitle(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	}-*/;

	/**
	 * 
	 * @param options
	 */
    private native void registerNativeBodyHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.beforeBody = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeBody(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	    options.afterBody = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterBody(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	}-*/;

	/**
	 * 
	 * @param options
	 */
    private native void registerNativeFooterHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.footer = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onFooter(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	    options.beforeFooter = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeFooter(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	    options.afterFooter = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterFooter(Lorg/pepstock/charba/client/items/TooltipItemArray;)(myItems);
	    }
	}-*/;
    
   
	/**
	 * 
	 * @param options
	 */
    private native void registerNativeLabelHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.label = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabel(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	    options.beforeLabel = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeLabel(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	    options.afterLabel = function(item, chart){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterLabel(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	    options.labelColor = function(item, chart){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabelColor(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	    options.labelTextColor = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabelTextColor(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	}-*/;

}