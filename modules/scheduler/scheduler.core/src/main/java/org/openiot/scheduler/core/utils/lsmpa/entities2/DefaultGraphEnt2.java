package org.openiot.scheduler.core.utils.lsmpa.entities2;

import java.util.ArrayList;
import java.util.Set;

import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.XSD;

import org.openiot.lsm.schema.LSMSchema;


public class DefaultGraphEnt2 
{
	private LSMSchema  ontTemplate;	
	private LSMSchema  ontInstance;

	private Individual defaultGraphClassIdv;
	private OntClass ontClsDefaultGraph;
	private OntProperty ontPdefaultGraphURI;
	private OntProperty ontPdefaultGraphOfQueryRequest;
	
	//bean properties
	private String id;
	private String defaultGraphURI;
	
	private QueryRequestEnt2 queryRequestEnt;
	
	public DefaultGraphEnt2()
	{
		this.ontTemplate=new  LSMSchema();
		this.ontInstance=new  LSMSchema();
		
		initOnt_DefaultRequest();
	}
	public DefaultGraphEnt2(String filePath)
	{
		this.ontTemplate=new  LSMSchema(filePath, OntModelSpec.OWL_DL_MEM,"TURTLE");
		this.ontInstance=new  LSMSchema();
		
		initOnt_DefaultRequest();		
	}
	
	private void initOnt_DefaultRequest()
	{
		ontClsDefaultGraph = ontTemplate.createClass("http://openiot.eu/ontology/ns/DefaultGraph");
		ontPdefaultGraphURI = ontTemplate.createProperty("http://openiot.eu/ontology/ns/defaultgraphURI");
		ontPdefaultGraphOfQueryRequest = ontTemplate.createProperty("http://openiot.eu/ontology/ns/defaultgraphOfQueryRequest");
	}
	
	////
	public Individual getClassIndividual()
	{
		return defaultGraphClassIdv;
	}
	////
	
	public void createClassIdv()
	{
		if(getId()==null)
			defaultGraphClassIdv = ontInstance.createIndividual(ontClsDefaultGraph);
		else
			defaultGraphClassIdv = ontInstance.createIndividual(getId(),ontClsDefaultGraph);
	}
	public void createPdefaultGraphURI()
	{
		if(getDefaultGraphURI()!=null)	
			defaultGraphClassIdv.setPropertyValue(ontPdefaultGraphURI, ontInstance.getBase().createTypedLiteral(getDefaultGraphURI()));
	}
	public void createPdefaultGraphOfQueryRequest()
	{
		if(queryRequestEnt!=null)	
			defaultGraphClassIdv.addProperty(ontPdefaultGraphOfQueryRequest, queryRequestEnt.getClassIndividual());
	}
	
	
	public QueryRequestEnt2 getQueryRequestEnt() {
		return queryRequestEnt;
	}
	public void setQueryRequestEnt(QueryRequestEnt2 queryRequestEnt) {
		this.queryRequestEnt = queryRequestEnt;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDefaultGraphURI() {
		return defaultGraphURI;
	}
	public void setDefaultGraphURI(String defaultGraphURI) {
		this.defaultGraphURI = defaultGraphURI;
	}
}
