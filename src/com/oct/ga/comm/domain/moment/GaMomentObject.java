package com.oct.ga.comm.domain.moment;

import java.util.List;

import com.google.gson.Gson;
import com.oct.ga.comm.domain.JsonBeanAdapter;

public class GaMomentObject
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3067743932929217896L;
	private String channelId;
	private String channelName;
	private String momentId;
	private String userId;
	private String userName;
	private String userPhotoUrl;
	private String desc;
	private int timestamp;
	private List<String> photos;

	@Override
	public GaMomentObject decode(String json)
	{
		Gson gson = new Gson();
		GaMomentObject info = gson.fromJson(json, GaMomentObject.class);
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

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public String getMomentId()
	{
		return momentId;
	}

	public void setMomentId(String momentId)
	{
		this.momentId = momentId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public int getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}

	public List<String> getPhotos()
	{
		return photos;
	}

	public void setPhotos(List<String> photos)
	{
		this.photos = photos;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPhotoUrl()
	{
		return userPhotoUrl;
	}

	public void setUserPhotoUrl(String userPhotoUrl)
	{
		this.userPhotoUrl = userPhotoUrl;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

}
