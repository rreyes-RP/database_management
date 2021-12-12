package com.fdflib.example.spackenkill_queries;

import com.fdflib.example.arlington_tables.Users;
import com.fdflib.example.spackenkill_tables.SK_Users;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;

/*
 * Created by Rorie on 12/5/21
 */
public class SK_InsertUsers extends FdfCommonServices {
    public SK_Users saveUsers(SK_Users users) {
        if(users != null) {
            // personal ID number is unique for every user so we are going to check for existing one first
            SK_Users existingUser = getUsersByPerIdNum(users.personalIdNum);
            if(existingUser != null) {
                // if a match is found, just set the id to match the existing one and it will automatically update
                // instead of insert
                users.id = existingUser.id;
            }

            if (users != null) {
                return this.save(SK_Users.class, users).current;
            }
        }

        return null;
    }

    /*
        Library Functions available for any object that extends CommonState
     */

    public SK_Users getUsersById(long id) {
        return getUsersWithHistoryById(id).current;
    }

    /*
        Another library function available for any object extending CommonState this one returns an FdfEntity which
        includes current FdfEntity.currrent (returns Users) and historical data FdfEntity.history (returns List<Users>)
     */

    public FdfEntity<SK_Users> getUsersWithHistoryById(long id) {
        FdfEntity<SK_Users> users = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            users = this.getEntityById(SK_Users.class, id);

            // get the roles
            users.current.currentSysRoles = new SK_InsertSysRoles().getSysRolesById(users.current.currentSysRolesId);
            for(SK_Users userHistory: users.history) {
                userHistory.currentSysRoles = new SK_InsertSysRoles().getSysRolesById(userHistory.currentSysRolesId);
            }
        }

        return users;
    }

    /*
        Queries for all users with the personal ID num passed
        Returns simple Users
     */
    public FdfEntity<SK_Users> getUsersByPerIdNumHistory(String personalIdNum) {
        List<FdfEntity<SK_Users>> usersWithHistory = getEntitiesByValueForPassedField(SK_Users.class, "personalIdNum", personalIdNum);
        if(usersWithHistory.size() > 0) {
            return usersWithHistory.get(0);
        }

        return null;
    }

    /*
        Returns the same as the last method, but removes the historical records
        Returns simple Users
     */

    public SK_Users getUsersByPerIdNum(String personalIdNum) {
        FdfEntity<SK_Users> usersWithHistory = getUsersByPerIdNumHistory(personalIdNum);
        if(usersWithHistory != null && usersWithHistory.current != null) {
            return usersWithHistory.current;
        }

        return null;
    }

    /*
        Write custom query
     */

    public FdfEntity<SK_Users> customUserQuery(String name) {
        FdfEntity<SK_Users> users = new FdfEntity<>();

        if(users != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "personalIdNum";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<SK_Users> returnedStates = SqlStatement.build().where(whereName).run(SK_Users.class);
            return manageReturnedEntity(returnedStates);

        }

        return users;
    }
}
