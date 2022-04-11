package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SoloShareDTO;

public interface SoloShareDAO {
	public void soloShareInsert(SoloShareDTO soloShare);
	public List<SoloShareDTO> soloShareSelect(int ownerId, String url);
	public List<SoloShareDTO> soloShareSelectAllByManagerId(int ownerId);
	public List<SoloShareDTO> soloShareSelectByManagerId(int ownerId, String url);
	public void soloShareDelete(int soloShareId);
}
