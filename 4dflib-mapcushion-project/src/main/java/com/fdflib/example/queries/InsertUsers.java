package com.fdflib.example.queries;

import com.fdflib.example.arlington_tables.Users;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;

/*
 * Created by Rorie on 12/5/21
 */
public class InsertUsers extends FdfCommonServices {
    public Users saveUsers(Users users) {
        if(users != null) {
            // personal ID number is unique for every user so we are going to check for existing one first
            Users existingUser = getUsersByPerIdNum(users.personalIdNum);
            if(existingUser != null) {
                // if a match is found, just set the id to match the existing one and it will automatically update
                // instead of insert
                users.id = existingUser.id;
            }

            if (users != null) {
                return this.save(Users.class, users).current;
            }
        }

        return null;
    }

    /*
        Library Functions available for any object that extends CommonState
     */

    public Users getUsersById(long id) {
        return getUsersWithHistoryById(id).current;
    }

    /*
        Another library function available for any object extending CommonState this one returns an FdfEntity which
        includes current FdfEntity.currrent (returns Users) and historical data FdfEntity.history (returns List<Users>)
     */

    public FdfEntity<Users> getUsersWithHistoryById(long id) {
        FdfEntity<Users> users = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            users = this.getEntityById(Users.class, id);

            // get the roles
            users.current.currentSysRoles = new InsertSysRoles().getSysRolesById(users.current.currentSysRolesId);
            for(Users userHistory: users.history) {
                userHistory.currentSysRoles = new InsertSysRoles().getSysRolesById(userHistory.currentSysRolesId);
            }
        }

        return users;
    }

    /*
        Queries for all users with the personal ID num passed
        Returns simple Users
     */
    public FdfEntity<Users> getUsersByPerIdNumHistory(String personalIdNum) {
        List<FdfEntity<Users>> usersWithHistory = getEntitiesByValueForPassedField(Users.class, "personalIdNum", personalIdNum);
        if(usersWithHistory.size() > 0) {
            return usersWithHistory.get(0);
        }

        return null;
    }

    /*
        Returns the same as the last method, but removes the historical records
        Returns simple Users
     */

    public Users getUsersByPerIdNum(String personalIdNum) {
        FdfEntity<Users> usersWithHistory = getUsersByPerIdNumHistory(personalIdNum);
        if(usersWithHistory != null && usersWithHistory.current != null) {
            return usersWithHistory.current;
        }

        return null;
    }

    /*
        Write custom query
     */

    public FdfEntity<Users> customUserQuery(String name) {
        FdfEntity<Users> users = new FdfEntity<>();

        if(users != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "personalIdNum";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<Users> returnedStates = SqlStatement.build().where(whereName).run(Users.class);
            return manageReturnedEntity(returnedStates);

        }

        return users;
    }



}
