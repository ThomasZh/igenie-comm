package com.oct.ga.comm.domain.msg;

import com.google.gson.Gson;
import com.oct.ga.comm.domain.JsonBeanAdapter;

public class MessageOriginalMulticast
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6993218118890836226L;
	/**
	 * MessageID(UUID)
	 */
	private String _id;
	/**
	 * accountId(UUID)
	 */
	private String fromAccountId;
	private String fromAccountName;
	/**
	 * time(second) since 1970.1.1
	 */
	private int timestamp;
	/**
	 * 101:task_message, 102:chat_message, 103:personal_message, 104:invite,
	 * 105:task_log, 106:news, 107:vote, 108:gomoku
	 */
	private short channelType;
	/**
	 * taskId,chatId,toAccountId(UUID),gameId
	 */
	private String channelId;
	private String channelName;
	private String chatId;
	/**
	 * 0:txt, 1:picture, 2:audio, 3:video, 9:others 101:join_game,
	 * 102:giveup_game, 103:play_next_step, 104:congratulate_win_game
	 */
	private short contentType;
	/**
	 * play_nex_step:{'step'='15','i'='3','j'='4'}
	 */
	private String content;
	private String attachUrl;

	@Override
	public MessageOriginalMulticast decode(String json)
	{
		Gson gson = new Gson();
		MessageOriginalMulticast info = gson.fromJson(json, MessageOriginalMulticast.class);
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

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public String getFromAccountId()
	{
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId)
	{
		this.fromAccountId = fromAccountId;
	}

	public String getFromAccountName()
	{
		return fromAccountName;
	}

	public void setFromAccountName(String fromAccountName)
	{
		this.fromAccountName = fromAccountName;
	}

	public int getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}

	public short getChannelType()
	{
		return channelType;
	}

	public void setChannelType(short toChannelType)
	{
		this.channelType = toChannelType;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String toChannelId)
	{
		this.channelId = toChannelId;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String toChannelName)
	{
		this.channelName = toChannelName;
	}

	public short getContentType()
	{
		return contentType;
	}

	public void setContentType(short contentType)
	{
		this.contentType = contentType;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getAttachUrl()
	{
		return attachUrl;
	}

	public void setAttachUrl(String attachId)
	{
		this.attachUrl = attachId;
	}

	public String getChatId()
	{
		return chatId;
	}

	public void setChatId(String chatId)
	{
		this.chatId = chatId;
	}

}
