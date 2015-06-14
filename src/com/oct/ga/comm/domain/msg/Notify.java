package com.oct.ga.comm.domain.msg;

import com.google.gson.Gson;

public class Notify
		extends MessageInlinecast
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4531992883612523585L;

	@Override
	public Notify decode(String json)
	{
		Gson gson = new Gson();
		Notify info = gson.fromJson(json, Notify.class);
		return info;
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	@Override
	public String toString()
	{
		return this.encode();
	}
}
