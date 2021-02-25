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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-zoom.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-zoom.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * @license",
		" * chartjs-plugin-zoom",
		" * http://chartjs.org/",
		" * Version: 0.7.7",
		" *",
		" * Copyright 2021 Chart.js Contributors",
		" * Released under the MIT license",
		" * https://github.com/chartjs/chartjs-plugin-zoom/blob/master/LICENSE.md",
		" */",
		"!function(e,o){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=o(require(\"chart.js\"),require(\"chart.js/helpers\"),require(\"hammerjs\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\",\"hammerjs\"],o):(e=\"undefined\"!=typeof globalThis?globalThis:e||self).ChartZoom=o(e.Chart,e.Chart.helpers,e.Hammer)}(this,(function(e,o,n){\"use strict\";function t(e){return e&&\"object\"==typeof e&&\"default\"in e?e:{default:e}}var a=t(n),i={},r=i.zoomFunctions=i.zoomFunctions||{},l=i.panFunctions=i.panFunctions||{};function m(e,n){var t={};void 0!==e.options.pan&&(t.pan=e.options.pan),void 0!==e.options.zoom&&(t.zoom=e.options.zoom);var a=e.$zoom;n=a._options=o.merge({},[n,t]);var i=a._node,r=n.zoom&&n.zoom.enabled,l=n.zoom.drag;r&&!l?i.addEventListener(\"wheel\",a._wheelHandler):i.removeEventListener(\"wheel\",a._wheelHandler),r&&l?(i.addEventListener(\"mousedown\",a._mouseDownHandler),i.ownerDocument.addEventListener(\"mouseup\",a._mouse",
		"UpHandler)):(i.removeEventListener(\"mousedown\",a._mouseDownHandler),i.removeEventListener(\"mousemove\",a._mouseMoveHandler),i.ownerDocument.removeEventListener(\"mouseup\",a._mouseUpHandler))}function s(e){var n=e.$zoom._originalOptions;o.each(e.scales,(function(e){n[e.id]||(n[e.id]=o.clone(e.options))})),o.each(n,(function(o,t){e.scales[t]||delete n[t]}))}function u(e,o,n){return void 0===e||(\"string\"==typeof e?-1!==e.indexOf(o):\"function\"==typeof e&&-1!==e({chart:n}).indexOf(o))}function c(e,n){if(e.scaleAxes&&e.rangeMax&&!o.isNullOrUndef(e.rangeMax[e.scaleAxes])){var t=e.rangeMax[e.scaleAxes];n>t&&(n=t)}return n}function d(e,n){if(e.scaleAxes&&e.rangeMin&&!o.isNullOrUndef(e.rangeMin[e.scaleAxes])){var t=e.rangeMin[e.scaleAxes];n<t&&(n=t)}return n}function p(e,o,n,t){var a=e.max-e.min,i=a*(o-1),r=e.isHorizontal()?n.x:n.y,l=(e.getValueForPixel(r)-e.min)/a,m=i*l,s=i*(1-l);e.options.min=d(t,e.min+m),e.options.max=c(t,e.max-s)}function f(e,o,n,t){var a=r[e.type];a&&a(e,o,n,t)}funct",
		"ion v(e,n,t,a,i,r){var l=e.chartArea;a||(a={x:(l.left+l.right)/2,y:(l.top+l.bottom)/2});var m=e.$zoom._options.zoom;if(m.enabled){s(e);var c,d=\"function\"==typeof m.mode?m.mode({chart:e}):m.mode;c=\"xy\"===d&&void 0!==i?i:\"xy\",o.each(e.scales,(function(o){o.isHorizontal()&&u(d,\"x\",e)&&u(c,\"x\",e)?(m.scaleAxes=\"x\",f(o,n,a,m)):!o.isHorizontal()&&u(d,\"y\",e)&&u(c,\"y\",e)&&(m.scaleAxes=\"y\",f(o,t,a,m))})),r?(e.options.transitions.zoom||(e.options.transitions.zoom={animation:{duration:r,easing:\"easeOutQuad\"}}),e.update(\"zoom\")):e.update(\"none\"),\"function\"==typeof m.onZoom&&m.onZoom({chart:e})}}function h(e,n,t){var a,i=e.options,r=e.min,l=e.max,m=e.getValueForPixel(e.getPixelForValue(r)-n),s=e.getValueForPixel(e.getPixelForValue(l)-n),u=m,c=s;t.scaleAxes&&t.rangeMin&&!o.isNullOrUndef(t.rangeMin[t.scaleAxes])&&(u=t.rangeMin[t.scaleAxes]),t.scaleAxes&&t.rangeMax&&!o.isNullOrUndef(t.rangeMax[t.scaleAxes])&&(c=t.rangeMax[t.scaleAxes]),m>=u&&s<=c?(i.min=m,i.max=s):m<u?(a=r-u,i.",
		"min=u,i.max=l-a):s>c&&(a=c-l,i.max=c,i.min=r+a)}function z(e,o,n){var t=l[e.type];t&&t(e,o,n)}i.zoomFunctions.category=function(e,o,n,t){var a=e.chart.data.labels,r=e.min,l=a.length-1,m=e.max,s=t.sensitivity,u=e.isHorizontal()?e.left+e.width/2:e.top+e.height/2,p=e.isHorizontal()?n.x:n.y;i.zoomCumulativeDelta=o>1?i.zoomCumulativeDelta+1:i.zoomCumulativeDelta-1,Math.abs(i.zoomCumulativeDelta)>s&&(i.zoomCumulativeDelta<0?(p>=u?r<=0?m=Math.min(l,m+1):r=Math.max(0,r-1):p<u&&(m>=l?r=Math.max(0,r-1):m=Math.min(l,m+1)),i.zoomCumulativeDelta=0):i.zoomCumulativeDelta>0&&(p>=u?r=r<m?r=Math.min(m,r+1):r:p<u&&(m=m>r?m=Math.max(r,m-1):m),i.zoomCumulativeDelta=0),e.options.min=d(t,a[r]),e.options.max=c(t,a[m]))},i.zoomFunctions.time=p,i.zoomFunctions.linear=p,i.zoomFunctions.logarithmic=p,i.panFunctions.category=function(e,o,n){var t,a=e.chart.data.labels,r=a.length-1,l=Math.max(e.ticks.length,1),m=n.speed,s=e.min,u=Math.round(e.width/(l*m));i.panCumulativeDelta+=o,s=i.panCumulativeDelta>u?Math.max(0,",
		"s-1):i.panCumulativeDelta<-u?Math.min(r-l+1,s+1):s,i.panCumulativeDelta=s!==e.min?0:i.panCumulativeDelta,t=Math.min(r,s+l-1),e.options.min=d(n,a[s]),e.options.max=c(n,a[t])},i.panFunctions.time=h,i.panFunctions.linear=h,i.panFunctions.logarithmic=h,i.panCumulativeDelta=0,i.zoomCumulativeDelta=0;var g={id:\"zoom\",defaults:{pan:{enabled:!1,mode:\"xy\",speed:20,threshold:10},zoom:{enabled:!1,mode:\"xy\",sensitivity:3,speed:.1}},start:function(e,n,t){e.$zoom={_originalOptions:{}};var r=e.$zoom._node=e.ctx.canvas;m(e,t);var l=e.$zoom._options,c=l.pan&&l.pan.threshold;e.$zoom._mouseDownHandler=function(o){r.addEventListener(\"mousemove\",e.$zoom._mouseMoveHandler),e.$zoom._dragZoomStart=o},e.$zoom._mouseMoveHandler=function(o){e.$zoom._dragZoomStart&&(e.$zoom._dragZoomEnd=o,e.update(\"none\"))},e.$zoom._mouseUpHandler=function(o){if(e.$zoom._dragZoomStart){r.removeEventListener(\"mousemove\",e.$zoom._mouseMoveHandler);var n=e.$zoom._dragZoomStart,t=n.target.getBoundingClientRect().left,a=Mat",
		"h.min(n.clientX,o.clientX)-t,i=Math.max(n.clientX,o.clientX)-t,m=n.target.getBoundingClientRect().top,s=Math.min(n.clientY,o.clientY)-m,c=i-a,d=Math.max(n.clientY,o.clientY)-m-s;e.$zoom._dragZoomStart=null,e.$zoom._dragZoomEnd=null;var p=l.zoom&&l.zoom.threshold||0;if(!(c<=p&&d<=p)){var f=e.chartArea,h=e.$zoom._options.zoom,z=f.right-f.left,g=u(h.mode,\"x\",e)&&c?1+(z-c)/z:1,x=f.bottom-f.top,_=u(h.mode,\"y\",e);v(e,g,_&&d?1+(x-d)/x:1,{x:(a-f.left)/(1-c/z)+f.left,y:(s-f.top)/(1-d/x)+f.top},void 0,h.drag.animationDuration),\"function\"==typeof h.onZoomComplete&&h.onZoomComplete({chart:e})}}};var d=null;if(e.$zoom._wheelHandler=function(o){if(o.cancelable&&o.preventDefault(),void 0!==o.deltaY){var n=o.target.getBoundingClientRect(),t={x:o.clientX-n.left,y:o.clientY-n.top},a=e.$zoom._options.zoom,i=a.speed;o.deltaY>=0&&(i=-i),v(e,1+i,1+i,t),clearTimeout(d),d=setTimeout((function(){\"function\"==typeof a.onZoomComplete&&a.onZoomComplete({chart:e})}),250)}},a.default){var p,f=new a.default.Ma",
		"nager(r);f.add(new a.default.Pinch),f.add(new a.default.Pan({threshold:c}));var h=function(o){var n=1/p*o.scale,t=o.target.getBoundingClientRect(),a={x:o.center.x-t.left,y:o.center.y-t.top},i=Math.abs(o.pointers[0].clientX-o.pointers[1].clientX),r=Math.abs(o.pointers[0].clientY-o.pointers[1].clientY),l=i/r;v(e,n,n,a,l>.3&&l<1.7?\"xy\":i>r?\"x\":\"y\");var m=e.$zoom._options.zoom;\"function\"==typeof m.onZoom&&m.onZoom({chart:e}),p=o.scale};f.on(\"pinchstart\",(function(){p=1})),f.on(\"pinch\",h),f.on(\"pinchend\",(function(o){h(o),p=null,i.zoomCumulativeDelta=0;var n=e.$zoom._options.zoom;\"function\"==typeof n.onZoomComplete&&n.onZoomComplete({chart:e})}));var g=null,x=null,_=!1,y=function(n){if(null!==g&&null!==x){_=!0;var t=n.deltaX-g,a=n.deltaY-x;g=n.deltaX,x=n.deltaY,function(e,n,t){s(e);var a=e.$zoom._options.pan;if(a.enabled){var i=\"function\"==typeof a.mode?a.mode({chart:e}):a.mode;o.each(e.scales,(function(o){o.isHorizontal()&&u(i,\"x\",e)&&0!==n?(a.scaleAxes=\"x\",z(o,n,a)):!",
		"o.isHorizontal()&&u(i,\"y\",e)&&0!==t&&(a.scaleAxes=\"y\",z(o,t,a))})),e.update(\"none\"),\"function\"==typeof a.onPan&&a.onPan({chart:e})}}(e,t,a)}};f.on(\"panstart\",(function(e){g=0,x=0,y(e)})),f.on(\"panmove\",y),f.on(\"panend\",(function(){g=null,x=null,i.panCumulativeDelta=0,setTimeout((function(){_=!1}),500);var o=e.$zoom._options.pan;\"function\"==typeof o.onPanComplete&&o.onPanComplete({chart:e})})),e.$zoom._ghostClickHandler=function(e){_&&e.cancelable&&(e.stopImmediatePropagation(),e.preventDefault())},r.addEventListener(\"click\",e.$zoom._ghostClickHandler),e._mc=f}e.resetZoom=function(){s(e);var n=e.$zoom._originalOptions;o.each(e.scales,(function(e){var o=e.options;n[e.id]?(o.min=n[e.id].min,o.max=n[e.id].max):(delete o.min,delete o.max)})),e.update()}},beforeUpdate:function(e,o,n){m(e,n)},beforeDatasetsDraw:function(e){var o=e.ctx;if(e.$zoom._dragZoomEnd){var n=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(a.isHorizontal())return a}}",
		"(e),t=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(!a.isHorizontal())return a}}(e),a=e.$zoom._dragZoomStart,i=e.$zoom._dragZoomEnd,r=n.left,l=n.right,m=t.top,s=t.bottom;if(u(e.$zoom._options.zoom.mode,\"x\",e)){var c=a.target.getBoundingClientRect().left;r=Math.min(a.clientX,i.clientX)-c,l=Math.max(a.clientX,i.clientX)-c}if(u(e.$zoom._options.zoom.mode,\"y\",e)){var d=a.target.getBoundingClientRect().top;m=Math.min(a.clientY,i.clientY)-d,s=Math.max(a.clientY,i.clientY)-d}var p=l-r,f=s-m,v=e.$zoom._options.zoom.drag;o.save(),o.beginPath(),o.fillStyle=v.backgroundColor||\"rgba(225,225,225,0.3)\",o.fillRect(r,m,p,f),v.borderWidth>0&&(o.lineWidth=v.borderWidth,o.strokeStyle=v.borderColor||\"rgba(225,225,225)\",o.strokeRect(r,m,p,f)),o.restore()}},stop:function(e){if(e.$zoom){var o=e.$zoom,n=o._node;n.removeEventListener(\"mousedown\",o._mouseDownHandler),n.removeEventListener(\"mousemove\",o._mouseMoveHandler),n.ownerDocument.removeEventListener(\"mou",
		"seup\",o._mouseUpHandler),n.removeEventListener(\"wheel\",o._wheelHandler),n.removeEventListener(\"click\",o._ghostClickHandler),delete e.$zoom;var t=e._mc;t&&(t.remove(\"pinchstart\"),t.remove(\"pinch\"),t.remove(\"pinchend\"),t.remove(\"panstart\"),t.remove(\"pan\"),t.remove(\"panend\"),t.destroy())}}};return e.Chart.register(g),g}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-zoom.min.js</code> content.
	 */
	ZoomPluginResource() {
		super(ResourceName.ZOOM_PLUGIN, CONTENT);
	}

}
