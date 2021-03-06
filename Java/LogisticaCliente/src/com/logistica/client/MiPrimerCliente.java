package com.logistica.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.logistica.interfaces.TestEntityManagerRemote;

public class MiPrimerCliente {

	public static void main(String[] args) throws Exception {

		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);
		final String earName = "LogisticaAppEAR"; // Nombre del m�dulo EAR
		final String ejbModuleName = "LogisticaApp"; // Nombre del m�dulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
		final String ejbClassName = "TestEntityManager"; // Nombre corto del EJB
		final String fullInterfaceName = TestEntityManagerRemote.class.getName();

		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;

		System.out.println("Conectando a " + lookupName);

		TestEntityManagerRemote mbr = (TestEntityManagerRemote) context.lookup(lookupName);

		mbr.nuevoArticulo();

		/*
		 * final String statefulEjbClassName = "StatefulLogisticaCalcServer"; //
		 * Nombre corto del EJB final String statefulFullInterfaceName =
		 * StatefulLogisticaCalcServerRemote.class.getName(); String
		 * statefulLookupName = "ejb:" + earName + "/" + ejbModuleName + "/" +
		 * distinctName + "/" + statefulEjbClassName + "!" +
		 * statefulFullInterfaceName + "?stateful";
		 * System.out.println("Conectando a " + statefulLookupName);
		 * StatefulLogisticaCalcServerRemote calc =
		 * (StatefulLogisticaCalcServerRemote)
		 * context.lookup(statefulLookupName); calc.add(10); calc.substract(3);
		 * System.out.println("Respuesta: " + calc.getValue());
		 */
	}

}
