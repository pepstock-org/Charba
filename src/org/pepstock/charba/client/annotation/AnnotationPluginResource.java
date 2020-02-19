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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-annotation.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-annotation.min.js
	private static final String[] CONTENT = { "/*@preserve!", " * chartjs-plugin-annotation.js", " * http://chartjs.org/", " * Version: 0.5.7", " *", " * Copyright 2016 Evert Timberg", " * Released under the MIT license",
			" * https://github.com/chartjs/Chart.Annotation.js/blob/master/LICENSE.md", " */",
			"!function o(l,r,s){function c(t,e){if(!r[t]){if(!l[t]){var n=\"function\"==typeof require&&require;if(!e&&n)return n(t,!0);if(u)return u(t,!0);var i=new Error(\"Cannot find module '\"+t+\"'\");throw i.code=\"MODULE_NOT_FOUND\",i}var a=r[t]={exports:{}};l[t][0].call(a.exports,function(e){return c(l[t][1][e]||e)},a,a.exports,o,l,r,s)}return r[t].exports}for(var u=\"function\"==typeof require&&require,e=0;e<s.length;e++)c(s[e]);return c}({1:[function(e,t,n){},{}],2:[function(n,e,t){e.exports=function(e){var a=e.helpers,l=n(\"./helpers.js\")(e),o=n(\"./events.js\")(e),r=e.Annotation.types;function i(e){l.decorate(e,\"afterDataLimits\",function(e,t){e&&e(t),l.adjustScaleRange(t)})}function t(i){return function(e,t){var n=e.annotation.options.drawTime;l.elements(e).filter(function(e){return i===(e.options.drawTime||n)}).forEach(function(e){e.configure(),e.transition(t).draw()})}}function s(e){var t=e.plugins;return(t&&t.annotation?t.annotation:null)||e.annotation||{}}return{id:\"annotation\",",
			"beforeInit:function(e){var t=e.options,n=e.annotation={elements:{},options:l.initConfig(s(t)),onDestroy:[],firstRun:!0,supported:!1};e.ensureScalesHaveIDs(),t.scales&&(n.supported=!0,a.each(t.scales.xAxes,i),a.each(t.scales.yAxes,i))},beforeUpdate:function(i){var a=i.annotation;if(a.supported){a.firstRun?a.firstRun=!1:a.options=l.initConfig(s(i.options));var o=[];a.options.annotations.forEach(function(e){var t=e.id||l.objectId();if(!a.elements[t]&&r[e.type]){var n=new r[e.type]({id:t,options:e,chartInstance:i});n.initialize(),a.elements[t]=n,e.id=t,o.push(t)}else a.elements[t]&&o.push(t)}),Object.keys(a.elements).forEach(function(e){-1===o.indexOf(e)&&(a.elements[e].destroy(),delete a.elements[e])})}},beforeDatasetsDraw:t(\"beforeDatasetsDraw\"),afterDatasetsDraw:t(\"afterDatasetsDraw\"),afterDraw:t(\"afterDraw\"),afterInit:function(t){var e=t.annotation.options.events;if(a.isArray(e)&&0<e.length){var n=t.chart.canvas,i=o.dispatcher.bind(t);o.collapseHoverEvents(e).forEach(function(e){a",
			".addEvent(n,e,i),t.annotation.onDestroy.push(function(){a.removeEvent(n,e,i)})})}},destroy:function(e){if(e&&e.annotation)for(var t=e.annotation.onDestroy;0<t.length;)t.pop()()}}}},{\"./events.js\":4,\"./helpers.js\":5}],3:[function(e,t,n){t.exports=function(e){var t=e.helpers;return e.Element.extend({initialize:function(){this.hidden=!1,this.hovering=!1,this._model=t.clone(this._model)||{},this.setDataLimits()},destroy:function(){},setDataLimits:function(){},configure:function(){},inRange:function(){},getCenterPoint:function(){},getWidth:function(){},getHeight:function(){},getArea:function(){},draw:function(){}})}},{}],4:[function(t,e,n){e.exports=function(e){var u=e.helpers,d=t(\"./helpers.js\")(e);function f(e){var t=!1,n=e.filter(function(e){switch(e){case\"mouseenter\":case\"mouseover\":case\"mouseout\":case\"mouseleave\":return!(t=!0);default:return!0}});return t&&-1===n.indexOf(\"mousemove\")&&n.push(\"mousemove\"),n}return{dispatcher:function(o){var e=this.annotation,t=d.element",
			"s(this),n=u.getRelativePosition(o,this.chart),i=d.getNearestItems(t,n),a=f(e.options.events),l=e.options.dblClickSpeed,r=[],s=d.getEventHandlerName(o.type),c=(i||{}).options;if(\"mousemove\"===o.type&&(i&&!i.hovering?[\"mouseenter\",\"mouseover\"].forEach(function(e){var t=d.getEventHandlerName(e),n=d.createMouseEvent(e,o);i.hovering=!0,\"function\"==typeof c[t]&&r.push([c[t],n,i])}):i||t.forEach(function(i){if(i.hovering){i.hovering=!1;var a=i.options;[\"mouseout\",\"mouseleave\"].forEach(function(e){var t=d.getEventHandlerName(e),n=d.createMouseEvent(e,o);\"function\"==typeof a[t]&&r.push([a[t],n,i])})}})),i&&-1<a.indexOf(\"dblclick\")&&\"function\"==typeof c.onDblclick){if(\"click\"===o.type&&\"function\"==typeof c.onClick)return clearTimeout(i.clickTimeout),i.clickTimeout=setTimeout(function(){delete i.clickTimeout,c.onClick.call(i,o)},l),void o.stopImmediatePropagation();\"dblclick\"===o.type&&i.clickTimeout&&(clearTimeout(i.clickTimeout),delete i.clickTimeout)}i&&\"function\"==typ",
			"eof c[s]&&0===r.length&&r.push([c[s],o,i]),0<r.length&&(o.stopImmediatePropagation(),r.forEach(function(e){e[0].call(e[2],e[1])}))},collapseHoverEvents:f}}},{\"./helpers.js\":5}],5:[function(e,t,n){function i(){}function a(e){var t=e.annotation.elements;return Object.keys(t).map(function(e){return t[e]})}function o(){return Math.random().toString(36).substr(2,6)}function r(e){return null!=e&&(\"number\"==typeof e?isFinite(e):!!e)}function s(t,n,i){t[\"$\"+n]||(t[n]?(t[\"$\"+n]=t[n].bind(t),t[n]=function(){var e=[t[\"$\"+n]].concat(Array.prototype.slice.call(arguments));return i.apply(t,e)}):t[n]=function(){var e=[void 0].concat(Array.prototype.slice.call(arguments));return i.apply(t,e)})}function c(e,t){e.forEach(function(e){(t?e[t]:e)()})}function u(e){return\"on\"+e[0].toUpperCase()+e.substring(1)}function d(t,n){try{return new MouseEvent(t,n)}catch(e){try{var i=document.createEvent(\"MouseEvent\");return i.initMouseEvent(t,n.canBubble,n.cancelable,n.view,n.detail,n.screenX,n.screenY,",
			"n.clientX,n.clientY,n.ctrlKey,n.altKey,n.shiftKey,n.metaKey,n.button,n.relatedTarget),i}catch(e){var a=document.createEvent(\"Event\");return a.initEvent(t,n.canBubble,n.cancelable),a}}}t.exports=function(t){var l=t.helpers;return{initConfig:function(e){return e=l.configMerge(t.Annotation.defaults,e),l.isArray(e.annotations)&&e.annotations.forEach(function(e){e.label=l.configMerge(t.Annotation.labelDefaults,e.label)}),e},elements:a,callEach:c,noop:i,objectId:o,isValid:r,decorate:s,adjustScaleRange:function(e){var t=function(t,e,n,i){var a=e.filter(function(e){return!!e._model.ranges[t]}).map(function(e){return e._model.ranges[t]});return{min:a.map(function(e){return Number(e.min)}).reduce(function(e,t){return isFinite(t)&&!isNaN(t)&&t<e?t:e},n),max:a.map(function(e){return Number(e.max)}).reduce(function(e,t){return isFinite(t)&&!isNaN(t)&&e<t?t:e},i)}}(e.id,a(e.chart),e.min,e.max);void 0===e.options.ticks.min&&void 0===e.options.ticks.suggestedMin&&(e.min=t.min),void 0===e.options.tick",
			"s.max&&void 0===e.options.ticks.suggestedMax&&(e.max=t.max),e.handleTickRangeOptions&&e.handleTickRangeOptions()},getNearestItems:function(e,a){var o=Number.POSITIVE_INFINITY;return e.filter(function(e){return e.inRange(a.x,a.y)}).reduce(function(e,t){var n=t.getCenterPoint(),i=l.distanceBetweenPoints(a,n);return i<o?(e=[t],o=i):i===o&&e.push(t),e},[]).sort(function(e,t){var n=e.getArea(),i=t.getArea();return i<n||n<i?n-i:e._index-t._index}).slice(0,1)[0]},getEventHandlerName:u,createMouseEvent:d}}},{}],6:[function(e,t,n){var i=e(\"chart.js\");(i=\"function\"==typeof i?i:window.Chart).Annotation=i.Annotation||{},i.Annotation.drawTimeOptions={afterDraw:\"afterDraw\",afterDatasetsDraw:\"afterDatasetsDraw\",beforeDatasetsDraw:\"beforeDatasetsDraw\"},i.Annotation.defaults={drawTime:\"afterDatasetsDraw\",dblClickSpeed:350,events:[],annotations:[]},i.Annotation.labelDefaults={backgroundColor:\"rgba(0,0,0,0.8)\",fontFamily:i.defaults.global.defaultFontFamily,fontSize:i.defaults.global.defaultF",
			"ontSize,fontStyle:\"bold\",fontColor:\"#fff\",xPadding:6,yPadding:6,cornerRadius:6,position:\"center\",xAdjust:0,yAdjust:0,enabled:!1,content:null},i.Annotation.Element=e(\"./element.js\")(i),i.Annotation.types={line:e(\"./types/line.js\")(i),box:e(\"./types/box.js\")(i)};var a=e(\"./annotation.js\")(i);t.exports=a,i.pluginService.register(a)},{\"./annotation.js\":2,\"./element.js\":3,\"./types/box.js\":7,\"./types/line.js\":8,\"chart.js\":1}],7:[function(t,e,n){e.exports=function(e){var f=t(\"../helpers.js\")(e);return e.Annotation.Element.extend({setDataLimits:function(){var e=this._model,t=this.options,n=this.chartInstance,i=n.scales[t.xScaleID],a=n.scales[t.yScaleID],o=n.chartArea;if(e.ranges={},o){var l=0,r=0;i&&(l=f.isValid(t.xMin)?t.xMin:i.getValueForPixel(o.left),r=f.isValid(t.xMax)?t.xMax:i.getValueForPixel(o.right),e.ranges[t.xScaleID]={min:Math.min(l,r),max:Math.max(l,r)}),a&&(l=f.isValid(t.yMin)?t.yMin:a.getValueForPixel(o.bottom),r=f.isValid(t.yMax)?t.yMax:a.getValueForPixe",
			"l(o.top),e.ranges[t.yScaleID]={min:Math.min(l,r),max:Math.max(l,r)})}},configure:function(){var e=this._model,t=this.options,n=this.chartInstance,i=n.scales[t.xScaleID],a=n.scales[t.yScaleID],o=n.chartArea;e.clip={x1:o.left,x2:o.right,y1:o.top,y2:o.bottom};var l,r,s=o.left,c=o.top,u=o.right,d=o.bottom;i&&(l=f.isValid(t.xMin)?i.getPixelForValue(t.xMin):o.left,r=f.isValid(t.xMax)?i.getPixelForValue(t.xMax):o.right,s=Math.min(l,r),u=Math.max(l,r)),a&&(l=f.isValid(t.yMin)?a.getPixelForValue(t.yMin):o.bottom,r=f.isValid(t.yMax)?a.getPixelForValue(t.yMax):o.top,c=Math.min(l,r),d=Math.max(l,r)),e.left=s,e.top=c,e.right=u,e.bottom=d,e.borderColor=t.borderColor,e.borderWidth=t.borderWidth,e.backgroundColor=t.backgroundColor},inRange:function(e,t){var n=this._model;return n&&e>=n.left&&e<=n.right&&t>=n.top&&t<=n.bottom},getCenterPoint:function(){var e=this._model;return{x:(e.right+e.left)/2,y:(e.bottom+e.top)/2}},getWidth:function(){var e=this._model;return Math.abs(e.right-e.left)},getHeight:fun",
			"ction(){var e=this._model;return Math.abs(e.bottom-e.top)},getArea:function(){return this.getWidth()*this.getHeight()},draw:function(){var e=this._view,t=this.chartInstance.chart.ctx;t.save(),t.beginPath(),t.rect(e.clip.x1,e.clip.y1,e.clip.x2-e.clip.x1,e.clip.y2-e.clip.y1),t.clip(),t.lineWidth=e.borderWidth,t.strokeStyle=e.borderColor,t.fillStyle=e.backgroundColor;var n=e.right-e.left,i=e.bottom-e.top;t.fillRect(e.left,e.top,n,i),t.strokeRect(e.left,e.top,n,i),t.restore()}})}},{\"../helpers.js\":5}],8:[function(t,e,n){e.exports=function(e){var f=e.helpers,h=t(\"../helpers.js\")(e),b=\"horizontal\",g=\"vertical\";function p(t){var n=(t.x2-t.x1)/(t.y2-t.y1),i=t.x1||0;this.m=n,this.b=i,this.getX=function(e){return n*(e-t.y1)+i},this.getY=function(e){return(e-i)/n+t.y1},this.intersects=function(e,t,n){n=n||.001;var i=this.getY(e),a=this.getX(t);return(!isFinite(i)||Math.abs(t-i)<n)&&(!isFinite(a)||Math.abs(e-a)<n)}}return e.Annotation.Element.extend({setDataLimits:function(){var e=this._mod",
			"el,t=this.options;e.ranges={},e.ranges[t.scaleID]={min:t.value,max:t.endValue||t.value}},configure:function(){var e,t,n=this._model,i=this.options,a=this.chartInstance,o=a.chart.ctx,l=a.scales[i.scaleID];if(l&&(e=h.isValid(i.value)?l.getPixelForValue(i.value,i.value.index):NaN,t=h.isValid(i.endValue)?l.getPixelForValue(i.endValue,i.value.index):e),!isNaN(e)){var r=a.chartArea;n.clip={x1:r.left,x2:r.right,y1:r.top,y2:r.bottom},this.options.mode===b?(n.x1=r.left,n.x2=r.right,n.y1=e,n.y2=t):(n.y1=r.top,n.y2=r.bottom,n.x1=e,n.x2=t),n.line=new p(n),n.mode=i.mode,n.labelBackgroundColor=i.label.backgroundColor,n.labelFontFamily=i.label.fontFamily,n.labelFontSize=i.label.fontSize,n.labelFontStyle=i.label.fontStyle,n.labelFontColor=i.label.fontColor,n.labelXPadding=i.label.xPadding,n.labelYPadding=i.label.yPadding,n.labelCornerRadius=i.label.cornerRadius,n.labelPosition=i.label.position,n.labelXAdjust=i.label.xAdjust,n.labelYAdjust=i.label.yAdjust,n.labelEnabled=i.label.enabled,n.labelContent=i.",
			"label.content,o.font=f.fontString(n.labelFontSize,n.labelFontStyle,n.labelFontFamily);var s=o.measureText(n.labelContent).width,c=n.labelFontSize;if(n.labelHeight=c+2*n.labelYPadding,n.labelContent&&f.isArray(n.labelContent)){var u=n.labelContent.slice(0).sort(function(e,t){return t.length-e.length})[0];s=o.measureText(u).width,n.labelHeight=c*n.labelContent.length+2*n.labelYPadding,n.labelHeight+=n.labelYPadding*(n.labelContent.length-1)}var d=function(e,t,n,i,a){var o=e.line,l={},r=0,s=0;switch(!0){case e.mode===g&&\"top\"===e.labelPosition:s=a+e.labelYAdjust,r=t/2+e.labelXAdjust,l.y=e.y1+s,l.x=(isFinite(o.m)?o.getX(l.y):e.x1)-r;break;case e.mode===g&&\"bottom\"===e.labelPosition:s=n+a+e.labelYAdjust,r=t/2+e.labelXAdjust,l.y=e.y2-s,l.x=(isFinite(o.m)?o.getX(l.y):e.x1)-r;break;case e.mode===b&&\"left\"===e.labelPosition:r=i+e.labelXAdjust,s=-n/2+e.labelYAdjust,l.x=e.x1+r,l.y=o.getY(l.x)+s;break;case e.mode===b&&\"right\"===e.labelPosition:r=t+i+e.labelXAdjust,s=-n/2+e.labelYAdjust,l.x=",
			"e.x2-r,l.y=o.getY(l.x)+s;break;default:l.x=(e.x1+e.x2-t)/2+e.labelXAdjust,l.y=(e.y1+e.y2-n)/2+e.labelYAdjust}return l}(n,s,c,n.labelXPadding,n.labelYPadding);n.labelX=d.x-n.labelXPadding,n.labelY=d.y-n.labelYPadding,n.labelWidth=s+2*n.labelXPadding,n.borderColor=i.borderColor,n.borderWidth=i.borderWidth,n.borderDash=i.borderDash||[],n.borderDashOffset=i.borderDashOffset||0}},inRange:function(e,t){var n=this._model;return n.line&&n.line.intersects(e,t,this.getHeight())||n.labelEnabled&&n.labelContent&&e>=n.labelX&&e<=n.labelX+n.labelWidth&&t>=n.labelY&&t<=n.labelY+n.labelHeight},getCenterPoint:function(){return{x:(this._model.x2+this._model.x1)/2,y:(this._model.y2+this._model.y1)/2}},getWidth:function(){return Math.abs(this._model.right-this._model.left)},getHeight:function(){return this._model.borderWidth||1},getArea:function(){return Math.sqrt(Math.pow(this.getWidth(),2)+Math.pow(this.getHeight(),2))},draw:function(){var e=this._view,t=this.chartInstance.chart.ctx;if(e.clip){if(t.save(",
			"),t.beginPath(),t.rect(e.clip.x1,e.clip.y1,e.clip.x2-e.clip.x1,e.clip.y2-e.clip.y1),t.clip(),t.lineWidth=e.borderWidth,t.strokeStyle=e.borderColor,t.setLineDash&&t.setLineDash(e.borderDash),t.lineDashOffset=e.borderDashOffset,t.beginPath(),t.moveTo(e.x1,e.y1),t.lineTo(e.x2,e.y2),t.stroke(),e.labelEnabled&&e.labelContent)if(t.beginPath(),t.rect(e.clip.x1,e.clip.y1,e.clip.x2-e.clip.x1,e.clip.y2-e.clip.y1),t.clip(),t.fillStyle=e.labelBackgroundColor,f.drawRoundedRectangle(t,e.labelX,e.labelY,e.labelWidth,e.labelHeight,e.labelCornerRadius),t.fill(),t.font=f.fontString(e.labelFontSize,e.labelFontStyle,e.labelFontFamily),t.fillStyle=e.labelFontColor,t.textAlign=\"center\",e.labelContent&&f.isArray(e.labelContent))for(var n=e.labelY+e.labelYPadding,i=0;i<e.labelContent.length;i++)t.textBaseline=\"top\",t.fillText(e.labelContent[i],e.labelX+e.labelWidth/2,n),n+=e.labelFontSize+e.labelYPadding;else t.textBaseline=\"middle\",t.fillText(e.labelContent,e.labelX+e.labelWidth/2,e.labelY+e.labelHeight",
			"/2);t.restore()}}})}},{\"../helpers.js\":5}]},{},[6]);" };

	/**
	 * Creates the injectable resource with <code>chartjs-plugin-annotation.min.js</code> content.
	 */
	AnnotationPluginResource() {
		super(ResourceName.ANNOTATION_PLUGIN, CONTENT);
	}

}
