package com.fdflib.example.spackenkill_queries;

import com.fdflib.example.arlington_tables.Visit;
import com.fdflib.example.spackenkill_tables.SK_CheckStatus;
import com.fdflib.example.spackenkill_tables.SK_Visit;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.ArrayList;
import java.util.List;

public class SK_CheckInStatus extends FdfCommonServices {
    public SK_Visit saveVisit(SK_Visit visit) {
        if(visit != null) {
            // id is unique for visit so we are going to check for existing one first
            SK_Visit existingVisit = getVisitByPerIdNum(visit.personalIdNum);
            if(existingVisit != null) {
                // if a match is found, just set the id to match the existing one and it will automatically update
                // instead of insert
                visit.id = existingVisit.id;
            }

            if (visit != null) {
                return this.save(SK_Visit.class, visit).current;
            }
        }

        return null;
    }

    /*
        Library Functions available for any object that extends CommonState
    */
    public SK_Visit getVisitById(long id) {
        return getVisitWithHistoryById(id).current;
    }

    public FdfEntity<SK_Visit> getVisitWithHistoryById(long id) {
        FdfEntity<SK_Visit> visit = new FdfEntity<>();

        // get the test
        if (id >= 0) {
            visit = this.getEntityById(SK_Visit.class, id);

            // get the users
            visit.current.currentUser = new SK_InsertUsers().getUsersById(visit.current.currentUserId);
            for (SK_Visit visitHistory: visit.history) {
                visitHistory.currentUser = new SK_InsertUsers().getUsersById(visitHistory.currentUserId);
            }
        }

        return visit;
    }

    public FdfEntity<SK_Visit> getVisitByPerIdNumHistory(String personalIdNum) {
        List<FdfEntity<SK_Visit>> visitWithHistory = getEntitiesByValueForPassedField(SK_Visit.class, "personalIdNum", personalIdNum);
        if(visitWithHistory.size() > 0) {
            return visitWithHistory.get(0);
        }

        return null;
    }

    public SK_Visit getVisitByPerIdNum(String personalIdNum) {
        FdfEntity<SK_Visit> visitWithHistory = getVisitByPerIdNumHistory(personalIdNum);
        if(visitWithHistory != null && visitWithHistory.current != null) {
            return visitWithHistory.current;
        }

        return null;
    }

    /*
        Queries for all visits
        Returns each with history as a List<FdfEntity<Visit>>
     */

    public List<FdfEntity<SK_Visit>> getVisitByCheckStatusWithHistory(SK_CheckStatus checkStatus) {
        List<FdfEntity<SK_Visit>> visitByCheckStatus = getEntitiesByValueForPassedField(SK_Visit.class, "checkStatus", checkStatus.toString());
        return visitByCheckStatus;
    }

    public List<SK_Visit> getVisitByCheckStatus(SK_CheckStatus checkStatus) {
        List<FdfEntity<SK_Visit>> visitByCheckStatusWithHistory = getVisitByCheckStatusWithHistory(checkStatus);
        List<SK_Visit> visit = new ArrayList<>();
        for(FdfEntity<SK_Visit> visitWithHistory: visitByCheckStatusWithHistory) {
            if(visitWithHistory.current != null) {
                visit.add(visitWithHistory.current);
            }
        }
        return visit;
    }

    public FdfEntity<SK_Visit> customVisitQuery(String name) {
        FdfEntity<SK_Visit> visit = new FdfEntity<>();

        if(visit != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "personalIdNum";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<SK_Visit> returnedStates = SqlStatement.build().where(whereName).run(SK_Visit.class);
            return manageReturnedEntity(returnedStates);

        }

        return visit;
    }

}
