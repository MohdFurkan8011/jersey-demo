package com.jersey.rnd.inject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

@Service
public class JustInTimeServiceResolver implements JustInTimeInjectionResolver {

    @Inject
    private ServiceLocator serviceLocator;

    public boolean justInTimeResolution(Injectee injectee) {
    	
    	final Type requiredType = injectee.getRequiredType();

        if (injectee.getRequiredQualifiers().isEmpty() && requiredType instanceof Class) {
            final Class<?> requiredClass = (Class<?>) requiredType;

            // IMPORTANT: check the package name, so we don't accidentally preempt other framework JIT resolvers
            if (requiredClass.getName().startsWith("com.jersey.rnd")) {
                final List<ActiveDescriptor<?>> descriptors = ServiceLocatorUtilities.addClasses(serviceLocator, requiredClass);

                return !descriptors.isEmpty();
            }
        }
        return false;
    }

}