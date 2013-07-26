package org.sample.jboss.snowdrop.snowdrop_41.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.annotation.spring.Spring;
import org.jboss.spring.callback.SpringLifecycleInterceptor;
import org.sample.jboss.snowdrop.snowdrop_41.repo.MemberDao;

@Stateless
@Interceptors(SpringLifecycleInterceptor.class)
public class InjectEJBImpl implements InjectEJB {

	@Spring(bean="springBean", jndiName="MyApp")
	private MemberDao memberDao;

	@Override
	public void writeSimplePersonData() {
		  System.out.println("Will write the name of the person that has been injected with snowdrop:");
		  memberDao.findAllOrderedByName();
	}
}
