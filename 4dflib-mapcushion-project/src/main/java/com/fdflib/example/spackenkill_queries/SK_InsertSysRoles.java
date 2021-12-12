package com.fdflib.example.spackenkill_queries;

import com.fdflib.example.spackenkill_tables.SK_SystemRole;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class SK_InsertSysRoles extends FdfCommonServices {
    public SK_SystemRole saveSysRole(SK_SystemRole systemRole) {
        if(systemRole != null) {
            return this.save(SK_SystemRole.class, systemRole).current;
        }

        return null;
    }

    public SK_SystemRole getSysRolesById(long id) {
        return getSysRolesWithHistoryById(id).current;
    }

    public FdfEntity<SK_SystemRole> getSysRolesWithHistoryById(long id) {
        FdfEntity<SK_SystemRole> systemRole = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            systemRole = this.getEntityById(SK_SystemRole.class, id);
        }

        return systemRole;
    }

}
