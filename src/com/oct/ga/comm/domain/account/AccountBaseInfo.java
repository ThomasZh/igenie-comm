package com.oct.ga.comm.domain.account;

import com.google.gson.Gson;
import com.oct.ga.comm.domain.JsonBeanAdapter;

public class AccountBaseInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -902289953752087847L;

	private String id;
	private String loginName;
	private String name;

	@Override
	public AccountBaseInfo decode(String json)
	{
		Gson gson = new Gson();
		AccountBaseInfo info = gson.fromJson(json, AccountBaseInfo.class);
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

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

}
