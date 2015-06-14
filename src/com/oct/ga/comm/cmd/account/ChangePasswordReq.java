package com.oct.ga.comm.cmd.account;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class ChangePasswordReq
		extends ReqCommand
{
	public ChangePasswordReq()
	{
		super();

		this.setTag(Command.CHANGE_PASSWORD_REQ);
	}

	public ChangePasswordReq(String email, String oldPassword, String newPassword)
	{
		this();

		this.setEmail(email);
		this.setNewPassword(newPassword);
		this.setOldPassword(oldPassword);
	}

	@Override
	public ChangePasswordReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 4;
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");
		TlvParser.decodeChildren(tlv, childCount);

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tEmail = tlv.getChild(i++);
		email = new String(tEmail.getValue(), "UTF-8");
		logger.debug("email: " + email);

		TlvObject tOldPassword = tlv.getChild(i++);
		oldPassword = new String(tOldPassword.getValue(), "UTF-8");

		TlvObject tNewPassword = tlv.getChild(i++);
		newPassword = new String(tNewPassword.getValue(), "UTF-8");

		return this;
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;

		TlvObject tSequence = new TlvObject(i++, 4, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tEmail = new TlvObject(i++, this.getEmail());
		TlvObject tOldPassword = new TlvObject(i++, oldPassword);
		TlvObject tNewPassword = new TlvObject(i++, newPassword);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.push(tSequence);
		tlv.push(tEmail);
		tlv.push(tOldPassword);
		tlv.push(tNewPassword);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	private String email;
	private String oldPassword;
	private String newPassword;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	private final static Logger logger = LoggerFactory.getLogger(ChangePasswordReq.class);
}
