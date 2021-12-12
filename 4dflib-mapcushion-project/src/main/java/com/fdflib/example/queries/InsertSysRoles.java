package com.fdflib.example.queries;

import com.fdflib.example.arlington_tables.SysRole;
import com.fdflib.example.arlington_tables.SystemRole;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;

public class InsertSysRoles extends FdfCommonServices {
    public SystemRole saveSysRole(SystemRole systemRole) {
        if(systemRole != null) {
            return this.save(SystemRole.class, systemRole).current;
        }

        return null;
    }

    public SystemRole getSysRolesById(long id) {
        return getSysRolesWithHistoryById(id).current;
    }

    public FdfEntity<SystemRole> getSysRolesWithHistoryById(long id) {
        FdfEntity<SystemRole> systemRole = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            systemRole = this.getEntityById(SystemRole.class, id);
        }

        return systemRole;
    }

}
