package com.jersey.rnd.inject;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.jersey.rnd.dao.VaccineDAO;
import com.jersey.rnd.dao.impl.VaccineDAOImpl;
import com.jersey.rnd.service.VaccineService;
import com.jersey.rnd.service.impl.VaccineServiceImpl;

@Provider
public class ApplicationBinder extends AbstractBinder {

	@Override
    protected void configure() {
		
		bind(VaccineServiceImpl.class).to(VaccineService.class).in(Singleton.class);
		bind(VaccineDAOImpl.class).to(VaccineDAO.class).in(Singleton.class);
        bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
    }
	
}
