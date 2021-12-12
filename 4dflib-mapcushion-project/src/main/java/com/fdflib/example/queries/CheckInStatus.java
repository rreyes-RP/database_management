package com.fdflib.example.queries;

import com.fdflib.example.arlington_tables.CheckStatus;
import com.fdflib.example.arlington_tables.Visit;
import com.fdflib.example.spackenkill_tables.SK_Users;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.ArrayList;
import java.util.List;

public class CheckInStatus extends FdfCommonServices {
    public Visit saveVisit(Visit visit) {
        if(visit != null) {
            // id is unique for visit so we are going to check for existing one first
            Visit existingVisit = getVisitByPerIdNum(visit.personalIdNum);
            if(existingVisit != null) {
                // if a match is found, just set the id to match the existing one and it will automatically update
                // instead of insert
                visit.id = existingVisit.id;
            }

            if (visit != null) {
                return this.save(Visit.class, visit).current;
            }
        }

        return null;
    }

    /*
        Library Functions available for any object that extends CommonState
    */
    public Visit getVisitById(long id) {
        return getVisitWithHistoryById(id).current;
    }

    public FdfEntity<Visit> getVisitWithHistoryById(long id) {
        FdfEntity<Visit> visit = new FdfEntity<>();

        // get the test
        if (id >= 0) {
            visit = this.getEntityById(Visit.class, id);

            // get the users
            visit.current.currentUser = new InsertUsers().getUsersById(visit.current.currentUserId);
            for (Visit visitHistory: visit.history) {
                visitHistory.currentUser = new InsertUsers().getUsersById(visitHistory.currentUserId);
            }
        }

        return visit;
    }

    public FdfEntity<Visit> getVisitByPerIdNumHistory(String personalIdNum) {
        List<FdfEntity<Visit>> visitWithHistory = getEntitiesByValueForPassedField(Visit.class, "personalIdNum", personalIdNum);
        if(visitWithHistory.size() > 0) {
            return visitWithHistory.get(0);
        }

        return null;
    }

    public Visit getVisitByPerIdNum(String personalIdNum) {
        FdfEntity<Visit> visitWithHistory = getVisitByPerIdNumHistory(personalIdNum);
        if(visitWithHistory != null && visitWithHistory.current != null) {
            return visitWithHistory.current;
        }

        return null;
    }

    /*
        Queries for all visits
        Returns each with history as a List<FdfEntity<Visit>>
     */

    public List<FdfEntity<Visit>> getVisitByCheckStatusWithHistory(CheckStatus checkStatus) {
        List<FdfEntity<Visit>> visitByCheckStatus = getEntitiesByValueForPassedField(Visit.class, "checkStatus", checkStatus.toString());
        return visitByCheckStatus;
    }

    public List<Visit> getVisitByCheckStatus(CheckStatus checkStatus) {
        List<FdfEntity<Visit>> visitByCheckStatusWithHistory = getVisitByCheckStatusWithHistory(checkStatus);
        List<Visit> visit = new ArrayList<>();
        for(FdfEntity<Visit> visitWithHistory: visitByCheckStatusWithHistory) {
            if(visitWithHistory.current != null) {
                visit.add(visitWithHistory.current);
            }
        }
        return visit;
    }

    public FdfEntity<Visit> customVisitQuery(String name) {
        FdfEntity<Visit> visit = new FdfEntity<>();

        if(visit != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "personalIdNum";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<Visit> returnedStates = SqlStatement.build().where(whereName).run(Visit.class);
            return manageReturnedEntity(returnedStates);

        }

        return visit;
    }

}
