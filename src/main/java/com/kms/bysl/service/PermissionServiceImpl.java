package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.PermissionDAO;
import com.kms.bysl.dto.PermissionDTO;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDAO dao;
	
	@Override
	public List<PermissionDTO> permissionSelectByTeamId(int teamId) {
		List<PermissionDTO> permissions;
		permissions = dao.permissionSelectByTeamId(teamId);
		return permissions;
	}

	@Override
	public List<PermissionDTO> permissionSelectAll() {
		List<PermissionDTO> permissions;
		permissions = dao.permissionSelectAll();
		return permissions;
	}

}
