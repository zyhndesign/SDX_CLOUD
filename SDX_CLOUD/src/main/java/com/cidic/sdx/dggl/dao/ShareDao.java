package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Share;

public interface ShareDao {

	public void createShare(Share share);

	public List<Share> getShareList(int matchId, int userId);
}
