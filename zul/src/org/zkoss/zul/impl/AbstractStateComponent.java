/* AbstractStateComponent.java

	Purpose:
		
	Description:
		
	History:
		Fri Mar 13 12:08:01 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zul.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.sys.ContentRenderer;

/**
 * A state component.
 */
public abstract class AbstractStateComponent extends XulElement {
	private Map<String, Object> _states = new LinkedHashMap<>(); // TODO: no need to create one if not set anything
	private boolean _hasRendered;

	@Override
	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);
		_states.forEach((k, v) -> {
			try {
				renderer.render(k, v);
			} catch (Exception e) {
				throw UiException.Aide.wrap(e);
			}
		});
		_hasRendered = true;
	}

	/**
	 * Gets if the component is rendered.
	 * @return true if the component is rendered.
	 */
	protected boolean isRendered() {
		return _hasRendered;
	}

	@Override
	public void invalidate() {
		super.invalidate();
		_hasRendered = false;
	}

	/**
	 * Sets the state.
	 *
	 * @param key the key of a state
	 * @param value the value of a state
	 * @param <T> a type of a state
	 */
	@SuppressWarnings("unchecked")
	protected <T> void setState(String key, T value) {
		T old = (T) _states.put(key, value);
		if (!Objects.equals(old, value) && isRendered()) {
			smartUpdate(key, value);
		}
	}

	/**
	 * Gets the state.
	 *
	 * @param key the key of a state
	 * @param <T> a type of a state
	 * @return the value of a state
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getState(String key) {
		return (T) _states.get(key);
	}
}
