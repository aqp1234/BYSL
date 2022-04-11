package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.PermissionDTO;

public interface PermissionService {
	public List<PermissionDTO> permissionSelectByTeamId(int teamId);
	public List<PermissionDTO> permissionSelectAll();
}
