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
		" * Version: 1.0.0-beta.1",
		" *",
		" * Copyright 2021 Chart.js Contributors",
		" * Released under the MIT license",
		" * https://github.com/chartjs/chartjs-plugin-zoom/blob/master/LICENSE.md",
		" */",
		"!function(e,o){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=o(require(\"chart.js\"),require(\"chart.js/helpers\"),require(\"hammerjs\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\",\"hammerjs\"],o):(e=\"undefined\"!=typeof globalThis?globalThis:e||self).ChartZoom=o(e.Chart,e.Chart.helpers,e.Hammer)}(this,(function(e,o,n){\"use strict\";function t(e){return e&&\"object\"==typeof e&&\"default\"in e?e:{default:e}}var a=t(n),i={},r=i.zoomFunctions=i.zoomFunctions||{},l=i.panFunctions=i.panFunctions||{};function m(e,o){var n=e.$zoom;n._options=o;var t=n._node,a=o.zoom&&o.zoom.enabled,i=o.zoom.drag;a&&!i?t.addEventListener(\"wheel\",n._wheelHandler):t.removeEventListener(\"wheel\",n._wheelHandler),a&&i?(t.addEventListener(\"mousedown\",n._mouseDownHandler),t.ownerDocument.addEventListener(\"mouseup\",n._mouseUpHandler)):(t.removeEventListener(\"mousedown\",n._mouseDownHandler),t.removeEventListener(\"mousemove\",n._mouseMoveHand",
		"ler),t.ownerDocument.removeEventListener(\"mouseup\",n._mouseUpHandler))}function c(e){var n=e.$zoom._originalOptions;o.each(e.scales,(function(e){n[e.id]||(n[e.id]=o.clone(e.options))})),o.each(n,(function(o,t){e.scales[t]||delete n[t]}))}function s(e,o,n){return void 0===e||(\"string\"==typeof e?-1!==e.indexOf(o):\"function\"==typeof e&&-1!==e({chart:n}).indexOf(o))}function u(e,n,t,a){if(e.enabled&&e.overScaleMode){var i=function(e,o,n){for(var t=n.scales,a=Object.keys(t),i=0;i<a.length;i++){var r=t[a[i]];if(o>=r.top&&o<=r.bottom&&e>=r.left&&e<=r.right)return r}return null}(n,t,a),r=\"function\"==typeof e.overScaleMode?e.overScaleMode({chart:a},i):e.overScaleMode;if(i&&s(r,i.axis,a))return[i];var l=[];return o.each(a.scales,(function(e){s(r,e.axis,a)||l.push(e)})),l}return null}function d(e,n){if(e.scaleAxes&&e.rangeMax&&!o.isNullOrUndef(e.rangeMax[e.scaleAxes])){const o=e.rangeMax[e.scaleAxes];n>o&&(n=o)}return n}function f(e,n){if(e.scaleAxes&&e.rangeMin&&!o.isNullOrUndef(e.rangeMi",
		"n[e.scaleAxes])){const o=e.rangeMin[e.scaleAxes];n<o&&(n=o)}return n}function p(e,o,n){const t=e.max-e.min,a=t*(o-1),i=e.isHorizontal()?n.x:n.y,r=(e.getValueForPixel(i)-e.min)/t;return{min:a*r,max:a*(1-r)}}function v(e,o,n,t){const a=p(e,o,n);e.options.min=f(t,e.min+a.min),e.options.max=d(t,e.max-a.max)}const h=e=>0===e||isNaN(e)?0:e<0?Math.min(Math.round(e),-1):Math.max(Math.round(e),1);function g(e,o,n,t){var a=r[e.type];a&&a(e,o,n,t)}function x(e,n,t,a,i,r){var l=e.chartArea;a||(a={x:(l.left+l.right)/2,y:(l.top+l.bottom)/2});var m=e.$zoom._options.zoom;if(m.enabled){c(e);var d,f=\"function\"==typeof m.mode?m.mode({chart:e}):m.mode;d=\"xy\"===f&&void 0!==i?i:\"xy\";var p=u(m,a.x,a.y,e);o.each(p||e.scales,(function(o){o.isHorizontal()&&s(f,\"x\",e)&&s(d,\"x\",e)?(m.scaleAxes=\"x\",g(o,n,a,m)):!o.isHorizontal()&&s(f,\"y\",e)&&s(d,\"y\",e)&&(m.scaleAxes=\"y\",g(o,t,a,m))})),r?(e.options.animation.zoom||(e.options.animation.zoom={duration:r,easing:\"easeOutQuad\"}),e.update(\"zoom\")):e.u",
		"pdate(\"none\"),\"function\"==typeof m.onZoom&&m.onZoom({chart:e})}}function z(e,o,n){const t=e.options,a=e.min,i=e.max,r=e.getValueForPixel(e.getPixelForValue(a)-o),l=e.getValueForPixel(e.getPixelForValue(i)-o),m=f(n,r),c=d(n,l);let s;r>=m&&l<=c?(t.min=r,t.max=l):r<m?(s=a-m,t.min=m,t.max=i-s):l>c&&(s=c-i,t.max=c,t.min=a+s)}function y(e,o,n){const t=l[e.type];t&&t(e,o,n)}i.zoomFunctions.category=function(e,o,n,t){const a=e.getLabels(),i=a.length-1;e.min===e.max&&o<1&&(e.min>0?e.min--:e.max<i&&e.max++);const r=p(e,o,n);e.options.min=a[Math.max(0,f(t,e.min+h(r.min)))],e.options.max=a[Math.min(i,d(t,e.max-h(r.max)))]},i.zoomFunctions.time=v,i.zoomFunctions.linear=v,i.zoomFunctions.logarithmic=v,i.panFunctions.category=function(e,o,n){const t=e.getLabels(),a=t.length-1,r=Math.max(e.ticks.length,1),l=n.speed,m=Math.round(e.width/(r*l));let c,s=e.min;i.panCumulativeDelta+=o,s=i.panCumulativeDelta>m?Math.max(0,s-1):i.panCumulativeDelta<-m?Math.min(a-r+1,s+1):s,i.panCumulativeDelta=s!==e.min?0:",
		"i.panCumulativeDelta,c=Math.min(a,s+r-1),e.options.min=f(n,t[s]),e.options.max=d(n,t[c])},i.panFunctions.time=z,i.panFunctions.linear=z,i.panFunctions.logarithmic=z,i.panCumulativeDelta=0;var _={id:\"zoom\",defaults:{pan:{enabled:!1,mode:\"xy\",speed:20,threshold:10,modifierKey:null},zoom:{enabled:!1,mode:\"xy\",sensitivity:3,speed:.1,wheelModifierKey:null}},start:function(e,n,t){e.$zoom={_originalOptions:{}};var i=e.$zoom._node=e.ctx.canvas;m(e,t);var r=e.$zoom._options,l=r.pan&&r.pan.threshold;e.$zoom._mouseDownHandler=function(o){i.addEventListener(\"mousemove\",e.$zoom._mouseMoveHandler),e.$zoom._dragZoomStart=o},e.$zoom._mouseMoveHandler=function(o){e.$zoom._dragZoomStart&&(e.$zoom._dragZoomEnd=o,e.update(\"none\"))},e.$zoom._mouseUpHandler=function(o){if(e.$zoom._dragZoomStart){i.removeEventListener(\"mousemove\",e.$zoom._mouseMoveHandler);var n=e.$zoom._dragZoomStart,t=n.target.getBoundingClientRect().left,a=Math.min(n.clientX,o.clientX)-t,l=Math.max(n.clientX,o.clientX)-t,m=n.ta",
		"rget.getBoundingClientRect().top,c=Math.min(n.clientY,o.clientY)-m,u=l-a,d=Math.max(n.clientY,o.clientY)-m-c;e.$zoom._dragZoomStart=null,e.$zoom._dragZoomEnd=null;var f=r.zoom&&r.zoom.threshold||0;if(!(u<=f&&d<=f)){var p=e.chartArea,v=e.$zoom._options.zoom,h=p.right-p.left,g=s(v.mode,\"x\",e)&&u?1+(h-u)/h:1,z=p.bottom-p.top,y=s(v.mode,\"y\",e);x(e,g,y&&d?1+(z-d)/z:1,{x:(a-p.left)/(1-u/h)+p.left,y:(c-p.top)/(1-d/z)+p.top},void 0,v.drag.animationDuration),\"function\"==typeof v.onZoomComplete&&v.onZoomComplete({chart:e})}}};var d=null;if(e.$zoom._wheelHandler=function(o){var n=e.$zoom._options.zoom;if(n&&n.wheelModifierKey&&!o[n.wheelModifierKey+\"Key\"])\"function\"==typeof n.onZoomRejected&&n.onZoomRejected({chart:e,event:o});else if(o.cancelable&&o.preventDefault(),void 0!==o.deltaY){var t=o.target.getBoundingClientRect(),a={x:o.clientX-t.left,y:o.clientY-t.top},i=n.speed;o.deltaY>=0&&(i=-i),x(e,1+i,1+i,a),clearTimeout(d),d=setTimeout((function(){\"function\"==typeof n.onZoomComplete&&",
		"n.onZoomComplete({chart:e})}),250)}},a.default){var f,p=e.$zoom._options.zoom,v=e.$zoom._options.pan,h=new a.default.Manager(i);p&&p.enabled&&h.add(new a.default.Pinch),v&&v.enabled&&h.add(new a.default.Pan({threshold:l,enable:function(o,n){const t=e.$zoom._options.pan;return!(!t||!t.enabled)&&(!n||!n.srcEvent||!(t.modifierKey&&\"mouse\"===n.pointerType&&!n.srcEvent[t.modifierKey+\"Key\"]&&(\"function\"==typeof t.onPanRejected&&t.onPanRejected({chart:e,event:n}),1)))}}));var g=function(o){var n=1/f*o.scale,t=o.target.getBoundingClientRect(),a={x:o.center.x-t.left,y:o.center.y-t.top},i=Math.abs(o.pointers[0].clientX-o.pointers[1].clientX),r=Math.abs(o.pointers[0].clientY-o.pointers[1].clientY),l=i/r;x(e,n,n,a,l>.3&&l<1.7?\"xy\":i>r?\"x\":\"y\"),\"function\"==typeof p.onZoom&&p.onZoom({chart:e}),f=o.scale};h.on(\"pinchstart\",(function(){f=1})),h.on(\"pinch\",g),h.on(\"pinchend\",(function(o){g(o),f=null,\"function\"==typeof p.onZoomComplete&&p.onZoomComplete({chart:e})}));var z=null,_=nu",
		"ll,M=!1,b=null,$=function(n){if(null!==z&&null!==_){M=!0;var t=n.deltaX-z,a=n.deltaY-_;z=n.deltaX,_=n.deltaY,function(e,n,t,a){c(e);var i=e.$zoom._options.pan;if(i.enabled){var r=\"function\"==typeof i.mode?i.mode({chart:e}):i.mode;o.each(a||e.scales,(function(o){o.isHorizontal()&&s(r,\"x\",e)&&0!==n?(i.scaleAxes=\"x\",y(o,n,i)):!o.isHorizontal()&&s(r,\"y\",e)&&0!==t&&(i.scaleAxes=\"y\",y(o,t,i))})),e.update(\"none\"),\"function\"==typeof i.onPan&&i.onPan({chart:e})}}(e,t,a,b)}};h.on(\"panstart\",(function(o){if(v.enabled){var n=o.target.getBoundingClientRect(),t=o.center.x-n.left,a=o.center.y-n.top;b=u(v,t,a,e)}z=0,_=0,$(o)})),h.on(\"panmove\",$),h.on(\"panend\",(function(){b=null,z=null,_=null,setTimeout((function(){M=!1}),500),\"function\"==typeof v.onPanComplete&&v.onPanComplete({chart:e})})),e.$zoom._ghostClickHandler=function(e){M&&e.cancelable&&(e.stopImmediatePropagation(),e.preventDefault())},i.addEventListener(\"click\",e.$zoom._ghostClickHandler),e._mc=h}e.resetZoom=function(",
		"){c(e);var n=e.$zoom._originalOptions;o.each(e.scales,(function(e){var o=e.options;n[e.id]?(o.min=n[e.id].min,o.max=n[e.id].max):(delete o.min,delete o.max)})),e.update()}},beforeUpdate:function(e,o,n){m(e,n)},beforeDatasetsDraw:function(e){var o=e.ctx;if(e.$zoom._dragZoomEnd){var n=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(a.isHorizontal())return a}}(e),t=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(!a.isHorizontal())return a}}(e),a=e.$zoom._dragZoomStart,i=e.$zoom._dragZoomEnd,r=n.left,l=n.right,m=t.top,c=t.bottom;if(s(e.$zoom._options.zoom.mode,\"x\",e)){var u=a.target.getBoundingClientRect().left;r=Math.min(a.clientX,i.clientX)-u,l=Math.max(a.clientX,i.clientX)-u}if(s(e.$zoom._options.zoom.mode,\"y\",e)){var d=a.target.getBoundingClientRect().top;m=Math.min(a.clientY,i.clientY)-d,c=Math.max(a.clientY,i.clientY)-d}var f=l-r,p=c-m,v=e.$zoom._options.zoom.drag;o.save(),o.beginPath(),o.fillStyle=v.backgro",
		"undColor||\"rgba(225,225,225,0.3)\",o.fillRect(r,m,f,p),v.borderWidth>0&&(o.lineWidth=v.borderWidth,o.strokeStyle=v.borderColor||\"rgba(225,225,225)\",o.strokeRect(r,m,f,p)),o.restore()}},stop:function(e){if(e.$zoom){var o=e.$zoom,n=o._node;n.removeEventListener(\"mousedown\",o._mouseDownHandler),n.removeEventListener(\"mousemove\",o._mouseMoveHandler),n.ownerDocument.removeEventListener(\"mouseup\",o._mouseUpHandler),n.removeEventListener(\"wheel\",o._wheelHandler),n.removeEventListener(\"click\",o._ghostClickHandler),delete e.$zoom;var t=e._mc;t&&(t.remove(\"pinchstart\"),t.remove(\"pinch\"),t.remove(\"pinchend\"),t.remove(\"panstart\"),t.remove(\"pan\"),t.remove(\"panend\"),t.destroy())}}};return e.Chart.register(_),_}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-zoom.min.js</code> content.
	 */
	ZoomPluginResource() {
		super(ResourceName.ZOOM_PLUGIN, CONTENT);
	}

}
