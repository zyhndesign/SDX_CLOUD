package com.cidic.sdx.hpgl.dao;

import com.cidic.sdx.hpgl.model.PermissionsModel;

public interface PermissionDao {

	 public PermissionsModel createPermission(PermissionsModel permission);

	 public void deletePermission(Long permissionId);
	    
}
