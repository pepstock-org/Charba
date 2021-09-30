/**
  Copyright 2021 Andrea "Stock" Stocchero

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
// ------------------------------
// creates toasting GLOBAL object
// ------------------------------
// sets default create function in case
// toasting creation is attempted before dom has finished loading
CharbaToast = {
  init: function() {
    // -------------------------------
    // creates the element for toasting
    // MAIN CONTAINER
    // -------------------------------
    const container = document.createElement('div');
    container.id = 'ct-container';
    document.body.appendChild(container);
    /**
     * Replaces "create" method when DOM has finished loading.
     * Creates and show the toast.
     *
     * @param {number} pId unique identifier of toast
     * @param {string} pTitle title to show
     * @param {Array} pLabel label to show
     * @param {Object} pOptions options to change look and feel of toast
     * @param {Object} pDateTime date time of toast item when queued and re-shown
     * @return {Object} object which represent the toast
     */
    // 
    CharbaToast.create = function (pId, pTitle, pLabel, pOptions, pDateTime) {
      // creates result
      const result = {
          id: pId,
          title: pTitle,
          label: pLabel,
          dateTime: Object.assign({}, pDateTime || {})
      };
      // checks consistenncy of toast
      const hasTitle = typeof pTitle === 'string' && pTitle.trim().length > 0;
      const hasLabel = Array.isArray(pLabel) && pLabel.length > 0;
      // If not consistent
      // returns null
      if (!hasTitle && !hasLabel) {
        return Object.assign(result, {
          status: 'invalid',
          dateTime: {
            invalid: Date.now()
          }
        });
      }
      // manages options 
      const options = Object.assign({}, CharbaToast.defaults, CharbaToast.overrides, pOptions || {});
      // manages title options 
      options.title = Object.assign({}, CharbaToast.defaults.title, CharbaToast.overrides.title, options.title);
      // manages label options 
      options.label = Object.assign({}, CharbaToast.defaults.label, CharbaToast.overrides.label, options.label);
      // manages action options 
      options.action = Object.assign({}, CharbaToast.defaults.action, CharbaToast.overrides.action, options.action);
      // -------------------------------
      // creates the element for toasting
      // -------------------------------
      const toasting = document.createElement('div');
      toasting.id = 'toast-' + pId;
      toasting.className = 'ct-toast';
      const wrapper = document.createElement('div');
      // -------------------------------
      // BORDER RADIUS
      // -------------------------------
      wrapper.style.borderRadius = options.borderRadius + 'px';
      // -------------------------------
      // BOX SHADOW
      // -------------------------------
      if (options.hideShadow) {
        wrapper.style.boxShadow = 'none';
      }
      // -------------------------------
      // TITLE
      // -------------------------------
      if (hasTitle) {
        const title = document.createElement('h4');
        title.className = 'ct-title';
        title.innerHTML = pTitle;
        // title color
        if (typeof options.title.color === 'string') {
          title.style.color = options.title.color;
        }
        if (typeof options.title.font === 'object') {
          // normalizes font 
          const font = Object.assign({}, CharbaToast.defaults.titleFont, options.title.font);
          const normFont = CharbaToast.helper.toFont.apply(this, [font]);
          title.style.font = normFont.string;
        }
        wrapper.appendChild(title);
      }
      // -------------------------------
      // LABEL
      // -------------------------------
      if (hasLabel) {
        const text = document.createElement('p');
        text.className = 'ct-label';
        text.innerHTML = pLabel.join('<br/>');
        // title color
        if (typeof options.label.color === 'string') {
          text.style.color = options.label.color;
        }
        if (typeof options.label.font === 'object') {
          // normalizes font 
          const font = Object.assign({}, CharbaToast.defaults.labelFont, options.label.font);
          const normFont = CharbaToast.helper.toFont.apply(this, [font]);
          text.style.font = normFont.string;
        }
        wrapper.appendChild(text);
      }
      // -------------------------------
      // ICON
      // -------------------------------
      if (options.icon && options.icon.src) {
        wrapper.classList += ' img';
        const icon = document.createElement('img');
        icon.src = options.icon.src;
        icon.className = 'ct-icon';
        wrapper.appendChild(icon);
      }
      // -------------------------------
      // ACTIONS
      // -------------------------------
      if (Array.isArray(options.actions) && options.actions.length > 0) {
        const actionsContainer = document.createElement('div');
        actionsContainer.className = 'ct-actions';
        // action align
        if (typeof options.align === 'string') {
          actionsContainer.style.textAlign = options.align;
        }
        // scans actions
        for (const item of options.actions) {
          if (typeof item.id === 'string' && typeof item.content === 'string' && typeof item.onClick === 'function') {
            // manages options 
            const actionOption = Object.assign({}, options.action, item);
            const itemElement = document.createElement('div');
            itemElement.className = 'ct-action';
            itemElement.innerHTML = actionOption.content;
            // add listener
            itemElement.addEventListener('click', (event) => {
              // stores date time of action 
              result.dateTime[actionOption.id] = Date.now();
              // invokes custom callback passing toast id
              const close = actionOption.onClick.apply(this, [pId, event]);
              // checks if the user callback wants to close the toast
              if (close) {
                // stores in the result object
                // the action id
                result.actionId = actionOption.id;
                toasting.hide.call();
              }
            });
            // action color
            if (typeof actionOption.color === 'string') {
               itemElement.style.color = actionOption.color;
            }
            // action background color
            if (typeof actionOption.backgroundColor === 'string') {
               itemElement.style.background = actionOption.backgroundColor;
            }
            // action border color
            if (typeof actionOption.borderColor === 'string') {
               itemElement.style.borderColor = actionOption.borderColor;
            }
            // action border width
            itemElement.style.borderWidth = Math.max(actionOption.borderWidth, 0) + 'px';
            // action border style
            itemElement.style.borderStyle = actionOption.borderStyle.join(' ');
            // action border radius
            itemElement.style.borderRadius = Math.max(actionOption.borderRadius, 0) + 'px';
            // action color
            if (typeof actionOption.color === 'string') {
               itemElement.style.color = actionOption.color;
            }
            // action font
            if (typeof actionOption.font === 'object') {
              // normalizes font 
              const font = Object.assign({}, CharbaToast.defaults.labelFont, actionOption.font);
              const normFont = CharbaToast.helper.toFont.apply(this, [font]);
              itemElement.style.font = normFont.string;
            }
            actionsContainer.appendChild(itemElement);
          }
        }
        wrapper.appendChild(actionsContainer);
      }
      // -------------------------------
      // PROGRESS BAR
      // -------------------------------
      if (!options.hideProgressBar) {
        const cssAnimation = document.createElement('style');
        cssAnimation.id = 'style-' + toasting.id;
        cssAnimation.type = 'text/css';
        const rules = document.createTextNode(`
            @keyframes animate-${pId} {
              0% {
                width: 100%
              }
              100% {
                width: 0%;
              }
            }
        `);
        cssAnimation.appendChild(rules);
        document.getElementsByTagName('head')[0].appendChild(cssAnimation);
        const progressBar = document.createElement('div');
        progressBar.classList = 'progress-bar';
        progressBar.style.animationName = 'animate-' + pId;
        progressBar.style.animationDuration = options.timeout / 1000 + 's';
        progressBar.style.animationTimingFunction = 'linear';
        progressBar.style.animationFillMode = 'forwards';
        if (options.progressBarType) {
          progressBar.classList += ' ' + options.progressBarType;    
        }
        if (options.progressBarHeight) {
          progressBar.style.height = options.progressBarHeight + 'px';
        }
        wrapper.appendChild(progressBar);
      }
      // -------------------------------
      // Custom CLICK EVENT callback
      // -------------------------------
      if (typeof options.onClick === 'function') {
        toasting.addEventListener('click', (event) => {
          // checks if the click is coming from an action
          if (event.target.className === 'ct-action') {
            return;
          }
          // invokes the custom handler
          options.onClick.apply(this, [pId, event])
        });
      }
      // -------------------------------
      // HIDING / AUTO HIDE
      // -------------------------------
      toasting.hide = function () {
        if (toasting.timeoutId) {
          clearTimeout(toasting.timeoutId);
        }
        toasting.className += ' ct-fadeOut';
        toasting.addEventListener('animationend', removeToast, false);
        return null;
      };
      if (options.autoHide) {
        toasting.timeoutId = setTimeout(toasting.hide, options.timeout + 200);
      }
      // -------------------------------
      // TYPE
      // -------------------------------
      if (options.type) {
        wrapper.className += ' ct-' + options.type;
      } else {
        wrapper.className += ' ct-default';
      }
      // -------------------------------
      // Internal CLICK EVENT callback
      // -------------------------------
      toasting.addEventListener('click', (event) => {
        // checks if the click is coming from an action
        if (event.target.className === 'ct-action') {
          return;
        }
        // checks if modifier key asbeen set and pressed
      	if (options.modifierKey && !event[options.modifierKey + 'Key']) {
      	  // if here, modifier key is set but not pressed 
          return;
        }
        // if here, modifier key not set or pressed
        toasting.hide.call();
  	  });
      // -------------------------------
      // REMOVE toast
      // -------------------------------
      function removeToast() {
        document.getElementById('ct-container').removeChild(toasting);
        const style = document.querySelector('#style-' + toasting.id);
        if (style) {
          style.remove();
        }
        result.status = 'closed';
        result.dateTime.closed = Date.now();
        result.element = toasting;
        CharbaToast.currentOpenItems--;
        // checks and calls callback
        if (typeof options.onClose === 'function') {
          options.onClose.apply(this, [result]);
        }
        // checks fo internal close handler
        if (typeof CharbaToast.onClose === 'function') {
          CharbaToast.onClose.apply(this, [result]);
        }
      }    
      // -------------------------------
      // ADD to DOCUMENT
      // -------------------------------
      toasting.appendChild(wrapper);
      document.getElementById('ct-container').appendChild(toasting);
      // sets new filed to result
      result.status = 'opened';
      result.dateTime.opened = Date.now();
      CharbaToast.currentOpenItems++;
      // checks and calls callback
      if (typeof options.onOpen === 'function') {
        options.onOpen.apply(this, [result]);
      }
      return result;
    }
  },
  create: function () {
    console.error(['DOM has not finished loading.', '\tInvoke create method when DOM\s readyState is complete'].join('\n'));
  },
  helper: {
    lineHeightRegExp: new RegExp(/^(normal|(\d+(?:\.\d+)?)(px|em|%)?)$/),
    fontStyleRegExp: new RegExp(/^(normal|italic|initial|inherit|unset|(oblique( -?[0-9]?[0-9]deg)?))$/), 
    /**
     * ToLineHeight
     */
    toLineHeight: function(value, size) {
      const matches = ('' + value).match(CharbaToast.helper.lineHeightRegExp);
      if (!matches || matches[1] === 'normal') {
        return size * 1.2;
      }
      value = +matches[2];
      switch (matches[3]) {
      case 'px':
        return value;
      case '%':
        value /= 100;
        break;
      default:
        break;
      }
      return size * value;
    },
    /**
     * ToFont
     */
    toFont: function(options) {
      let size = options.size;
      if (typeof size === 'string') {
        size = parseInt(size, 10);
      }
      let style = options.style;
      if (style && !('' + style).match(CharbaToast.helper.fontStyleRegExp)) {
        style = '';
      }
      const font = {
        family: options.family,
        lineHeight: CharbaToast.helper.toLineHeight.apply(this, [options.lineHeight, size]),
        size,
        style,
        weight: options.weight,
        string: ''
      };
      font.string = (font.style ? font.style + ' ' : '')
		+ (font.weight ? font.weight + ' ' : '')
		+ font.size + 'px '
		+ font.family;
		
      return font;
    }
  },
  clone: function(source) {
    return Object.assign({}, source || {});
  },
  defaults: {
    align: undefined,
    actions: [],
    autoHide: true,
    borderRadius: 8,
    hideProgressBar: false,
    hideShadow: false,
    icon: undefined,
    onClick: undefined,
    onClose: undefined,
    onOpen: undefined,
    modifierKey: undefined,
    progressBarHeight: undefined,
    progressBarType: undefined,
    title: {
      color: undefined,
      font: undefined
    },
    label: {
      color: undefined,
      font: undefined
    },
    action: {
      name: undefined,
      onClick: undefined,
      backgroundColor: undefined,
      borderColor: undefined,
      borderWidth: 0,
      borderStyle: ['none'],
      borderRadius: 0, 
      color: undefined,
      font: undefined
    },
    timeout: 4000,
    type: undefined,
    titleFont: {
      family: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
      lineHeight: 1.2,
      size: 15,
      style: 'normal',
      weight: 'bold',
    },
    labelFont: {
      family: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
      lineHeight: '20px',
      size: 14,
      style: 'normal',
      weight: 'normal',
    }
  },
  overrides: {},
  onClose: undefined, // internal use only
  currentOpenItems : 0
};
// freeze defaults and helper
Object.freeze(CharbaToast.defaults);
Object.freeze(CharbaToast.helper);
// needs DOM to be ready
if (document.readyState === 'complete') {
  CharbaToast.init();
} else {
  window.addEventListener('DOMContentLoaded', CharbaToast.init);
}
