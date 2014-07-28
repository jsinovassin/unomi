package org.oasis_open.wemi.context.server.persistence.elasticsearch.conditions;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.oasis_open.wemi.context.server.api.conditions.Condition;

import java.util.ArrayList;
import java.util.List;

/**
* Created by toto on 27/06/14.
*/
class AndConditionESQueryBuilder extends AbstractESQueryBuilder {
    @Override
    public String getConditionId() {
        return "andCondition";
    }

    @Override
    public FilterBuilder buildFilter(Condition condition, ConditionESQueryBuilderDispatcher dispatcher) {
        List<Condition> conditions = (List<Condition>) condition.getParameterValues().get("subConditions").getValue();

        List<FilterBuilder> l = new ArrayList<FilterBuilder>();
        for (Object sub : conditions) {
            l.add(dispatcher.buildFilter((Condition) sub));
        }
        return FilterBuilders.andFilter(l.toArray(new FilterBuilder[l.size()]));
    }
}
