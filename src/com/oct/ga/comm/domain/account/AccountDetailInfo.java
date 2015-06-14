package com.oct.ga.comm.domain.account;

import com.google.gson.Gson;

public class AccountDetailInfo
		extends AccountMasterInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6562618215234266799L;
	private String desc;
	private String email; // toLowerCase
	private String telephone;
	private short state;

	@Override
	public AccountDetailInfo decode(String json)
	{
		Gson gson = new Gson();
		AccountDetailInfo info = gson.fromJson(json, AccountDetailInfo.class);
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public short getState()
	{
		return state;
	}

	public void setState(short state)
	{
		this.state = state;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}
}
