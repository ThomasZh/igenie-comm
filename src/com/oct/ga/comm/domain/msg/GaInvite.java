package com.oct.ga.comm.domain.msg;

import com.google.gson.Gson;
import com.oct.ga.comm.domain.JsonBeanAdapter;

public class GaInvite
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5436390190562608020L;
	private String inviteId;
	private short inviteType;
	private String fromUserId;
	private String fromUserName;
	private String fromUserAvatarUrl;
	private String toUserSemiId;
	private short channelType;
	private String channelId;
	private String channelName;
	private int expiry;
	private int timestamp;

	@Override
	public GaInvite decode(String json)
	{
		Gson gson = new Gson();
		GaInvite info = gson.fromJson(json, GaInvite.class);
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

	public String getFromUserName()
	{
		return fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

	public String getInviteId()
	{
		return inviteId;
	}

	public void setInviteId(String inviteId)
	{
		this.inviteId = inviteId;
	}

	public short getInviteType()
	{
		return inviteType;
	}

	public void setInviteType(short inviteType)
	{
		this.inviteType = inviteType;
	}

	public String getFromUserId()
	{
		return fromUserId;
	}

	public void setFromUserId(String fromUserId)
	{
		this.fromUserId = fromUserId;
	}

	public String getToUserSemiId()
	{
		return toUserSemiId;
	}

	public void setToUserSemiId(String toUserSemiId)
	{
		this.toUserSemiId = toUserSemiId;
	}

	public short getChannelType()
	{
		return channelType;
	}

	public void setChannelType(short channelType)
	{
		this.channelType = channelType;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public int getExpiry()
	{
		return expiry;
	}

	public void setExpiry(int expiry)
	{
		this.expiry = expiry;
	}

	public int getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(int lastUpdateTime)
	{
		this.timestamp = lastUpdateTime;
	}

	public String getFromUserAvatarUrl()
	{
		return fromUserAvatarUrl;
	}

	public void setFromUserAvatarUrl(String fromUserAvatarUrl)
	{
		this.fromUserAvatarUrl = fromUserAvatarUrl;
	}

}
