package com.oct.ga.comm.cmd.following;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class UnfollowingReq
		extends ReqCommand
{
	public UnfollowingReq()
	{
		super();

		this.setTag(Command.UNFOLLOW_REQ);
	}

	public UnfollowingReq(String myAccountId, String friendId)
	{
		this();

		this.setFriendId(friendId);
		this.setMyAccountId(myAccountId);
	}

	@Override
	public UnfollowingReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		logger.info("from tlv:(tag=" + Command.UNFOLLOW_REQ + ", child=2) to command");

		TlvParser.decodeChildren(tlv, 2);

		TlvObject tMyAccountId = tlv.getChild(0);
		String myAccountId = new String(tMyAccountId.getValue(), "UTF-8");
		logger.info("myAccountId: " + myAccountId);

		TlvObject tFriendId = tlv.getChild(1);
		String friendId = new String(tFriendId.getValue(), "UTF-8");
		logger.info("friendId: " + friendId);

		this.setMyAccountId(myAccountId);
		this.setFriendId(friendId);

		return this;
	}

	private String myAccountId;
	private String friendId;

	public String getMyAccountId()
	{
		return myAccountId;
	}

	public void setMyAccountId(String myAccountId)
	{
		this.myAccountId = myAccountId;
	}

	public String getFriendId()
	{
		return friendId;
	}

	public void setFriendId(String friendId)
	{
		this.friendId = friendId;
	}

	private final static Logger logger = LoggerFactory.getLogger(UnfollowingReq.class);
}
