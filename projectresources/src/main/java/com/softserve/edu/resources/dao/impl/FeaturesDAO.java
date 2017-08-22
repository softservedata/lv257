/*
 * 
 */
package com.softserve.edu.resources.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.Features;
import com.sun.org.apache.xalan.internal.utils.FeatureManager.Feature;

public class FeaturesDAO extends crudDAOImpl<Features> {
	public FeaturesDAO(Session session) {
		super(Features.class, session);
	}

	public void setFeature(Feature feature) {

	}

	public List<Feature> getFeature(int featureID) {

	}

}
