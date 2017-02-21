package com.cidic.sdx.hpgl.service;

import com.cidic.sdx.hpgl.model.PermissionsModel;

public interface PermissionsService {

	public PermissionsModel createPermission(PermissionsModel permission);

	public void deletePermission(Long permissionId);
}
