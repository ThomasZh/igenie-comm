package com.oct.ga.comm.cmd.account;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.domain.account.AccountDetailInfo;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class UploadAccountReq
		extends ReqCommand
{
	public UploadAccountReq()
	{
		super();

		this.setTag(Command.UPLOAD_MY_ACCOUNT_REQ);
	}

	public UploadAccountReq(AccountDetailInfo account)
	{
		this();

		this.account = account;
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;

		TlvObject tSequence = new TlvObject(i++, 4, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tAccountId = new TlvObject(i++, account.getId());
		TlvObject tAccountName = new TlvObject(i++, account.getName());
		TlvObject tPhone = new TlvObject(i++, account.getTelephone());
		TlvObject tDescription = new TlvObject(i++, account.getDesc());
		TlvObject tFacePhoto = new TlvObject(i++, account.getImageUrl());

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.push(tSequence);
		tlv.push(tAccountId);
		tlv.push(tAccountName);
		tlv.push(tPhone);
		tlv.push(tDescription);
		tlv.push(tFacePhoto);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public UploadAccountReq decode(TlvObject tlv)
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

		TlvObject tAccountId = tlv.getChild(i++);
		String accountId = new String(tAccountId.getValue(), "UTF-8");
		logger.debug("myAccountId: " + accountId);

		TlvObject tAccountName = tlv.getChild(i++);
		String accountName = new String(tAccountName.getValue(), "UTF-8");
		logger.debug("accountName: " + accountName);

		TlvObject tPhone = tlv.getChild(i++);
		String phone = new String(tPhone.getValue(), "UTF-8");
		logger.debug("phone: " + phone);

		TlvObject tDescription = tlv.getChild(i++);
		String description = new String(tDescription.getValue(), "UTF-8");
		logger.debug("description: " + description);

		TlvObject tFacePhoto = tlv.getChild(i++);
		String facePhoto = new String(tFacePhoto.getValue(), "UTF-8");
		logger.debug("facePhoto: " + facePhoto);

		account = new AccountDetailInfo();
		account.setId(accountId);
		account.setName(accountName);
		account.setTelephone(phone);
		account.setImageUrl(facePhoto);
		account.setDesc(description);

		return this;
	}

	private AccountDetailInfo account;

	public AccountDetailInfo getAccount()
	{
		return account;
	}

	public void setAccount(AccountDetailInfo account)
	{
		this.account = account;
	}

	private final static Logger logger = LoggerFactory.getLogger(UploadAccountReq.class);
}
