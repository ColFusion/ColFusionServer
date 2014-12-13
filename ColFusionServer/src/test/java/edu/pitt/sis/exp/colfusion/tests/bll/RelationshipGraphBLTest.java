package edu.pitt.sis.exp.colfusion.tests.bll;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import edu.pitt.sis.exp.colfusion.bll.RelationshipGraphBL;
import edu.pitt.sis.exp.colfusion.dal.dao.StatonverdictsDAO;
import edu.pitt.sis.exp.colfusion.dal.dao.StatonverdictsDAOImpl;
import edu.pitt.sis.exp.colfusion.dal.dataModels.tableDataModel.RelationKey;
import edu.pitt.sis.exp.colfusion.dal.managers.DNameInfoManager;
import edu.pitt.sis.exp.colfusion.dal.managers.DNameInfoManagerImpl;
import edu.pitt.sis.exp.colfusion.dal.managers.GeneralManagerImpl;
import edu.pitt.sis.exp.colfusion.dal.managers.RelationshipsManager;
import edu.pitt.sis.exp.colfusion.dal.managers.RelationshipsManagerImpl;
import edu.pitt.sis.exp.colfusion.dal.managers.SourceInfoManager;
import edu.pitt.sis.exp.colfusion.dal.managers.SourceInfoManagerImpl;
import edu.pitt.sis.exp.colfusion.dal.orm.ColfusionRelationships;
import edu.pitt.sis.exp.colfusion.dal.orm.ColfusionSourceinfo;
import edu.pitt.sis.exp.colfusion.dal.viewmodels.DnameViewModel;
import edu.pitt.sis.exp.colfusion.relationships.relationshipGraph.RelationshipGraph;
import edu.pitt.sis.exp.colfusion.relationships.relationshipGraph.RelationshipGraphEdge;
import edu.pitt.sis.exp.colfusion.relationships.relationshipGraph.RelationshipGraphNode;
import edu.pitt.sis.exp.colfusion.relationships.relationshipGraph.RelationshipGraphEdge.NodeInfo;
import edu.pitt.sis.exp.colfusion.responseModels.RelationshipGraphResponseModel;

public class RelationshipGraphBLTest {

	//Test case 1 : Empty database
	
