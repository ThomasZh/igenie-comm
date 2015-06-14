package com.oct.ga.comm.domain.msg;

import com.google.gson.Gson;

public class Invite
		extends MessageInlinecast
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4272723893486303731L;
	/**
	 * Invire ID.
	 */
	private String srcInviteId;
	/**
	 * 121: Apply,122: Accept,123: Reject
	 */
	private short inviteState;
	/**
	 * ����taskdesc
	 */
	private String attachDesc;

	@Override
	public Invite decode(String json)
	{
		Gson gson = new Gson();
		Invite info = gson.fromJson(json, Invite.class);
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

	public String getSrcInviteId()
	{
		return srcInviteId;
	}

	public void setSrcInviteId(String srcInviteId)
	{
		this.srcInviteId = srcInviteId;
	}

	public short getInviteState()
	{
		return inviteState;
	}

	public void setInviteState(short inviteState)
	{
		this.inviteState = inviteState;
	}

	public String getAttachDesc()
	{
		return attachDesc;
	}

	public void setAttachDesc(String attachDesc)
	{
		this.attachDesc = attachDesc;
	}
}
