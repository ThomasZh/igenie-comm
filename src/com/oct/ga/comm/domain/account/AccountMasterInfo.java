package com.oct.ga.comm.domain.account;

import com.google.gson.Gson;

public class AccountMasterInfo
		extends AccountBaseInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3311855789534213291L;
	private String imageUrl;

	@Override
	public AccountMasterInfo decode(String json)
	{
		Gson gson = new Gson();
		AccountMasterInfo info = gson.fromJson(json, AccountMasterInfo.class);
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

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String url)
	{
		this.imageUrl = url;
	}
}
