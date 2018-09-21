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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.callbacks.TooltipLabelColor;
import org.pepstock.charba.client.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipItemArray;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsCallbacks {
	adfafa
	// empty string 
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
	
	/**
	 * Creates the object using the tooltip one.
	 * 
	 * @param tooltips tooltip object
	 */
	TooltipsCallbacks(Tooltips tooltips) {
		this.tooltips = tooltips;
		// sets the colors getting from tooltip
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
	 * Called before body creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onBeforeBody(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (bodyCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return bodyCallback.onBeforeBody(tooltips.getChart(), items.getItems());
		}
		return new String[0];
	}
	
	/**
	 * Called after body creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onAfterBody(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (bodyCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return bodyCallback.onAfterBody(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called before title creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onBeforeTitle(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (titleCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return titleCallback.onBeforeTitle(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called title creation
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onTitle(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (titleCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return titleCallback.onTitle(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called after title creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onAfterTitle(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (titleCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return titleCallback.onAfterTitle(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called before footer creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onBeforeFooter(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (footerCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return footerCallback.onBeforeFooter(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called footer creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onFooter(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (footerCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return footerCallback.onFooter(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called after footer creation.
	 * @param object java script object tooltips items
	 * @return array of strings for body. Default is an empty array.
	 */
	protected String[] onAfterFooter(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (footerCallback != null){
			TooltipItemArray items = new TooltipItemArray(object);
			return footerCallback.onAfterFooter(tooltips.getChart(), items.getItems());
		}
		// empty array
		return new String[0];
	}

	/**
	 * Called before label creation.
	 * @param object java script object tooltips items
	 * @return string for label. Default is an empty string.
	 */
	protected String onBeforeLabel(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (labelCallback != null){
			TooltipItem item = new TooltipItem(object);
			return labelCallback.onBeforeLabel(tooltips.getChart(), item);
		}
		// empty string
		return EMPTY;
	}

	/**
	 * Called label creation.
	 * @param object java script object tooltips items
	 * @return string for label. Default is an empty string.
	 */
	protected String onLabel(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (labelCallback != null){
			TooltipItem item = new TooltipItem(object);
			return labelCallback.onLabel(tooltips.getChart(), item);
		}
		// empty string
		return EMPTY;
	}
	
	/**
	 * Called text label color creation.
	 * @param object java script object tooltips items
	 * @return string for label color.
	 */
	protected String onLabelTextColor(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (labelCallback != null){
			TooltipItem item = new TooltipItem(object);
			IsColor color = labelCallback.onLabelTextColor(tooltips.getChart(), item); 
			return color != null ? color.toRGBA() : null;
		}
		// empty string
		return EMPTY;
	}
	
	/**
	 * Called label color creation.
	 * @param object java script object tooltips items
	 * @return label color object.
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelColor
	 */
	protected GenericJavaScriptObject onLabelColor(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (labelCallback != null){
			TooltipItem item = new TooltipItem(object);
			TooltipLabelColor result = labelCallback.onLabelColor(tooltips.getChart(), item);
			if (result != null){
				return result.getObject();
			}
		}
		// default color
		return DEFAULT_LABEL_COLOR.getObject();
	}

	/**
	 * Called after label creation.
	 * @param object java script object tooltips items
	 * @return string for label.
	 */
	protected String onAfterLabel(GenericJavaScriptObject object){
		// checks if callbacks is set
		if (labelCallback != null){
			TooltipItem item = new TooltipItem(object);
			return labelCallback.onAfterLabel(tooltips.getChart(), item);
		}
		// empty string
		return EMPTY;
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeTitleHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.title = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onTitle(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	    options.beforeTitle = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeTitle(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	    options.afterTitle = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterTitle(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeBodyHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.beforeBody = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeBody(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	    options.afterBody = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterBody(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeFooterHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.footer = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onFooter(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	    options.beforeFooter = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeFooter(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	    options.afterFooter = function(items, data){
	    	var myItems = new Object();
	    	myItems.items = items;
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterFooter(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItems);
	    }
	}-*/;
    
   
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeLabelHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.label = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabel(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(item);
	    }
	    options.beforeLabel = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onBeforeLabel(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(item);
	    }
	    options.afterLabel = function(item, chart){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onAfterLabel(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(item);
	    }
	    options.labelColor = function(item, chart){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabelColor(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(item);
	    }
	    options.labelTextColor = function(item, data){
        	return self.@org.pepstock.charba.client.options.TooltipsCallbacks::onLabelTextColor(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(item);
	    }
	}-*/;

}