	public void testGetRelationshipGraph_Empty(){
		RelationshipGraphResponseModel result = new RelationshipGraphResponseModel();
		RelationshipGraphBL graphBL = new RelationshipGraphBL();
		
		RelationshipGraphResponseModel expResult = new RelationshipGraphResponseModel();
		RelationshipGraph expGraph = new RelationshipGraph();
		expGraph.setEdges(new  ArrayList<RelationshipGraphEdge>());
		expGraph.setNodes(new ArrayList<RelationshipGraphNode>());
		expResult.setPayload(expGraph);
		try{
			RelationshipGraph graph = graphBL.getRelationshipGraph();
			result.setPayload(graph);
			assertEquals(expResult.toJson(),result.toJson());
		}catch(Exception e ){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	//Test case 2 : Data sets without relationships(After insert);
	
	public void testGetRelationshipGraph_NoRelationship(){
		RelationshipGraphResponseModel result = new RelationshipGraphResponseModel();
		RelationshipGraphBL graphBL = new RelationshipGraphBL();
		
		RelationshipGraphResponseModel expResult = new RelationshipGraphResponseModel();
		RelationshipGraph expGraph = new RelationshipGraph();
		
		List<RelationshipGraphNode> nodes= new ArrayList<RelationshipGraphNode>();
		SourceInfoManager sourceInfoMng = new SourceInfoManagerImpl();
		List<ColfusionSourceinfo> storyList = new ArrayList<ColfusionSourceinfo>();
		
		try {
			storyList.add(sourceInfoMng.findByID(2210));
			storyList.add(sourceInfoMng.findByID(2211));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setExpNodes(storyList, nodes);

		expGraph.setEdges(new  ArrayList<RelationshipGraphEdge>());
		expGraph.setNodes(nodes);
		expResult.setPayload(expGraph);
		
		try{
			RelationshipGraph graph = graphBL.getRelationshipGraph();
			result.setPayload(graph);
			assertEquals(expResult.toJson(),result.toJson());
		}catch(Exception e ){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	//Test case 3 : Test if system can detect the relationships.(Insert a new relationship(direct-related))
	@Test
	public void testGetRelationshipGraph_DirectRelationship() throws NoSuchFieldException, IllegalAccessException{
		RelationshipGraphResponseModel result = new RelationshipGraphResponseModel();
		RelationshipGraphBL graphBL = new RelationshipGraphBL();
		
		RelationshipGraphResponseModel expResult = new RelationshipGraphResponseModel();
		RelationshipGraph expGraph = new RelationshipGraph();
		
		List<RelationshipGraphNode> nodes= new ArrayList<RelationshipGraphNode>();
		List<RelationshipGraphEdge> edges = new ArrayList<RelationshipGraphEdge>();
		
		SourceInfoManager sourceInfoMng = new SourceInfoManagerImpl();
		List<ColfusionSourceinfo> storyList = new ArrayList<ColfusionSourceinfo>();
		
		try {
			storyList.add(sourceInfoMng.findByID(2210));
			storyList.add(sourceInfoMng.findByID(2211));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setExpNodes(storyList, nodes);
		expGraph.setNodes(nodes);
		
		RelationshipsManager relationshipMng = new RelationshipsManagerImpl();
		List<ColfusionRelationships> relationships =  new ArrayList<ColfusionRelationships>();
		try {
			relationships.add(relationshipMng.findByID(13));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setExpEdges(relationships, edges);
		expGraph.setEdges(edges);
		expResult.setPayload(expGraph);
		
		try{
			RelationshipGraph graph = graphBL.getRelationshipGraph();
			result.setPayload(graph);
			assertEquals(expResult.toJson(),result.toJson());
		}catch(Exception e ){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	public void setExpEdges(List<ColfusionRelationships> relationships,List<RelationshipGraphEdge> edges) throws NoSuchFieldException, IllegalAccessException{
		StatonverdictsDAO dao = new StatonverdictsDAOImpl();
		Map<Integer, BigDecimal> confidenceMap = dao.getAvgConfidence();
		
		for (ColfusionRelationships relationship : relationships) {
			
			relationship = GeneralManagerImpl.initializeField(relationship, "colfusionSourceinfoBySid1");
			
			NodeInfo sidFrom = new NodeInfo(relationship.getColfusionSourceinfoBySid1().getSid(), 
					relationship.getColfusionSourceinfoBySid1().getTitle(), relationship.getTableName1());
			
			relationship = GeneralManagerImpl.initializeField(relationship, "colfusionSourceinfoBySid2");
			
			NodeInfo sidTo = new NodeInfo(relationship.getColfusionSourceinfoBySid2().getSid(), 
					relationship.getColfusionSourceinfoBySid2().getTitle(), relationship.getTableName2());
			
			RelationshipGraphEdge edge = new RelationshipGraphEdge(relationship.getRelId(), relationship.getName(), 
					confidenceMap.get(relationship.getRelId()).doubleValue(), sidFrom, sidTo);
			
			edges.add(edge);
		}
		
	}
	
	public void setExpNodes(List<ColfusionSourceinfo> storyList, List<RelationshipGraphNode> nodes){
		SourceInfoManager sourceInfoMng = new SourceInfoManagerImpl();
		DNameInfoManager dNameInfoMng = new DNameInfoManagerImpl();
		for (ColfusionSourceinfo story : storyList) {
			
			List<RelationKey> tableNames = sourceInfoMng.getTableNames(story.getSid());
			
			for (RelationKey table : tableNames) {
				List<DnameViewModel> allColumns = dNameInfoMng.getDnameListViewModel(story.getSid(), table.getTableName());
				
				RelationshipGraphNode node = new RelationshipGraphNode(story.getSid(), table.getTableName(), story.getTitle(), allColumns);
				nodes.add(node);
			}	
		}
	}
	
}
