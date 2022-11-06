package com.malyson.esig.tarefas.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction trx = manager.getTransaction();
        boolean interceptado = false;

        try {
            if (!trx.isActive()) {
                trx.begin();
                trx.rollback();
                trx.begin();

                interceptado = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (trx != null && interceptado) {
                trx.rollback();
            }

            throw e;
        } finally {
            if (trx != null && trx.isActive() && interceptado) {
                trx.commit();
            }
        }
    }

}