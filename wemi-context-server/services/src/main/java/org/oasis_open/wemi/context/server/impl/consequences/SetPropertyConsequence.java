package org.oasis_open.wemi.context.server.impl.consequences;

import org.oasis_open.wemi.context.server.api.User;
import org.oasis_open.wemi.context.server.api.consequences.Consequence;
import org.oasis_open.wemi.context.server.api.consequences.ConsequenceExecutor;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by toto on 26/06/14.
 */
@ApplicationScoped
@OsgiServiceProvider
@Properties({
    @Property(name = "consequenceExecutorId", value = "setProperty")
})
public class SetPropertyConsequence implements ConsequenceExecutor {
    public SetPropertyConsequence() {
    }

    public String getConsequenceId() {
        return "setPropertyConsequence";
    }

    public boolean execute(Consequence consequence, User user, Object context) {
        String propertyValue = (String) consequence.getParameterValues().get("propertyValue");
        if (propertyValue.equals("now")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            propertyValue = format.format(new Date());
        }
        user.setProperty(
                (String) consequence.getParameterValues().get("propertyName"),
                propertyValue);
        return true;
    }

}
