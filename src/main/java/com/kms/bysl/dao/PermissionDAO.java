package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.PermissionDTO;

public interface PermissionDAO {
	public List<PermissionDTO> permissionSelectByTeamId(int teamId);
	public List<PermissionDTO> permissionSelectAll();
}
