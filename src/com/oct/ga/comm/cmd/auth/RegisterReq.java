package com.oct.ga.comm.cmd.auth;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.domain.account.AccountDetailInfo;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvObjectExt;
import com.oct.ga.comm.tlv.TlvParser;

public class RegisterReq
		extends ReqCommand
{

	private String password;

	public RegisterReq()
	{
		super();

		this.setTag(Command.REGISTER_REQ);
	}

	public RegisterReq(AccountDetailInfo account)
	{
		this();

		this.setAccount(account);
	}

	public RegisterReq(AccountDetailInfo account, String password)
	{
		this();
		this.password = password;
		this.account = account;
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;

		TlvObject tSequence = new TlvObject(i++, 4, TlvByteUtil.int2Byte(sequence));
		TlvObject tFirstName = new TlvObject(i++, account.getName());
		TlvObject tLastName = new TlvObject(i++, "");
		TlvObject tEmail = new TlvObject(i++, account.getEmail());
		TlvObject tPassword = new TlvObject(i++, this.password);
		TlvObject tFacePhoto = new TlvObject(i++, "");

		TlvObjectExt tlv = new TlvObjectExt(this.getTag());
		tlv.plus(tSequence);
		tlv.plus(tFirstName);
		tlv.plus(tLastName);
		tlv.plus(tEmail);
		tlv.plus(tPassword);
		tlv.plus(tFacePhoto);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public RegisterReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 6;
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");
		TlvParser.decodeChildren(tlv, childCount);

		int i = 0;

		TlvObject tSequence = tlv.getChild(i++);
		sequence = TlvByteUtil.byte2Int(tSequence.getValue());
		logger.debug("sequence: " + sequence);

		TlvObject tFirstName = tlv.getChild(i++);
		String firstName = new String(tFirstName.getValue(), "UTF-8");
		logger.debug("firstName: " + firstName);

		TlvObject tLastName = tlv.getChild(i++);
		String lastName = new String(tLastName.getValue(), "UTF-8");
		logger.debug("lastname: " + lastName);

		TlvObject tEmail = tlv.getChild(i++);
		String email = new String(tEmail.getValue(), "UTF-8");
		logger.debug("email: " + email);

		TlvObject tPassword = tlv.getChild(i++);
		setMd5pwd(new String(tPassword.getValue(), "UTF-8"));

		TlvObject tFacePhoto = tlv.getChild(i++);
		String facePhoto = new String(tFacePhoto.getValue(), "UTF-8");
		logger.debug("facePhoto: " + facePhoto);

		account = new AccountDetailInfo();
		account.setEmail(email);
		account.setImageUrl(facePhoto);
		account.setName(firstName);

		return this;
	}

	private String md5pwd;
	private AccountDetailInfo account;

	public AccountDetailInfo getAccount()
	{
		return account;
	}

	public void setAccount(AccountDetailInfo account)
	{
		this.account = account;
	}

	public String getMd5pwd()
	{
		return md5pwd;
	}

	public void setMd5pwd(String md5pwd)
	{
		this.md5pwd = md5pwd;
	}

	private final static Logger logger = LoggerFactory.getLogger(RegisterReq.class);
}
