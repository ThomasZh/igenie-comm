package com.oct.ga.comm.domain.msg;

import com.google.gson.Gson;

public class NotifyGomoku
		extends Notify
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8180756838005920957L;

	@Override
	public NotifyGomoku decode(String json)
	{
		Gson gson = new Gson();
		NotifyGomoku info = gson.fromJson(json, NotifyGomoku.class);
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
